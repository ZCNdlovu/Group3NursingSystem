package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Facility;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.StaffAllocation;
import za.ac.cput.domain.StatusType;

import java.util.List;

@Repository
public interface StaffAllocationRepository extends JpaRepository<StaffAllocation, Integer> {

    // Find all allocations for a specific staff member
    List<StaffAllocation> findByStaff(Staff staff);

    // Find all allocations for a specific facility
    List<StaffAllocation> findByFacility(Facility facility);

    // Find all allocations with a specific status
    List<StaffAllocation> findByStatus(StatusType status);

    List<StaffAllocation> findByYearLevel(Integer level);
}

