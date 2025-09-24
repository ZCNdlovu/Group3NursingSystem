package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceFactoryTest {

    @Test
    void testCreateAttendanceSuccess() {
        Student student = new Student.Builder()
                .setStudentId("stu-001")
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@example.com")
                .build();

        Facility facility = new Facility.Builder()
                .setFacilityId(1)
                .setName("Health Clinic")
                .build();

        Placement placement = new Placement.Builder()
                .setPlacementId(1)
                .build();

        Staff staff = new Staff.Builder()
                .setStaffId("staff-001")
                .setFirstName("Alice")
                .setLastName("Smith")
                .setEmail("alice.smith@example.com")
                .setClinical(true)
                .build();

        Attendance attendance = AttendanceFactory.createAttendance(
                student,
                facility,
                placement,
                LocalDateTime.now(),
                null,
                8.0,
                false,
                12.345678,
                StatusType.APPROVED,
                staff,
                LocalDateTime.now(),
                "Good attendance"
        );

        assertNotNull(attendance);
        assertEquals(student, attendance.getStudent());
        assertEquals(facility, attendance.getFacility());
        assertEquals(placement, attendance.getPlacement());
        assertEquals(staff, attendance.getVerifiedBy());
        assertEquals(StatusType.APPROVED, attendance.getStatus());
    }

    @Test
    void testCreateAttendanceMissingStudent() {
        Facility facility = new Facility.Builder()
                .setFacilityId(1)
                .setName("Health Clinic")
                .build();

        Placement placement = new Placement.Builder()
                .setPlacementId(1)
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                AttendanceFactory.createAttendance(
                        null,
                        facility,
                        placement,
                        LocalDateTime.now(),
                        null,
                        8.0,
                        false,
                        12.345678,
                        StatusType.PENDING,
                        null,
                        null,
                        "Missing student"
                )
        );

        assertEquals("Student is required", exception.getMessage());
    }
}
