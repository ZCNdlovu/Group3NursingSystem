package za.ac.cput.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.RoleType;
import za.ac.cput.repository.AdminRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create default admin if none exists
        if (adminRepository.count() == 0) {
            Admin admin = new Admin.Builder()
                    .setAdminId("AD0001")
                    .setFirstName("Zama")
                    .setLastName("Ndlovu")
                    .setEmail("zamandlovu222@gmail.com")
                    .setPhone("0717456628")
                    .setPassword(passwordEncoder.encode("Zama@50087"))
                    .setRole(RoleType.ADMIN)
                    .build();

            adminRepository.save(admin);
            System.out.println("Default admin created: zamandlovu222@gmail.com / Zama@50087");
        }
    }
}
