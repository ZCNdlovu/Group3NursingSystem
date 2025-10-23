package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Report;
import za.ac.cput.service.Impl.ReportServiceImpl;

import java.util.List;
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
}
