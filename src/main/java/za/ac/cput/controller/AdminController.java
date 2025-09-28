package za.ac.cput.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.service.IAdminService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final IAdminService adminService;

    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/create")
    public ResponseEntity<Admin> create(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.create(admin));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Admin> read(@PathVariable String id) {
        Admin admin = adminService.read(id);
        return admin != null ? ResponseEntity.ok(admin) : ResponseEntity.notFound().build();
    }


    @PutMapping("/update")
    public ResponseEntity<Admin> update(@RequestBody Admin admin) {
        try {
            Admin updated = adminService.update(admin);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        adminService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAll() {
        return ResponseEntity.ok(adminService.getAll());
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<Admin> findByEmail(@PathVariable String email) {
        Optional<Admin> admin = adminService.findByEmail(email);
        return admin.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/login")
    public ResponseEntity<List<Admin>> login(@RequestParam String email, @RequestParam String password) {
        List<Admin> admins = adminService.findByEmailAndPassword(email, password);
        return admins.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(admins);
    }
}
