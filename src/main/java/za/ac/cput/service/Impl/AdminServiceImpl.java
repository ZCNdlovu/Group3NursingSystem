package za.ac.cput.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.RoleType;

import za.ac.cput.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import za.ac.cput.service.IAdminService;
import za.ac.cput.util.IdGenerator;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements IAdminService {
    private final AdminRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final IdGenerator idGenerator;

    @Autowired
    public AdminServiceImpl(AdminRepository repository, PasswordEncoder passwordEncoder,  IdGenerator idGenerator) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.idGenerator = idGenerator;
    }

    @Override
    public Admin create(Admin admin) {
        if (admin.getAdminId() == null || admin.getAdminId().isEmpty()) {
            // 2. Generate the custom ID
            String newId = idGenerator.generateNextId("AD");
            admin = new Admin.Builder().copy(admin).setAdminId(newId).build();
        }
        admin.setRole(RoleType.ADMIN);
        admin.setPassword(passwordEncoder.encode(admin.getPassword())); // Encode password
        return repository.save(admin);
    }

    @Override
    public Admin read(String adminId) {
        return repository.findById(adminId).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        if (admin.getAdminId() == null) {
            throw new IllegalArgumentException("Cannot update admin without an ID");
        }
        if (repository.existsById(admin.getAdminId())) {
            return repository.save(admin); // ✅ this is UPDATE
        }
        return null;
    }

    @Override
    public List<Admin> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String staffId) {
        repository.deleteById(staffId);
    }

    @Override
    public Optional<Admin> findByEmail(String email)  {
        return repository.findByEmail(email);
    }




}
