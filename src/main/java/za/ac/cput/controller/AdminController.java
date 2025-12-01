package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.RoleType;
import za.ac.cput.domain.dto.AdminRegistrationRequest;
import za.ac.cput.service.EmailService;
import za.ac.cput.service.IAdminService;
import za.ac.cput.service.Impl.AdminServiceImpl;
import za.ac.cput.service.Impl.EmailServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CREATE - Register Admin
    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody AdminRegistrationRequest request) {
        try {
            // Check if admin already exists by email (optional: for better error handling)
            if (adminService.findByEmail(request.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error creating admin: Email already registered.");
            }

            String tempPassword = UUID.randomUUID().toString().substring(0, 8);
            String encodedPassword = passwordEncoder.encode(tempPassword);

            Admin admin = new Admin.Builder()
                    .setEmail(request.getEmail())
                    .setFirstName(request.getFirstName())
                    .setLastName(request.getLastName())
                    .setPhone(request.getPhone())
                    .setPassword(encodedPassword)
                    .build();
            // The service implementation already sets RoleType.ADMIN and encodes password if needed,
            // but setting it here doesn't hurt, assuming Admin class supports a setter/builder.

            Admin savedAdmin = adminService.create(admin);

            // Send email
            String subject = "Welcome to Nursing System - Your Admin Account";
            String emailBody = String.format(
                    "Dear %s %s,\n\n" +
                            "Your administrator account has been created.\n" +
                            "Login details:\n" +
                            "Email: %s\n" +
                            "Temporary Password: %s\n\n" +
                            "Please change your password after first login.\n\n" +
                            "Best regards,\nSystem Administrator",
                    request.getFirstName(), request.getLastName(), request.getEmail(), tempPassword
            );

            try {
                emailService.sendSimpleMessage(request.getEmail(), subject, emailBody);
            } catch (Exception e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating admin: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Admin> getAdminById(@PathVariable String id) {
        Admin admin = adminService.read(id);
        return admin != null ? ResponseEntity.ok(admin) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Restrict to Admin role
    public ResponseEntity<Admin> updateAdmin(@PathVariable String id, @RequestBody Admin admin) {
        // Build the admin object, ensuring the ID from the path is set
        Admin adminToUpdate = new Admin.Builder()
                .copy(admin)
                .setAdminId(id) // Assuming Admin has a Builder
                .build();

        Admin updated = adminService.update(adminToUpdate);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAdmin(@PathVariable String id) {
        adminService.delete(id); // service method parameter is staffId, but context implies adminId
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAll();
        return ResponseEntity.ok(admins);
    }
}