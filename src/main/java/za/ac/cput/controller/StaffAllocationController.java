package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Facility;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.StaffAllocation;
import za.ac.cput.domain.StatusType;
import za.ac.cput.service.IStaffAllocationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff-allocations")
public class StaffAllocationController {

    private final IStaffAllocationService staffAllocationService;

    @Autowired
    public StaffAllocationController(IStaffAllocationService staffAllocationService) {
        this.staffAllocationService = staffAllocationService;
    }

    @PostMapping
    public ResponseEntity<StaffAllocation> create(@RequestBody StaffAllocation allocation) {
        return ResponseEntity.ok(staffAllocationService.create(allocation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffAllocation> read(@PathVariable Integer id) {
        StaffAllocation allocation = staffAllocationService.read(id);
        return allocation != null ? ResponseEntity.ok(allocation) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<StaffAllocation> getAll() {
        return staffAllocationService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        staffAllocationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/staff/{staffId}")
    public List<StaffAllocation> findByStaff(@PathVariable Staff staff) {
        return staffAllocationService.findByStaff(staff);
    }

    @GetMapping("/facility/{facilityId}")
    public List<StaffAllocation> findByFacility(@PathVariable Facility facility) {
        return staffAllocationService.findByFacility(facility);
    }

    @GetMapping("/status/{status}")
    public List<StaffAllocation> findByStatus(@PathVariable StatusType status) {
        return staffAllocationService.findByStatus(status);
    }
}
