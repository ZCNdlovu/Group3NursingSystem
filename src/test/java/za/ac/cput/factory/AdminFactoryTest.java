package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Admin;

import static org.junit.jupiter.api.Assertions.*;

class AdminFactoryTest {

    @Test
    void testCreateAdminSuccess() {
        Admin admin = AdminFactory.createAdmin(
                "Bob", "Johnson", "bob.johnson@example.com"
        );

        assertNotNull(admin, "Admin object should not be null");
        assertNotNull(admin.getAdminId(), "Admin ID should be auto-generated");
        assertFalse(admin.getAdminId().isEmpty(), "Admin ID should not be empty");

        assertEquals("Bob", admin.getFirstName());
        assertEquals("Johnson", admin.getLastName());
        assertEquals("bob.johnson@example.com", admin.getEmail());
    }

    @Test
    void testCreateAdminInvalidFirstName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                AdminFactory.createAdmin("", "Johnson", "bob.johnson@example.com")
        );
        assertEquals("First name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCreateAdminInvalidLastName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                AdminFactory.createAdmin("Bob", "", "bob.johnson@example.com")
        );
        assertEquals("Last name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCreateAdminInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                AdminFactory.createAdmin("Bob", "Johnson", "invalid-email")
        );
        assertEquals("Invalid email address", exception.getMessage());
    }
}
