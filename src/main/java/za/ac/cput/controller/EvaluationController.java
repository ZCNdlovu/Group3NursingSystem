package za.ac.cput.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Evaluation;
import za.ac.cput.service.IEvaluationService;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@CrossOrigin(origins = "*")
public class EvaluationController {

    private final IEvaluationService service;

    public EvaluationController(IEvaluationService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Evaluation> create(@RequestBody Evaluation evaluation) {
        Evaluation saved = service.save(evaluation);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // New Update Endpoint with Security
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Evaluation> update(@PathVariable Integer id, @RequestBody Evaluation evaluation) {
        // Build the evaluation object, ensuring the ID from the path is set
        Evaluation evaluationToUpdate = new Evaluation.Builder()
                .copy(evaluation)
                .setEvaluationId(id) // Assuming Evaluation has a Builder
                .build();

        Evaluation updated = service.update(evaluationToUpdate);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<List<Evaluation>> getAll() {
        List<Evaluation> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Evaluation> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

