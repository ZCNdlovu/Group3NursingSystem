package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Facility;
import za.ac.cput.service.Impl.FacilityServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/facility")
@CrossOrigin(origins = "*")
public class FacilityController {
    private final FacilityServiceImpl facilityService;

    @Autowired
    public FacilityController(FacilityServiceImpl facilityService) {
        this.facilityService = facilityService;
    }

    //CREATE
    @PutMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Facility create(@RequestBody Facility facility) {
        return facilityService.create(facility);
    }

    //READ BY ID
    @GetMapping("/read/{id}")
    public Facility read(@PathVariable Integer id) {
        return facilityService.read(id);
    }

    //UPDATE
    @PutMapping("/update")
    public Facility update(@RequestBody Facility facility) {
        return facilityService.update(facility);
    }

    //GET ALL
    @GetMapping("/getAll")
    public List<Facility> getAll() {
        return facilityService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')") // <-- ADD THIS SECURITY
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        // Assuming the service layer returns true/false or handles the deletion
        facilityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

