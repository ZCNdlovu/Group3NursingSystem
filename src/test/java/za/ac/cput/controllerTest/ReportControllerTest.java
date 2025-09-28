package za.ac.cput.controllerTest;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Report;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReportControllerTest {

    private static Integer createdReportId;
    private static final String BASE_URL = "/api/report";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void a_createReport() {
        Report report = new Report.Builder()
                .setReportName("Midterm Report")
                .setGeneratedAt(LocalDate.now())
                .build();

        ResponseEntity<Report> response = restTemplate.postForEntity(
                BASE_URL + "/create", report, Report.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        createdReportId = response.getBody().getReportId();
        assertNotNull(createdReportId);
    }

    @Test
    @Order(2)
    void b_readReport() {
        ResponseEntity<Report> response = restTemplate.getForEntity(
                BASE_URL + "/read/{reportId}", Report.class, createdReportId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdReportId, response.getBody().getReportId());
    }

    @Test
    @Order(3)
    void c_updateReport() {
        Report updatedReport = new Report.Builder()
                .setReportId(createdReportId)
                .setReportName("Updated Midterm Report")
                .setGeneratedAt(LocalDate.now())
                .build();

        HttpEntity<Report> request = new HttpEntity<>(updatedReport);
        ResponseEntity<Report> response = restTemplate.exchange(
                BASE_URL + "/update", HttpMethod.PUT, request, Report.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Midterm Report", response.getBody().getReportName());
    }

    //finding report by Name
    @Test
    @Order(4)
    void d_findByName() {
        ResponseEntity<Report[]> response = restTemplate.getForEntity(
                BASE_URL + "/findByName/{reportName}", Report[].class, "Updated Midterm Report");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    @Order(5)
    void e_getAllReports() {
        ResponseEntity<Report[]> response = restTemplate.getForEntity(
                BASE_URL + "/all", Report[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    @Order(6)
    void f_deleteReport() {
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                BASE_URL + "/delete/{reportId}", HttpMethod.DELETE, null, Void.class, createdReportId);
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

        ResponseEntity<Void> notFoundResponse = restTemplate.exchange(
                BASE_URL + "/delete/{reportId}", HttpMethod.DELETE, null, Void.class, 999);
        assertEquals(HttpStatus.NOT_FOUND, notFoundResponse.getStatusCode());
    }
}
