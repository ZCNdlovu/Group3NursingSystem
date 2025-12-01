package za.ac.cput.service.Impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Admin;
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.service.IAdminService;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    private IAdminService administratorService;
    @Autowired
    private AdminFactory adminFactory;
    private  Admin admin;

    @BeforeEach
    void setup() {
        admin = adminFactory.createAdmin("Zama", "Ndlovu", "zamandlovu222@gmail.com", "0717456628", "Zama@50087");
    }
    @Test
    void create() {
        administratorService.create(admin);
        assertNotNull(admin);
        System.out.println(admin);
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
    void findByEmailAndPassword() {
    }
}