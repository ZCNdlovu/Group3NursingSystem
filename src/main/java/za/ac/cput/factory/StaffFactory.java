package za.ac.cput.factory;

import org.springframework.stereotype.Component;
import za.ac.cput.domain.Staff;
import za.ac.cput.util.IdGenerator;

import java.util.UUID;
@Component
public class StaffFactory {
    private static IdGenerator idGenerator;
    public StaffFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }
    public Staff createStaff(String firstName, String lastName, String email,
                                    String staffNumber, String password, Boolean isClinical) {
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
        if (staffNumber == null || staffNumber.isEmpty()) {
            throw new IllegalArgumentException("Staff number cannot be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (isClinical == null) {
            throw new IllegalArgumentException("isClinical cannot be null");
        }

        // Auto-generate UUID as staffId
        String staffId = idGenerator.generateNextId("SF");

        return new Staff.Builder()
                .setStaffId(staffId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setStaffNumber(staffNumber)
                .setPassword(password)
                .setClinical(isClinical)
                .build();
    }
}
