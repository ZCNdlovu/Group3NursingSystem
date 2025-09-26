package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Document;
import za.ac.cput.service.Impl.DocumentServiceImpl;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/document")
public class DocumentController {

    private final DocumentServiceImpl documentService;

    @Autowired
    public DocumentController(DocumentServiceImpl documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Document> create(@RequestBody Document document) {
        Document created = documentService.create(document);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/read/{documentId}")
    public ResponseEntity<Document> read(@PathVariable("documentId") String documentId) {
        Document doc = documentService.read(documentId);
        return doc != null ? ResponseEntity.ok(doc) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Document> update(@RequestBody Document document) {
        Document updated = documentService.update(document);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    //finding by ID
    @GetMapping("/find/{documentId}")
    public ResponseEntity<Optional<Document>> findById(@PathVariable("documentId") String documentId) {
        Optional<Document> doc = documentService.findById(documentId);
        return doc.isPresent() ? ResponseEntity.ok(doc) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Document>> getAll() {
        List<Document> docs = documentService.getAll();
        return ResponseEntity.ok(docs);
    }

    @DeleteMapping("/delete/{documentId}")
    public ResponseEntity<Void> delete(@PathVariable("documentId") String documentId) {
        documentService.deleteById(documentId);
        return ResponseEntity.noContent().build();
    }
}
