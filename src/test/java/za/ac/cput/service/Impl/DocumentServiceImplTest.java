package za.ac.cput.service.Impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Document;
import za.ac.cput.domain.StatusType;
import za.ac.cput.repository.DocumentRepository;
import za.ac.cput.service.IDocumentService;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class DocumentServiceImplTest {

    @Autowired
    private IDocumentService documentService;

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    void createDocument() {
        Document doc1 = new Document.Builder()
                .setDocumentId("doc1-0101")
                .setFilePath("Documents/test.pdf")
                .setFileName("test.pdf")
                .setSizeBytes(25L)
                .setUploadedAt(LocalDateTime.now())
                .setStatus(StatusType.PENDING)
                .build();

        Document created = documentService.create(doc1);
        assertNotNull(created);
        assertEquals("doc1-0101", created.getDocumentId());
    }

    //displaying/reading the created document
    @Test
    void readDocumentById() {
        Document readDoc = documentService.read("doc1-0101");
        assertNotNull(readDoc);
        assertEquals("doc1-0101", readDoc.getDocumentId());

        //update the document
        Document updatedDoc = new Document.Builder()
                .copy(readDoc)
                .setFileName("test-updated.pdf")
                .setSizeBytes(38L)
                .build();

        Document updated = documentService.update(updatedDoc);
        assertEquals("test-updated.pdf", updated.getFileName());
        assertEquals(38L, updated.getSizeBytes());
    }

    @Test
    void findDocumentById() {
        Optional<Document> optionalDoc = documentService.findById("doc1-0101");
        assertTrue(optionalDoc.isPresent());
    }

    @Test
    void findAllDocument() {
        Document doc = new Document.Builder()
                .setDocumentId("doc1-0101")
                .setFilePath("Documents/test.pdf")
                .setFileName("test2.pdf")
                .setSizeBytes(25L)
                .setUploadedAt(LocalDateTime.now())
                .setStatus(StatusType.PENDING)
                .build();

        //save it first
        documentService.create(doc);

        List<Document> allDocs = documentService.getAll();
        assertTrue(allDocs.size() > 0);
    }

    @Test
    void deleteDocumentById() {
        documentService.deleteById("doc1-0101");
        assertNull(documentService.read("doc1-0101"));
    }
}
