package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.AuditLog;
import za.ac.cput.domain.RoleType;

import static org.junit.jupiter.api.Assertions.*;

public class AuditLogFactoryTest {

    @Test
    void testCreateAuditLog() {

        RoleType User  = RoleType.STUDENT;
        String Action = "Update evaluation";
        String EntityId = "456e7890-e89b-12d3-a456-426614174000";
        String EntityType = "Evaluation";
        String DetailsJson = "{\"rating\": 5, \"comment\": \"Great!\"}";


        AuditLog auditLog = AuditLogFactory.createAuditLog(User , Action, EntityId, EntityType, DetailsJson);


        assertNotNull(auditLog);
        assertNull(auditLog.getLogId());
        assertEquals(User , auditLog.getUser ());
        assertEquals(Action, auditLog.getAction());
        assertEquals(EntityId, auditLog.getEntityId());
        assertEquals(EntityType, auditLog.getEntityType());
        assertEquals(DetailsJson, auditLog.getDetailsJson());
    }

    @Test
    void testCreateAuditLogWithId() {

        Integer Id = 999;
        RoleType User  = RoleType.STAFF;
        String Action = "Delete placement";
        String EntityId = "789e0123-e89b-12d3-a456-426614174000";
        String EntityType = "Placement";
        String DetailsJson = "{\"reason\": \"Completed\"}";

        AuditLog auditLog = AuditLogFactory.createAuditLogWithId(Id, User , Action, EntityId, EntityType, DetailsJson);


        assertNotNull(auditLog);
        assertEquals(Id, auditLog.getLogId());
        assertEquals(User , auditLog.getUser ());
        assertEquals(Action, auditLog.getAction());
        assertEquals(EntityId, auditLog.getEntityId());
        assertEquals(EntityType, auditLog.getEntityType());
        assertEquals(DetailsJson, auditLog.getDetailsJson());
    }

    @Test
    void testCreateDefaultAuditLog() {

        AuditLog auditLog = AuditLogFactory.createDefaultAuditLog();

        assertNotNull(auditLog);
        assertNull(auditLog.getLogId());
        assertEquals(RoleType.STUDENT, auditLog.getUser ());
        assertEquals("User  login", auditLog.getAction());
        assertEquals("123e4567-e89b-12d3-a456-426614174000", auditLog.getEntityId());
        assertEquals("User ", auditLog.getEntityType());
        assertEquals("{\"timestamp\": \"2023-01-01\"}", auditLog.getDetailsJson());
    }
}
