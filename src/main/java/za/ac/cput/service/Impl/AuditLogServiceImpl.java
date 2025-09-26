package za.ac.cput.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.AuditLog;
import za.ac.cput.domain.RoleType;
import za.ac.cput.repository.AuditLogRepository;
import za.ac.cput.service.IAuditLogService;

import java.util.List;
import java.util.Optional;

@Service
public class AuditLogServiceImpl implements IAuditLogService {

    private final AuditLogRepository repository;

    public AuditLogServiceImpl(AuditLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuditLog save(AuditLog auditLog) {
        return repository.save(auditLog);
    }

    @Override
    public List<AuditLog> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AuditLog> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<AuditLog> findByUser (RoleType user) {  // Fixed: No space before (RoleType
        return repository.findByUser (user);  // Fixed: No space
    }
}
