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

    public static AuditLog createAuditLogWithId(Integer logId, RoleType user, String action, String entityId, String entityType, String detailsJson) {
        return new AuditLog.Builder()
                .setLogId(logId)
                .setUser (user)
                .setAction(action)
                .setEntityId(entityId)
                .setEntityType(entityType)
                .setDetailsJson(detailsJson)
                .build();
    }


    public static AuditLog createDefaultAuditLog() {
        RoleType defaultUser  = RoleType.STUDENT;
        return createAuditLog(defaultUser , "User  login", "123e4567-e89b-12d3-a456-426614174000", "User ", "{\"timestamp\": \"2023-01-01\"}");
    }
}
