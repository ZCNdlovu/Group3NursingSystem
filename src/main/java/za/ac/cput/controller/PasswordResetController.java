package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.dto.PasswordResetRequest;
import za.ac.cput.domain.dto.PasswordResetTokenRequest;
import za.ac.cput.service.EmailService;
import za.ac.cput.service.Impl.EmailServiceImpl;
import za.ac.cput.service.Impl.UserServiceImpl;
import za.ac.cput.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class PasswordResetController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody PasswordResetTokenRequest request) {
        try {
            // Check if user exists
            if (userService.findByEmail(request.getEmail()).isEmpty()) {
                // For security, don't reveal if email exists or not
                return ResponseEntity.ok().body("If the email exists, a password reset link has been sent.");
            }

            // Generate token
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(request.getEmail(), token);

            // Send email (in a real app, you'd include a reset link)
            String resetLink = "http://localhost:3000/reset-password?token=" + token;
            String subject = "Password Reset Request";
            String emailBody = String.format(
                    "You have requested to reset your password.\n\n" +
                            "Use this token to reset your password: %s\n\n" +
                            "Or click this link: %s\n\n" +
                            "This token will expire in 24 hours.\n\n" +
                            "If you didn't request this, please ignore this email.",
                    token, resetLink
            );

            try {
                emailService.sendSimpleMessage(request.getEmail(), subject, emailBody);
            } catch (Exception e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }

            Map<String, String> response = new HashMap<>();
            response.put("message", "If the email exists, a password reset link has been sent.");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing password reset request");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest request) {
        try {
            // Validate token
            var validationResult = userService.validatePasswordResetToken(request.getToken());
            if (validationResult.isPresent()) {
                String error = validationResult.get();
                Map<String, String> response = new HashMap<>();
                response.put("error", error);
                return ResponseEntity.badRequest().body(response);
            }

            // Reset password
            userService.resetPassword(
                    userService.findEmailByResetToken(request.getToken()).orElse(""),
                    request.getNewPassword()
            );

            Map<String, String> response = new HashMap<>();
            response.put("message", "Password reset successfully");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error resetting password");
        }
    }
}
