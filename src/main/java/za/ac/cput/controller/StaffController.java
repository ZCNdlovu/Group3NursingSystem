package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.RoleType;
import za.ac.cput.domain.dto.StaffRegistrationRequest;
import za.ac.cput.service.EmailService;
import za.ac.cput.service.IStaffService;
import za.ac.cput.service.Impl.EmailServiceImpl;
import za.ac.cput.service.Impl.StaffServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    private StaffServiceImpl staffService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerStaff(@RequestBody StaffRegistrationRequest request) {
        try {
            // Generate temporary password
            String tempPassword = UUID.randomUUID().toString().substring(0, 8);
            // The StaffServiceImpl.create() method will handle password encoding
            // and setting the default RoleType.STAFF.

            Staff staff = new Staff.Builder()
                    .setEmail(request.getEmail())
                    .setFirstName(request.getFirstName())
                    .setLastName(request.getLastName())
                    .setPhone(request.getPhone())
                    .setStaffNumber(request.getStaffNumber())
                    .setClinical(request.getIsClinical())
                    // Pass the unencoded temporary password to the service
                    .setPassword(tempPassword)
                    .build();

            Staff savedStaff = staffService.create(staff); // Service handles persistence, ID, role, and encoding

            // Send email
            String subject = "Welcome to Nursing System - Your Staff Account";
            String emailBody = String.format(
                    "Dear %s %s,\n\n" +
                            "Your staff account has been created.\n" +
                            "Login details:\n" +
                            "Email: %s\n" +
                            "Temporary Password: %s\n\n" +
                            "Role: %s\n\n" +
                            "Please change your password after first login.\n\n" +
                            "Best regards,\nNursing System Admin",
                    request.getFirstName(), request.getLastName(), request.getEmail(), tempPassword,
                    request.getIsClinical() ? "Clinical Staff" : "Non-Clinical Staff"
            );

            try {
                emailService.sendSimpleMessage(request.getEmail(), subject, emailBody);
            } catch (Exception e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(savedStaff);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating staff: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STAFF') and #id == authentication.principal.username)")
    public ResponseEntity<Staff> updateStaff(@PathVariable String id, @RequestBody Staff staff) {
        // Build the staff object, ensuring the ID from the path is set
        Staff staffToUpdate = new Staff.Builder()
                .copy(staff)
                .setStaffId(id)
                .build();

        Staff updated = staffService.update(staffToUpdate);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staff = staffService.getAll();
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Staff> getStaffById(@PathVariable String id) {
        Staff staff = staffService.read(id);
        return staff != null ? ResponseEntity.ok(staff) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStaff(@PathVariable String id) {
        Staff existingStaff = staffService.read(id);
        if (existingStaff == null) {
            return ResponseEntity.notFound().build();
        }
        staffService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('ADMIN')") // Restrict sensitive search to Admin
    public ResponseEntity<Staff> findByEmail(@PathVariable String email) {
        return staffService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/staff-number/{staffNumber}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Staff> findByStaffNumber(@PathVariable String staffNumber) {
        return staffService.findByStaffNumber(staffNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}