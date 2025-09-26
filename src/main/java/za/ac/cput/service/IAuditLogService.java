package za.ac.cput.service;

import za.ac.cput.domain.AuditLog;
import za.ac.cput.domain.RoleType;

import java.util.List;
import java.util.Optional;

public interface IAuditLogService {
    AuditLog save(AuditLog auditLog);
    List<AuditLog> findAll();
    Optional<AuditLog> findById(Integer id);
    void deleteById(Integer id);
    List<AuditLog> findByUser (RoleType user);  // Fixed: No space before (RoleType
}
