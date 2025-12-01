package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.AuditLog;
import za.ac.cput.domain.RoleType;

import static org.assertj.core.api.Assertions.assertThat;

class AuditLogFactoryTest {

    @Test
    void createAuditLog_ShouldReturnValidAuditLog() {
        // Arrange
        RoleType user = RoleType.STUDENT;
        String action = "LOGIN";
        String entityId = "123e4567-e89b-12d3-a456-426614174000";
        String entityType = "User ";
        String detailsJson = "{\"timestamp\":\"2023-01-01\"}";

        // Act
        AuditLog auditLog = AuditLogFactory.createAuditLog(user, action, entityId, entityType, detailsJson);

        // Assert
        assertThat(auditLog).isNotNull();
        assertThat(auditLog.getUser ()).isEqualTo(user);
        assertThat(auditLog.getAction()).isEqualTo(action);
        assertThat(auditLog.getEntityId()).isEqualTo(entityId);
        assertThat(auditLog.getEntityType()).isEqualTo(entityType);
        assertThat(auditLog.getDetailsJson()).isEqualTo(detailsJson);
        assertThat(auditLog.getLogId()).isNull(); // ID is generated
    }
}
