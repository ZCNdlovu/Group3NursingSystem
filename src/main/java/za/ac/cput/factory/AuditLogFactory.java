package za.ac.cput.factory;
import za.ac.cput.domain.AuditLog;
import za.ac.cput.domain.RoleType;

public class AuditLogFactory {
    public static AuditLog createAuditLog(RoleType user, String action, String entityId, String entityType, String detailsJson) {
        return new AuditLog.Builder()
                .setUser (user)
                .setAction(action)
                .setEntityId(entityId)
                .setEntityType(entityType)
                .setDetailsJson(detailsJson)
                .build();
    }
}
