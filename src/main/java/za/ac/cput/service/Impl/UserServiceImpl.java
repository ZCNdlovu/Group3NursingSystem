package za.ac.cput.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.*;
import za.ac.cput.repository.AdminRepository;
import za.ac.cput.repository.PasswordResetTokenRepository;
import za.ac.cput.repository.StudentRepository;
import za.ac.cput.repository.StaffRepository;
import za.ac.cput.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(AdminRepository adminRepository, StudentRepository studentRepository, StaffRepository staffRepository, PasswordResetTokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.staffRepository = staffRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent()) {
            return Optional.of(admin.get());
        }
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            return Optional.of(student.get());
        }
        Optional<Staff> staff = staffRepository.findByEmail(email);
        if (staff.isPresent()) {
            return Optional.of(staff.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findEmailByResetToken(String token) {
        return tokenRepository.findByToken(token)
                .map(PasswordResetToken::getUserEmail);
    }
    @Override
    @Transactional
    public void createPasswordResetTokenForUser(String email, String token) {
        // Clear old tokens for this user
        tokenRepository.deleteByUserEmail(email);

        // Create new token
        PasswordResetToken myToken = new PasswordResetToken(token, email);
        tokenRepository.save(myToken);
    }

    @Override
    public Optional<String> validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isEmpty()) {
            return Optional.of("invalidToken");
        }

        PasswordResetToken resetToken = tokenOpt.get();
        if (resetToken.isExpired()) {
            return Optional.of("expired");
        }

        return Optional.empty(); // Token is valid
    }

    @Override
    @Transactional
    public void resetPassword(String email, String newPassword) {
        Optional<User> userOpt = findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String encodedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedPassword);
            save(user);
            tokenRepository.deleteByUserEmail(email);
        }
    }

    @Override
    public void save(User user) {
        if (user instanceof Admin) {
            adminRepository.save((Admin) user);
        } else if (user instanceof Student) {
            studentRepository.save((Student) user);
        } else if (user instanceof Staff) {
            staffRepository.save((Staff) user);
        }
        // Add other user types here
    }
}
