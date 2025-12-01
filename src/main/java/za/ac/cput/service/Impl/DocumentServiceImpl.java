package za.ac.cput.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Document;
import za.ac.cput.domain.Placement;
import za.ac.cput.domain.Report;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.DocumentRepository;
import za.ac.cput.repository.ReportRepository;
import za.ac.cput.service.IDocumentService;
import za.ac.cput.service.IFileStorageService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements IDocumentService {

    private final DocumentRepository documentRepository;
    private final IFileStorageService fileStorageService;
    private final ReportRepository reportRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, IFileStorageService fileStorageService, ReportRepository reportRepository) {
        this.documentRepository = documentRepository;
        this.fileStorageService = fileStorageService;
        this.reportRepository = reportRepository;

    }

    @Override
    // Example in DocumentService (Conceptual)
    public Document create(Document document) {
        // 1. Get the reportId from the document object (or DTO)
        Integer reportId = document.getReport().getReportId();

        // 2. Fetch the existing Report entity from the database

        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Report not found"));

        // 3. Set the managed Report entity on the Document
        document = new Document.Builder()
                .setReport(report)
                .build();

        // 4. Save the Document
        return documentRepository.save(document);
    }

    @Override
    public Document read(String documentId) {
        return documentRepository.findById(documentId)
                .orElse(null);
    }
    @Override
    public Document processFileUpload(MultipartFile file, Student studentId, Placement placementId, String uploadedBy, String fileType) {

        // 1. Basic Input Validation
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot process an empty file.");
        }

        try {
            // 2. Save the file physically and get its URL/Path
            // The implementation detail of fileStorageService.storeFile is handled separately.
            // It returns the full path or URL where the file can be accessed later.
            String fileUrl = fileStorageService.storeFile(file);

            // 3. Create the Document entity
            // Note: documentId is assumed to be a UUID generated here or in the Document constructor
            String documentId = UUID.randomUUID().toString();

            Document document = new Document.Builder()
                    .setDocumentId(documentId)
                    .setFileName(file.getOriginalFilename())
                    .setFilePath(fileUrl) // Use the path returned from the storage service
                    .setStudent(studentId)
                    .setPlacement(placementId)
                    .setUploadedAt(LocalDateTime.now())
                    .build();

            // 4. Save the Document metadata to the database
            return documentRepository.save(document);

        } catch (IOException e) {
            // Handle file-related exceptions (e.g., failed to save to disk)
            System.err.println("File storage error: " + e.getMessage());
            throw new RuntimeException("Failed to store the file: " + file.getOriginalFilename(), e);
        } catch (Exception e) {
            // Catch any other exceptions during entity creation or database save
            System.err.println("Document creation error: " + e.getMessage());
            throw new RuntimeException("Failed to process document upload: " + e.getMessage(), e);
        }
    }
    @Override
    public Document update(Document document) {
        if (document.getDocumentId() == null || !documentRepository.existsById(document.getDocumentId())) {
            throw new IllegalArgumentException("Document does not exist");
        }
        return documentRepository.save(document);
    }

    @Override
    public Optional<Document> findById(String documentId) {
        return documentRepository.findById(documentId);
    }


    @Override
    public Document processFileUpload(MultipartFile file, String studentId, Integer placementId, String uploadedBy, String fileType) {
        return null;
    }

    @Override
    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    @Override
    public void deleteById(String documentId) {
        if (documentRepository.existsById(documentId)) {
            documentRepository.deleteById(documentId);

        }
    }
}
