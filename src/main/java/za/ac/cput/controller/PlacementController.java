package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Placement;
import za.ac.cput.service.Impl.PlacementServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/placement")
@CrossOrigin(origins = "*")
public class PlacementController {

    private final PlacementServiceImpl placementService;

    @Autowired
    public PlacementController(PlacementServiceImpl placementService) {
        this.placementService = placementService;
    }

    //CREATE
    @PostMapping("/create")
    public Placement create(@RequestBody Placement placement) {
        return placementService.create(placement);
    }

    //READ BY ID
    @GetMapping("/read/{id}")
    public Placement read(@PathVariable Integer id) {
        return placementService.read(id);
    }

    //UPDATE
    @PutMapping("/update")
    public Placement update(@RequestBody Placement placement) {
        return placementService.update(placement);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        placementService.delete(id);
        // Returns 204 No Content on successful deletion
        return ResponseEntity.noContent().build();
    }
    //GET ALL
    @GetMapping("/getAll")
    public List<Placement> getAll() {
        return placementService.getAll();
    }

}

