package za.ac.cput.factory;

import za.ac.cput.domain.Student;

import java.util.UUID;

public class StudentFactory {

    public static Student createStudent(String firstName, String lastName, String email) {
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


        String studentId = UUID.randomUUID().toString();

        return new Student.Builder()
                .setStudentId(studentId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .build();
    }
}
