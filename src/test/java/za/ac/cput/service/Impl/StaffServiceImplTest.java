package za.ac.cput.service.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.Student;
import za.ac.cput.factory.StaffFactory;
import za.ac.cput.factory.StudentFactory;
import za.ac.cput.service.IStaffService;
import za.ac.cput.service.IStudentService;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StaffServiceImplTest {
    @Autowired
    private IStaffService staffService;
    @Autowired
    private StaffFactory staffFactory;
    private Staff staff;

    @BeforeEach
    void setup() {
        staff = staffFactory.createStaff("Staff", "Group3", "group3staff@gmail.com", "2025","Staff@3", true );
    }
    @Test
    void create() {
        staffService.create(staff);
        assertNotNull(staff);
        System.out.println(staff);
    }

    @Test
    void read() {
    }

    @Test
    void update() {
    }

    @Test
    void getAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findByStaffNumber() {
    }
}