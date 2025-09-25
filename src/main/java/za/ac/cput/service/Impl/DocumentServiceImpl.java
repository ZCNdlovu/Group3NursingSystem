package za.ac.cput.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Document;
import za.ac.cput.repository.DocumentRepository;
import za.ac.cput.service.IDocumentService;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements IDocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document create(Document document) {
        if (document.getUploadedAt() == null) {
            document = new Document.Builder()
                    .copy(document)
                    .setUploadedAt(java.time.LocalDate.now())
                    .build();
        }
        return documentRepository.save(document);
    }

    @Override
    public Document read(String documentId) {
        return documentRepository.findById(documentId)
                .orElse(null);
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
        return Optional.ofNullable(read(documentId));
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
