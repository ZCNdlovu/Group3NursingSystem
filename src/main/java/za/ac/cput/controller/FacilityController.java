package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Facility;
import za.ac.cput.service.Impl.FacilityServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/facility")
public class FacilityController {
    private final FacilityServiceImpl facilityService;

    @Autowired
    public FacilityController(FacilityServiceImpl facilityService) {
        this.facilityService = facilityService;
    }

    //CREATE
    @PutMapping("/create")
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

}

