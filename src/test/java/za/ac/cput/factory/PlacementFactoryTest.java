package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlacementFactoryTest {

    @Test
    void testCreatePlacementWithAllFields() {
        Student student = new Student.Builder()
                .setFirstName("Smith")
                .setLastName("Job")
                .build();

        Facility facility = new Facility.Builder()
                .setName("CPUT Health Center")
                .setType("Healthcare")
                .setAddress("124 Long Street, Cape Town")
                .setContactPerson("Nurse Smith")
                .setContactPhone("021245367")
                .setLatitude(22.1)
                .setLongitude(22.5)
                .build();

Staff staff = new Staff.Builder()
        .setStaffId("SR002")
        .setFirstName("Lebo")
        .setLastName("Johnson")
        .build();

LocalDate startDate = LocalDate.of(2025, 10, 1);
LocalDate endDate = LocalDate.of(2025, 12, 30);

Placement placement = PlacementFactory.createPlacement(
        student,
        facility,
        startDate,
        endDate,
        staff,
        StatusType.APPROVED
);

assertNotNull(placement);
        System.out.println(placement);


    }

    @Test
    void testCreatePlacementWithTwoFields() {
        Student student = new Student.Builder()
                .setStudentId("W004")
                .setFirstName("Smith")
                .setLastName("Job")
                .build();

        Facility facility = new Facility.Builder()
                .setName("Community Clinic")
                .setType("Healthcare")
                .setAddress("655 Hello Street, Cape Town")
                .setContactPerson("Mr Albert")
                .setContactPhone("0217654321")
                .setLatitude(25.1)
                .setLongitude(19.5)
                .build();

        Placement placement = PlacementFactory.createPlacement(
                student,
                facility
        );


assertNotNull(placement);
        System.out.println(placement);
    }
}
