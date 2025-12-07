package za.ac.cput.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.RoleType;
import za.ac.cput.repository.AdminRepository;
import za.ac.cput.repository.StudentRepository;
import za.ac.cput.repository.StaffRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (adminRepository.count() == 0) {
            // Create first admin
            Admin admin1 = new Admin.Builder()
                    .setAdminId("AD0001")
                    .setFirstName("Zama")
                    .setLastName("Ndlovu")
                    .setEmail("zamandlovu222@gmail.com")
                    .setPhone("0717456628")
                    .setPassword(passwordEncoder.encode("Zama@50087"))
                    .setRole(RoleType.ADMIN)
                    .build();
            adminRepository.save(admin1);
            System.out.println("Default admin created: zamandlovu222@gmail.com / Zama@50087");

            // Create second admin
            Admin admin2 = new Admin.Builder()
                    .setAdminId("AD0002")
                    .setFirstName("John")
                    .setLastName("Doe")
                    .setEmail("john.doe@cput.ac.za")
                    .setPhone("0723456789")
                    .setPassword(passwordEncoder.encode("John@50087"))
                    .setRole(RoleType.ADMIN)
                    .build();
            adminRepository.save(admin2);
            System.out.println("Second admin created: john.doe@cput.ac.za / John@50087");
        }


        // --- Create default student ---
            if (studentRepository.count() == 0) {
                Student student = new Student.Builder()
                        .setStudentId("STU0001")
                        .setFirstName("Lindiwe")
                        .setLastName("Mokoena")
                        .setEmail("lindiwe@cput.ac.za")
                        .setPhone("0723000001")
                        .setPassword(passwordEncoder.encode("Student@50087"))
                        .setStudentNumber("STU001")
                        .setYearLevel((short) 1)
                        .setProgram("Nursing")
                        .setRole(RoleType.STUDENT)
                        .build();
                studentRepository.save(student);
                System.out.println("Default student created: lindiwe@cput.ac.za / Student@50087");
            }

            // --- Create default staff ---
            if (staffRepository.count() == 0) {
                Staff staff = new Staff.Builder()
                        .setStaffId("ST0001")
                        .setFirstName("Alice")
                        .setLastName("Mkhize")
                        .setEmail("alice@cput.ac.za")
                        .setPhone("0712000001")
                        .setPassword(passwordEncoder.encode("Staff@50087"))
                        .setStaffNumber("S001")
                        .setClinical(true)
                        .setRole(RoleType.STAFF)
                        .build();
                staffRepository.save(staff);
                System.out.println("Default staff created: alice@cput.ac.za / Staff@50087");
            }
        }
    }

