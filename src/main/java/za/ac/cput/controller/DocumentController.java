package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Document> update(@RequestBody Document document) {
        Document updated = documentService.update(document);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    // In DocumentController.java

// ... (existing code)

    // Recommended new endpoint for file and metadata upload
    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF') or hasRole('STUDENT')") // All roles can upload, permissions defined by parameters
    public ResponseEntity<Document> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("studentId") String studentId,
            @RequestParam("placementId") Integer placementId,
            @RequestParam("uploadedBy") String uploadedBy,
            @RequestParam("fileType") String fileType) {

        try {
            // Service handles saving the actual file and creating the Document entity
            Document created = documentService.processFileUpload(file, studentId, placementId, uploadedBy, fileType);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            System.err.println("File upload failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //finding by ID
    @GetMapping("/find/{documentId}")
    public ResponseEntity<Optional<Document>> findById(@PathVariable("documentId") String documentId) {
        Optional<Document> doc = documentService.findById(documentId);
        return doc.isPresent() ? ResponseEntity.ok(doc) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<List<Document>> getAll() {
        List<Document> docs = documentService.getAll();
        return ResponseEntity.ok(docs);
    }

    @DeleteMapping("/delete/{documentId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Void> delete(@PathVariable("documentId") String documentId) {
        documentService.deleteById(documentId);
        return ResponseEntity.noContent().build();
    }
}
