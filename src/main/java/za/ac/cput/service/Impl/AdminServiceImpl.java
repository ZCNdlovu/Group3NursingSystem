package za.ac.cput.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.RoleType;

import za.ac.cput.repository.AdminRepository;

import za.ac.cput.service.IAdminService;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements IAdminService {
    private final AdminRepository repository;

    @Autowired
    public AdminServiceImpl(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public Admin create(Admin admin) {
        admin.setRole(RoleType.ADMIN);
        return repository.save(admin);
    }

    @Override
    public Admin read(String adminId) {
        return repository.findById(adminId).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        if (repository.existsById(admin.getAdminId())) {
            return repository.save(admin);
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
    public Optional<Admin> findByEmail(String email) {
        return repository.findByEmail(email);
    }



    @Override
    public List<Admin> findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }
}
