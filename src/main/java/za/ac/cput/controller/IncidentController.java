package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Facility;
import za.ac.cput.domain.Incident;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.Student;
import za.ac.cput.service.IIncidentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IIncidentService incidentService;

    @Autowired
    public IncidentController(IIncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    public ResponseEntity<Incident> create(@RequestBody Incident incident) {
        return ResponseEntity.ok(incidentService.create(incident));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> read(@PathVariable Integer id) {
        Incident incident = incidentService.read(id);
        return incident != null ? ResponseEntity.ok(incident) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Incident> getAll() {
        return incidentService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        incidentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public List<Incident> findByStudent(@PathVariable Student student) {
        return incidentService.findByStudent(student).stream().toList();
    }

    @GetMapping("/staff/{staffId}")
    public List<Incident> findByReportedByStaff(@PathVariable Staff staff) {
        return incidentService.findByReportedByStaff(staff);
    }

    @GetMapping("/facility/{facilityId}")
    public List<Incident> findByFacility(@PathVariable Facility facility) {
        return incidentService.findByFacility(facility);
    }
}

