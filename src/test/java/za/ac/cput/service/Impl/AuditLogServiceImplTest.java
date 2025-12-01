package za.ac.cput.service.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.domain.AuditLog;
import za.ac.cput.domain.RoleType;
import za.ac.cput.repository.AuditLogRepository;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AuditLogServiceImplTest {

    @Mock
    private AuditLogRepository repository;

    @InjectMocks
    private AuditLogServiceImpl service;

    private AuditLog auditLogs;

    @BeforeEach
    void setUp() {
        auditLogs = new AuditLog.Builder()
                .setUser (RoleType.STUDENT)  // No space in method call
                .setAction("LOGIN")
                .setEntityId("123e4567-e89b-12d3-a456-426614174000")
                .setEntityType("User ")
                .setDetailsJson("{\"timestamp\":\"2023-01-01\"}")
                .setLogId(1)
                .build();
    }


    @Test
    void _ShouldReturnListForUser() {  // FIXED: No spaces in method name, uses underscore
        // Arrange
        RoleType user = RoleType.STUDENT;
        List<AuditLog> expectedList = Collections.singletonList(auditLogs);
        when(repository.findByUser (user)).thenReturn(expectedList);  // No space in method call

        // Act
        List<AuditLog> result = service.findByUser (user);  // No space in method call

        // Assert
        assertEquals(1, result.size());
        assertEquals(user, result.get(0).getUser ());  // No space in method call
        verify(repository, times(1)).findByUser (user);  // No space in method call
    }
}
