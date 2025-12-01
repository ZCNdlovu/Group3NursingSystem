package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@CrossOrigin(origins = "*")
public class StaffAllocationController {

    private final IStaffAllocationService staffAllocationService;

    @Autowired
    public StaffAllocationController(IStaffAllocationService staffAllocationService) {
        this.staffAllocationService = staffAllocationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StaffAllocation> create(@RequestBody StaffAllocation allocation) {
        return ResponseEntity.ok(staffAllocationService.create(allocation));
    }

    // New Update Endpoint
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StaffAllocation> update(@PathVariable Integer id, @RequestBody StaffAllocation allocation) {
        // Build the allocation object, ensuring the ID from the path is set
        StaffAllocation allocationToUpdate = new StaffAllocation.Builder()
                .copy(allocation)
                .setAllocationId(id) // Assuming StaffAllocation has a Builder
                .build();

        StaffAllocation updated = staffAllocationService.update(allocationToUpdate);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffAllocation> read(@PathVariable Integer id) {
        StaffAllocation allocation = staffAllocationService.read(id);
        return allocation != null ? ResponseEntity.ok(allocation) : ResponseEntity.notFound().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public List<StaffAllocation> getAll() {
        return staffAllocationService.getAll();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        staffAllocationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/staff-allocations/year/{level}
     * Purpose: Retrieve the mentor(s) assigned to a specific student cohort (CRITICAL).
     */
    @GetMapping("/year/{level}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF') or hasRole('STUDENT')") // Students need this to see their mentor
    public ResponseEntity<List<StaffAllocation>> findByYearLevel(@PathVariable Integer level) {
        List<StaffAllocation> allocations = staffAllocationService.findByYearLevel(level);
        return ResponseEntity.ok(allocations);
    }

    // Other finders...
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
