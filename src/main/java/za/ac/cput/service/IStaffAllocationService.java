package za.ac.cput.service;



import za.ac.cput.domain.Facility;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.StaffAllocation;
import za.ac.cput.domain.StatusType;

import java.util.List;

public interface IStaffAllocationService extends IService<StaffAllocation, Integer> {
    List<StaffAllocation> findByStaff(Staff staff);
    List<StaffAllocation> findByFacility(Facility facility);
    List<StaffAllocation> findByStatus(StatusType status);

    @Override
    StaffAllocation read(Integer id);

    void delete(Integer id);

    List<StaffAllocation> findByYearLevel(Integer level);
}



