package za.ac.cput.factory;

import jakarta.persistence.Column;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.RoleType;
import za.ac.cput.domain.Student;
import za.ac.cput.util.IdGenerator;

import java.util.UUID;
@Component
public class StudentFactory {
    private static IdGenerator idGenerator;
    public StudentFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
    public Student createStudent(String firstName, String lastName, String email,String password,String studentNumber, Short yearLevel, String program ) {
        // Basic validation
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (studentNumber == null || studentNumber.isEmpty()) {
            throw new IllegalArgumentException("Student Number cannot be null or empty");
        }
        if (yearLevel == null || yearLevel <= 0) {
            throw new IllegalArgumentException("Year Level cannot be null or empty");
        }
        if (program == null || program.isEmpty()) {
            throw new IllegalArgumentException("Program cannot be null or empty");
        }

        String studentId = idGenerator.generateNextId("ST");

        return new Student.Builder()
                .setStudentId(studentId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setStudentNumber(studentNumber)
                .setYearLevel(yearLevel)
                .setProgram(program)
                .setRole(RoleType.STUDENT)
                .build();
    }
}
