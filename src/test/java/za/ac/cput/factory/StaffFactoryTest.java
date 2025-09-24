package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Staff;

import static org.junit.jupiter.api.Assertions.*;

class StaffFactoryTest {

    @Test
    void testCreateStaffSuccess() {
        Staff staff = StaffFactory.createStaff(
                "Alice", "Smith", "alice.smith@example.com", "STF12345", true
        );

        assertNotNull(staff);
        assertNotNull(staff.getStaffId(), "Staff ID should be auto-generated");
        assertFalse(staff.getStaffId().isEmpty(), "Staff ID should not be empty");

        assertEquals("Alice", staff.getFirstName());
        assertEquals("Smith", staff.getLastName());
        assertEquals("alice.smith@example.com", staff.getEmail());
        assertEquals("STF12345", staff.getStaffNumber());
        assertTrue(staff.getClinical(), "Staff should be clinical");
    }

    @Test
    void testCreateStaffInvalidFirstName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                StaffFactory.createStaff("", "Smith", "alice.smith@example.com", "STF12345", true)
        );
        assertEquals("First name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCreateStaffInvalidLastName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                StaffFactory.createStaff("Alice", "", "alice.smith@example.com", "STF12345", true)
        );
        assertEquals("Last name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCreateStaffInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                StaffFactory.createStaff("Alice", "Smith", "invalid-email", "STF12345", true)
        );
        assertEquals("Invalid email address", exception.getMessage());
    }

    @Test
    void testCreateStaffInvalidStaffNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                StaffFactory.createStaff("Alice", "Smith", "alice.smith@example.com", "", true)
        );
        assertEquals("Staff number cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCreateStaffNullIsClinical() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                StaffFactory.createStaff("Alice", "Smith", "alice.smith@example.com", "STF12345", null)
        );
        assertEquals("isClinical cannot be null", exception.getMessage());
    }
}
