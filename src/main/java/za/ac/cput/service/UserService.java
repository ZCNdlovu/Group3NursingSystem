package za.ac.cput.service;

import za.ac.cput.domain.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    void createPasswordResetTokenForUser(String email, String token);
    Optional<String> validatePasswordResetToken(String token);
    void resetPassword(String email, String newPassword);
    void save(User user);
    Optional<String> findEmailByResetToken(String token);
}
