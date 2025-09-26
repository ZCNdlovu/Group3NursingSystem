package za.ac.cput.controllerTest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Document;
import za.ac.cput.domain.StatusType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DocumentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/api/document";

    private static final String DOC_ID = "doc1-0101";

    @Test
    @Order(1)
    void a_createDocument() {
        Document doc = new Document.Builder()
                .setDocumentId(DOC_ID)
                .setFileName("test.pdf")
                .setFilePath("Documents/test.pdf")
                .setUploadedAt(LocalDate.now())
                .setStatus(StatusType.PENDING)
                .build();

        ResponseEntity<Document> response = restTemplate.postForEntity(BASE_URL + "/create", doc, Document.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(DOC_ID, response.getBody().getDocumentId());
    }

    @Test
    @Order(2)
    void b_readDocument() {
        ResponseEntity<Document> response = restTemplate.getForEntity(BASE_URL + "/read/" + DOC_ID, Document.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(DOC_ID, response.getBody().getDocumentId());
    }

    @Test
    @Order(3)
    void d_updateDocument() {
        Document updatedDoc = new Document.Builder()
                .setDocumentId(DOC_ID)
                .setFileName("test-updated.pdf")
                .setFilePath("Documents/test-updated.pdf")
                .setUploadedAt(LocalDate.now())
                .setStatus(StatusType.APPROVED)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Document> request = new HttpEntity<>(updatedDoc, headers);

        ResponseEntity<Document> response = restTemplate.exchange(BASE_URL + "/update", HttpMethod.PUT, request, Document.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("test-updated.pdf", response.getBody().getFileName());
        assertEquals(StatusType.APPROVED, response.getBody().getStatus());
    }

    @Test
    @Order(4)
    void c_findDocumentById() {
        ResponseEntity<Optional> response = restTemplate.getForEntity(BASE_URL + "/find/" + DOC_ID, Optional.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }



    @Test
    @Order(5)
    void e_getAllDocuments() {
        ResponseEntity<List> response = restTemplate.getForEntity(BASE_URL + "/all", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    @Order(6)
    void f_deleteDocument() {
        restTemplate.delete(BASE_URL + "/delete/" + DOC_ID);

        ResponseEntity<Document> response = restTemplate.getForEntity(BASE_URL + "/read/" + DOC_ID, Document.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
