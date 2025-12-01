package za.ac.cput.factory;

import org.springframework.stereotype.Component;
import za.ac.cput.domain.Admin;
import za.ac.cput.util.IdGenerator;
@Component
public class AdminFactory {
 private static IdGenerator idGenerator;
    public AdminFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
    public static Admin createAdmin(String firstName, String lastName, String email, String phone, String password) {
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
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        // Generate custom admin ID using IdGenerator
        String adminId = idGenerator.generateNextId("AD");

        return new Admin.Builder()
                .setAdminId(adminId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhone(phone)
                .setPassword(password) // Note: Password should be encoded before saving to database
                .build();
    }

    // Overloaded method for cases where you might not have all fields initially
    public Admin createAdmin(String firstName, String lastName, String email) {
        return createAdmin(firstName, lastName, email, "default-phone", "default-password");
    }

    // Method for creating admin with registration data
    public Admin createAdminWithDefaults(String firstName, String lastName, String email, String phone) {
        // Generate a temporary password
        String tempPassword = generateTemporaryPassword();

        return createAdmin(firstName, lastName, email, phone, tempPassword);
    }

    private String generateTemporaryPassword() {
        // Generate a random temporary password (you can enhance this)
        return "TempPass123!"; // In production, use a proper random generator
    }
}
