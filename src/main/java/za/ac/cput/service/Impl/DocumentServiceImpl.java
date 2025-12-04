package za.ac.cput.service.Impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Document;
import za.ac.cput.domain.Placement;
import za.ac.cput.domain.Report;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Staff;
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
    public DocumentServiceImpl(DocumentRepository documentRepository,
                               IFileStorageService fileStorageService,
                               ReportRepository reportRepository) {
        this.documentRepository = documentRepository;
        this.fileStorageService = fileStorageService;
        this.reportRepository = reportRepository;
    }

    @Override
    public Document create(Document document) {
        if (document.getReport() == null || document.getReport().getReportId() == null) {
            throw new IllegalArgumentException("Document must have a valid report");
        }

        Report report = reportRepository.findById(document.getReport().getReportId())
                .orElseThrow(() -> new EntityNotFoundException("Report not found"));

        Document newDocument = new Document.Builder()
                .copy(document)
                .setReport(report)
                .build();

        return documentRepository.save(newDocument);
    }

    @Override
    public Document read(String documentId) {
        try {
            Integer id = Integer.parseInt(documentId);
            return documentRepository.findById(id).orElse(null);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid document ID format");
        }
    }

    @Override
    public Optional<Document> findById(String documentId) {
        try {
            Integer id = Integer.parseInt(documentId);
            return documentRepository.findById(id);
        } catch (NumberFormatException e) {
            return Optional.empty();
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
    public void deleteById(String documentId) {
        try {
            Integer id = Integer.parseInt(documentId);
            if (documentRepository.existsById(id)) {
                documentRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("Document with ID " + documentId + " not found");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid document ID format");
        }
    }

    @Override
    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    @Override
    public Document processFileUpload(MultipartFile file, Student studentId, Placement placementId, String uploadedBy, String fileType) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot process an empty file.");
        }

        try {
            String fileUrl = fileStorageService.storeFile(file);

            String documentId = UUID.randomUUID().toString(); // Temporary ID for Builder; DB will assign Integer

            Document document = new Document.Builder()
                    .setDocumentId(null) // DB will generate Integer ID
                    .setFileName(file.getOriginalFilename())
                    .setFilePath(fileUrl)
                    .setStudent(studentId)
                    .setPlacement(placementId)
                    .setUploadedAt(LocalDateTime.now())
                    .build();

            return documentRepository.save(document);

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + file.getOriginalFilename(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process document upload: " + e.getMessage(), e);
        }
    }

    @Override
    public Document processFileUpload(MultipartFile file, String studentId, Integer placementId, String uploadedBy, String fileType) {
        // Currently not implemented; kept for interface compatibility
        return null;
    }
}
