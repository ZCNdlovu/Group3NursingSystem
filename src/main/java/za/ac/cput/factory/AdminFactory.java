package za.ac.cput.factory;

import za.ac.cput.domain.Admin;

import java.util.UUID;

public class AdminFactory {

    public static Admin createAdmin(String firstName, String lastName, String email) {
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

        // Auto-generate UUID as adminId
        String adminId = UUID.randomUUID().toString();

        return new Admin.Builder()
                .setAdminId(adminId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .build();
    }
}
