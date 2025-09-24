package za.ac.cput.factory;

import za.ac.cput.domain.*;
import java.time.LocalDateTime;

public class AttendanceFactory {

    public static Attendance createAttendance(
            Student student,
            Facility facility,
            Placement placement,
            LocalDateTime checkIn,
            LocalDateTime checkOut,
            Double hours,
            Boolean isLate,
            Double log,
            StatusType status,
            Staff verifiedBy,
            LocalDateTime verifiedAt,
            String notes
    ) {
        // Simple validation
        if (student == null) throw new IllegalArgumentException("Student is required");
        if (facility == null) throw new IllegalArgumentException("Facility is required");
        if (placement == null) throw new IllegalArgumentException("Placement is required");
        if (checkIn == null) throw new IllegalArgumentException("Check-in time is required");
        if (status == null) throw new IllegalArgumentException("Status is required");

        // Hours calculation
        if (checkOut != null && hours == null) {
            hours = (double) java.time.Duration.between(checkIn, checkOut).toHours();
        }

        return new Attendance.Builder()
                .setStudent(student)
                .setFacility(facility)
                .setPlacement(placement)
                .setCheckIn(checkIn)
                .setCheckOut(checkOut)
                .setHours(hours)
                .setLate(isLate != null ? isLate : false)
                .setLog(log)
                .setStatus(status)
                .setVerifiedBy(verifiedBy)
                .setVerifiedAt(verifiedAt)
                .setNotes(notes)
                .build();
    }
}
