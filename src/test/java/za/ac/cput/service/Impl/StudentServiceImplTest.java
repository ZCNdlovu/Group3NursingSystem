package za.ac.cput.service.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Student;
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.factory.StudentFactory;
import za.ac.cput.service.IAdminService;
import za.ac.cput.service.IStudentService;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentServiceImplTest {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private StudentFactory studentFactory;
    private Student student;

    @BeforeEach
    void setup() {
        student = studentFactory.createStudent("Student", "Group3", "group3student@gmail.com", "Student@3","219001122",
                (short) 3,
                "ICT: Applications Development");
    }
    @Test
    void create() {
        studentService.create(student);
        assertNotNull(student);
        System.out.println(student);
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
    void findByStudentNumber() {
    }
}