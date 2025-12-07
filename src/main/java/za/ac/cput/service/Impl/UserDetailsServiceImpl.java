package za.ac.cput.service.Impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.Student;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentServiceImpl studentService;
    private final StaffServiceImpl staffService;
    private final AdminServiceImpl adminService;

    public UserDetailsServiceImpl(StudentServiceImpl studentService,
                                  StaffServiceImpl staffService,
                                  AdminServiceImpl adminService) {
        this.studentService = studentService;
        this.staffService = staffService;
        this.adminService = adminService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Loading user by email: " + email);

        // Check Student
        Optional<Student> student = studentService.findByEmail(email);
        if (student.isPresent()) {
            System.out.println("Found student: " + student.get().getEmail());
            return buildUserDetails(student.get().getEmail(), student.get().getPassword(), "ROLE_STUDENT");
        }

        // Check Staff - FIXED: Use the Optional directly
        Optional<Staff> staff = staffService.findByEmail(email);
        if (staff.isPresent()) {
            System.out.println("Found staff: " + staff.get().getEmail());
            return buildUserDetails(staff.get().getEmail(), staff.get().getPassword(), "ROLE_STAFF");
        }

        // Check Admin - FIXED: Use the Optional directly
        Optional<Admin> admin = adminService.findByEmail(email);
        if (admin.isPresent()) {
            System.out.println("Found admin: " + admin.get().getEmail());
            System.out.println("Encoded password in DB: " + admin.get().getPassword());
            return buildUserDetails(admin.get().getEmail(), admin.get().getPassword(), "ROLE_ADMIN");
        }

        System.out.println("User not found with email: " + email);
        throw new UsernameNotFoundException("User not found with email: " + email);
    }

    private UserDetails buildUserDetails(String email, String password, String role) {
        System.out.println("Building user details for: " + email + " with role: " + role);
        return new User(
                email,
                password,
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }
}
