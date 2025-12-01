package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.RoleType;
import za.ac.cput.domain.dto.StudentRegistrationRequest;
import za.ac.cput.service.EmailService;
import za.ac.cput.service.IStudentService;
import za.ac.cput.service.Impl.EmailServiceImpl;
import za.ac.cput.service.Impl.StudentServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationRequest request) {
        try {
            // Generate temporary password
            String tempPassword = UUID.randomUUID().toString().substring(0, 8);
            String encodedPassword = passwordEncoder.encode(tempPassword);

            // Create student
            Student student = new Student.Builder()
                    .setEmail(request.getEmail())
                    .setFirstName(request.getFirstName())
                    .setLastName(request.getLastName())
                    .setPhone(request.getPhone())
                    .setStudentNumber(request.getStudentNumber())
                    .setYearLevel(request.getYearLevel())
                    .setProgram(request.getProgram())
                    .setPassword(encodedPassword)
                    .build();
            student.setRole(RoleType.STUDENT);

            Student savedStudent = studentService.create(student);

            // Send email notification
            String subject = "Welcome to Nursing System - Your Student Account";
            String emailBody = String.format(
                    "Dear %s %s,\n\n" +
                            "Your student account has been created.\n" +
                            "Login details:\n" +
                            "Email: %s\n" +
                            "Temporary Password: %s\n\n" +
                            "Please change your password after first login.\n\n" +
                            "Best regards,\nNursing System Admin",
                    request.getFirstName(), request.getLastName(), request.getEmail(), tempPassword
            );

            try {
                emailService.sendSimpleMessage(request.getEmail(), subject, emailBody);
            } catch (Exception e) {
                System.err.println("Failed to send email: " + e.getMessage());
                // Continue anyway - the user was created successfully
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating student: " + e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF') or hasRole('STUDENT')")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Student student = studentService.read(id);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF') or (hasRole('STUDENT') and #id == authentication.principal.username)")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student) {
        student = new Student.Builder().copy(student).setStudentId(id).build();
        Student updated = studentService.update(student);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    // --- New Endpoints based on ServiceImpl methods ---

    /**
     * GET /api/student/email/{email}
     * Purpose: Find a student by their email address.
     */
    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Student> findByEmail(@PathVariable String email) {
        return studentService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /api/student/student-number/{studentNumber}
     * Purpose: Find a student by their unique student number.
     */
    @GetMapping("/student-number/{studentNumber}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Student> findByStudentNumber(@PathVariable String studentNumber) {
        return studentService.findByStudentNumber(studentNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
