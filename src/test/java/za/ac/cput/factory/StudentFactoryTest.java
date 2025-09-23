package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentFactoryTest {

    @Test
    void testCreateStudentSuccess() {
        Student student = StudentFactory.createStudent(
                "John", "Doe", "john.doe@example.com"
        );

        assertNotNull(student);
        assertNotNull(student.getStudentId(), "Student ID should be auto-generated");
        assertFalse(student.getStudentId().isEmpty(), "Student ID should not be empty");

        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("john.doe@example.com", student.getEmail());
    }

    @Test
    void testCreateStudentInvalidFirstName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                StudentFactory.createStudent("", "Doe", "john.doe@example.com")
        );
        assertEquals("First name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCreateStudentInvalidLastName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                StudentFactory.createStudent("John", "", "john.doe@example.com")
        );
        assertEquals("Last name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCreateStudentInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                StudentFactory.createStudent("John", "Doe", "not-an-email")
        );
        assertEquals("Invalid email address", exception.getMessage());
    }
}
