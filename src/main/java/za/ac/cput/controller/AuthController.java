package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.*;
import za.ac.cput.domain.dto.LoginRequest;
import za.ac.cput.service.Impl.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final StudentServiceImpl studentService;
    private final StaffServiceImpl staffService;
    private final AdminServiceImpl adminService;

    @Autowired
    public AuthController(StudentServiceImpl studentService, StaffServiceImpl staffService, AdminServiceImpl adminService) {
        this.studentService = studentService;
        this.staffService = staffService;
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and password are required");
        }

        try {
            List<Student> students = studentService.findByEmailAndPassword(email, password);
            if (!students.isEmpty()) {
                Student user = students.get(0);
                return buildResponse(user.getStudentId(), user.getFirstName(), user.getLastName(), user.getEmail(), RoleType.STUDENT.toString());
            }

            List<Staff> staffMembers = staffService.findByEmailAndPassword(email, password);
            if (!staffMembers.isEmpty()) {
                Staff user = staffMembers.get(0);
                return buildResponse(user.getStaffId(), user.getFirstName(), user.getLastName(), user.getEmail(), RoleType.STAFF.toString());
            }

            List<Admin> admins = adminService.findByEmailAndPassword(email, password);
            if (!admins.isEmpty()) {
                Admin user = admins.get(0);
                return buildResponse(user.getAdminId(), user.getFirstName(), user.getLastName(), user.getEmail(), RoleType.ADMIN.toString());
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login.");
        }
    }

    private ResponseEntity<?> buildResponse(String id, String firstName, String lastName, String email, String role) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Login successful");
        response.put("role", role);

        Map<String, Object> userData = new HashMap<>();
        userData.put("id", id);
        userData.put("firstName", firstName);
        userData.put("lastName", lastName);
        userData.put("email", email);
        userData.put("role", role);

        response.put("data", userData);
        return ResponseEntity.ok(response);
    }
}
