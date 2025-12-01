package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "audit_log")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RoleType user; // Enum: STUDENT, STAFF, ADMIN

    @Column(name = "action",nullable = false)
    private String action;

    @Column(name = "entity_id")
    private String entityId;

    @Column(name = "entity_type", nullable = false)
    private String entityType;

    @Column(name = "details", nullable = false)
    private String detailsJson;

    protected AuditLog() {}

    private AuditLog(Builder builder) {
        this.logId = builder.logId;
        this.user = builder.user;
        this.action = builder.action;
        this.entityId = builder.entityId;
        this.entityType = builder.entityType;
        this.detailsJson = builder.detailsJson;
    }

    public Integer getLogId() {
        return logId;
    }

    public RoleType getUser () {  // Fixed: No space before ()
        return user;
    }

    public String getAction() {
        return action;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getDetailsJson() {
        return detailsJson;
    }

    public static class Builder {
        private Integer logId;
        private RoleType user;
        private String action;
        private String entityId;
        private String entityType;
        private String detailsJson;

        public Builder setLogId(Integer logId) { this.logId = logId; return this; }
        public Builder setUser (RoleType user) { this.user = user; return this; }  // Fixed: No space before (RoleType
        public Builder setAction(String action) { this.action = action; return this; }
        public Builder setEntityId(String entityId) { this.entityId = entityId; return this; }
        public Builder setEntityType(String entityType) { this.entityType = entityType; return this; }
        public Builder setDetailsJson(String detailsJson) { this.detailsJson = detailsJson; return this; }

        public Builder copy(AuditLog auditLog) {
            this.logId = auditLog.getLogId();
            this.user = auditLog.getUser ();  // Fixed: No space
            this.action = auditLog.getAction();
            this.entityId = auditLog.getEntityId();
            this.entityType = auditLog.getEntityType();
            this.detailsJson = auditLog.getDetailsJson();
            return this;
        }

        public AuditLog build() { return new AuditLog(this); }
    }
}
