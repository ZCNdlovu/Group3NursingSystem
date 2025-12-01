package za.ac.cput.factory;

import org.springframework.stereotype.Component;
import za.ac.cput.domain.Student;
import za.ac.cput.util.IdGenerator;

import java.util.UUID;
@Component
public class StudentFactory {
    private static IdGenerator idGenerator;
    public StudentFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
    public Student createStudent(String firstName, String lastName, String email) {
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


        String studentId = idGenerator.generateNextId("ST");

        return new Student.Builder()
                .setStudentId(studentId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .build();
    }
}
