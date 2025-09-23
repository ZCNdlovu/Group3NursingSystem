package za.ac.cput.factory;

import za.ac.cput.domain.Staff;

import java.util.UUID;

public class StaffFactory {

    public static Staff createStaff(String firstName, String lastName, String email,
                                    String staffNumber, Boolean isClinical) {
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
        if (isClinical == null) {
            throw new IllegalArgumentException("isClinical cannot be null");
        }

        // Auto-generate UUID as staffId
        String staffId = UUID.randomUUID().toString();

        return new Staff.Builder()
                .setStaffId(staffId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setStaffNumber(staffNumber)
                .setClinical(isClinical)
                .build();
    }
}
