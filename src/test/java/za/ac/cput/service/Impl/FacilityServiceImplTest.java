package za.ac.cput.service.Impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Facility;
import za.ac.cput.factory.FacilityFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class FacilityServiceImplTest {

    private FacilityServiceImpl facilityService;
    private static Facility facility1;
    private static Facility facility2;
    private static Facility facility3;

    @Autowired
    public FacilityServiceImplTest(FacilityServiceImpl facilityService) {this.facilityService = facilityService;}

    @BeforeAll
    static void setUp(){

        facility1 = FacilityFactory.createFacility("CPUT Health Center","Healthcare","125 Long Street,Cape Town",
                "Nurse Smith","0217434567",22.1,22.6);

        facility2 = FacilityFactory.createFacility("UWC Health Center","Healthcare","132 RobertRd,Cape Town",
                "Nurse Mitchelle","0219800718",33.1,18.6);

        facility3 = FacilityFactory.createFacility("Mowbray Health Center","Healthcare","174 KromboomRd,Cape Town",
                "Nurse Mike ","0216965133",40.1,18.6);

    }

    @Test
    @Order(1)
    void create() {

        Facility savedFacility = facilityService.create(facility1);
        assertNotNull(savedFacility);
        System.out.println(savedFacility);

        Facility savedFacility2 = facilityService.create(facility2);
        assertNotNull(savedFacility2);
        System.out.println(savedFacility2);

        Facility savedFacility3 = facilityService.create(facility3);
        assertNotNull(savedFacility3);
        System.out.println(savedFacility3);
    }

    @Test
    @Order(2)
    void read() {
        Facility readFacility = facilityService.read(1);
        assertNotNull(readFacility);
        System.out.println(readFacility);
    }

    @Test
    @Order(3)
    void update() {
        Facility facilityToUpdate = new Facility.Builder().copy(facility2).setContactPerson("Nurse James").build();

        //from Database
        Facility updatedFacility = facilityService.update(facilityToUpdate);
        assertNotNull(updatedFacility);
        System.out.println(updatedFacility);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Facility> allFacility = facilityService.getAll();
        assertNotNull(allFacility);
        System.out.println(allFacility);
    }

    @Test
    void findByName() {
    }

    @Test
    void findByNameAndAddress() {
    }
}