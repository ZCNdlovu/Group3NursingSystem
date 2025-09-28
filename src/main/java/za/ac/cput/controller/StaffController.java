package za.ac.cput.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Staff;
import za.ac.cput.service.IStaffService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final IStaffService staffService;

    public StaffController(IStaffService staffService) {
        this.staffService = staffService;
    }


    @PostMapping("/create")
    public ResponseEntity<Staff> create(@RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.create(staff));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Staff> read(@PathVariable String id) {
        Staff staff = staffService.read(id);
        return staff != null ? ResponseEntity.ok(staff) : ResponseEntity.notFound().build();
    }


    @PutMapping("/update")
    public ResponseEntity<Staff> update(@RequestBody Staff staff) {
        try {
            Staff updated = staffService.update(staff);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        staffService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/all")
    public ResponseEntity<List<Staff>> getAll() {
        return ResponseEntity.ok(staffService.getAll());
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<Staff> findByEmail(@PathVariable String email) {
        Optional<Staff> staff = staffService.findByEmail(email);
        return staff.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/staffNumber/{staffNumber}")
    public ResponseEntity<Staff> findByStaffNumber(@PathVariable String staffNumber) {
        Optional<Staff> staff = staffService.findByStaffNumber(staffNumber);
        return staff.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/login")
    public ResponseEntity<List<Staff>> login(@RequestParam String email, @RequestParam String password) {
        List<Staff> staffList = staffService.findByEmailAndPassword(email, password);
        return staffList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(staffList);
    }
}
