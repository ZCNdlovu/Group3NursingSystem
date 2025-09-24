package za.ac.cput.factory;

import za.ac.cput.domain.*;

import java.time.LocalDate;


public class PlacementFactory {

    public static Placement createPlacement(Student student, Facility facility, LocalDate startDate, LocalDate endDate, Staff allocatedByStaff, StatusType status) {

        return new Placement.Builder()
                .setStudent(student)
                .setFacility(facility)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setAllocatedByStaff(allocatedByStaff)
                .setStatus(status)
                .build();
    }

    //Optional Create placement with only Student, Facility//
    public static Placement createPlacement(Student student, Facility facility) {

        return new Placement.Builder()
                .setStudent(student)
                .setFacility(facility)
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusMonths(4))
                .setStatus(StatusType.IN_PROGRESS).build();
    }
}
