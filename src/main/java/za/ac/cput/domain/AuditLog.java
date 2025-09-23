package za.ac.cput.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "audit_log")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id_INT")
    private Integer logId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_VARCHAR(20)")
    private  RoleType user; // Need to be changed and make Student,Staff and Admin

    @Column(name = "action_TEXT")
    private String action;

    @Column(name = "entity_id_CHAR(36)")
    private String entityId;

    @Column(name = "entity_type_VARCHAR(100)")
    private String entityType;

    @Column(name = "details_JSON")
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

    public RoleType getUser() {
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
        public Builder setUser(RoleType user) { this.user = user; return this; }
        public Builder setAction(String action) { this.action = action; return this; }
        public Builder setEntityId(String entityId) { this.entityId = entityId; return this; }
        public Builder setEntityType(String entityType) { this.entityType = entityType; return this; }
        public Builder setDetailsJson(String detailsJson) { this.detailsJson = detailsJson; return this; }

        public Builder copy(AuditLog auditLog) {
            this.logId = auditLog.getLogId();
            this.user = auditLog.getUser();
            this.action = auditLog.getAction();
            this.entityId = auditLog.getEntityId();
            this.entityType = auditLog.getEntityType();
            this.detailsJson = auditLog.getDetailsJson();
            return this;
        }

        public AuditLog build() { return new AuditLog(this); }
    }
}
