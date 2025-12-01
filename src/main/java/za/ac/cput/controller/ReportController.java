package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Report;
import za.ac.cput.service.Impl.ReportServiceImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportServiceImpl reportService;

    @Autowired
    public ReportController(ReportServiceImpl reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/create")
    public ResponseEntity<Report> create(@RequestBody Report report) {
        Report created = reportService.create(report);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/read/{reportId}")
    public ResponseEntity<Report> read(@PathVariable("reportId") Integer reportId) {
        Optional<Report> reportOpt = Optional.ofNullable(reportService.read(reportId));
        return reportOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<Report> update(@RequestBody Report report) {
        Optional<Report> updatedOpt;
        try {
            updatedOpt = Optional.ofNullable(reportService.update(report));
        } catch (IllegalArgumentException e) {
            updatedOpt = Optional.empty();
        }
        return updatedOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findByName/{reportName}")
    public ResponseEntity<List<Report>> findByName(@PathVariable("reportName") String reportName) {
        List<Report> reports = reportService.findByReportName(reportName);
        return reports.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(reports);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Report>> getAll() {
        return ResponseEntity.ok(reportService.getAll());
    }

    @DeleteMapping("/delete/{reportId}")
    public ResponseEntity<Void> delete(@PathVariable("reportId") Integer reportId) {
        Optional<Report> reportOpt = Optional.ofNullable(reportService.read(reportId));
        if (reportOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reportService.deleteById(reportId);
        return ResponseEntity.noContent().build();
    }
    // In ReportController.java

// ... (existing code and imports)

    /**
     * Endpoint to generate a report and export it as a file.
     * Assuming the service method returns the file data as a byte array.
     */
    @GetMapping("/generatePdf/{reportId}")
    public ResponseEntity<byte[]> generateReportPdf(@PathVariable Integer reportId) {
        try {
            // 1. Call the service layer to get the PDF byte array
            byte[] pdfBytes = reportService.generateReportPdf(reportId);

            // 2. Configure the HTTP response for file download
            return ResponseEntity.ok()
                    // Set header to force download and suggest a filename
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report_" + reportId + ".pdf\"")
                    // Set the content type to PDF
                    .contentType(MediaType.APPLICATION_PDF)
                    // Set the file size
                    .contentLength(pdfBytes.length)
                    // Return the byte array as the response body
                    .body(pdfBytes);

        } catch (NoSuchElementException e) {
            // Report not found in DB
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            // Generic error during PDF creation/processing
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
