package za.ac.cput.factory;

import za.ac.cput.domain.Facility;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.StaffAllocation;
import za.ac.cput.domain.StatusType;

import java.time.LocalDate;

public class StaffAllocationFactory {

    public static StaffAllocation createStaffAllocation(
            Staff staff,
            Facility facility,
            Short yearLevel,
            LocalDate startDate,
            LocalDate endDate,
            StatusType status
    ) {
        if (staff == null) {
            throw new IllegalArgumentException("Staff is required");
        }
        if (facility == null) {
            throw new IllegalArgumentException("Facility is required");
        }
        if (yearLevel == null || yearLevel <= 0) {
            throw new IllegalArgumentException("Year level must be greater than 0");
        }
        if (startDate == null) {
            throw new IllegalArgumentException("Start date is required");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status is required");
        }

        return new StaffAllocation.Builder()
                .setStaff(staff)
                .setFacility(facility)
                .setYearLevel(yearLevel)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setStatus(status)
                .build();
    }
}
