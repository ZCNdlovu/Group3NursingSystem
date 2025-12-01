package za.ac.cput.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Facility;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.StaffAllocation;
import za.ac.cput.domain.StatusType;
import za.ac.cput.repository.StaffAllocationRepository;
import za.ac.cput.service.IStaffAllocationService;

import java.util.List;

@Service
public class StaffAllocationServiceImpl implements IStaffAllocationService {

    private final StaffAllocationRepository repository;

    @Autowired
    public StaffAllocationServiceImpl(StaffAllocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public StaffAllocation create(StaffAllocation staffAllocation) {
        return repository.save(staffAllocation);
    }

    @Override
    public StaffAllocation read(Integer id) {
        return repository.findById(id).orElse(null);   // ✅ returns entity, null if not found
    }

    @Override
    public StaffAllocation update(StaffAllocation staffAllocation) {
        if (staffAllocation.getAllocationId() == null) {
            throw new IllegalArgumentException("Cannot update StaffAllocation without ID");
        }
        if (repository.existsById(staffAllocation.getAllocationId())) {
            return repository.save(staffAllocation);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<StaffAllocation> findByYearLevel(Integer level) {
        return repository.findByYearLevel(level);
    }

    @Override
    public List<StaffAllocation> getAll() {
        return repository.findAll();
    }

    @Override
    public List<StaffAllocation> findByStaff(Staff staff) {
        return repository.findByStaff(staff);
    }

    @Override
    public List<StaffAllocation> findByFacility(Facility facility) {
        return repository.findByFacility(facility);
    }

    @Override
    public List<StaffAllocation> findByStatus(StatusType status) {
        return repository.findByStatus(status);
    }
}
