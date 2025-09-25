package za.ac.cput.service.Impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.repository.ReportRepository;
import za.ac.cput.service.IReportService;
import java.time.LocalDate;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class ReportServiceImplTest {

    @Autowired
    private IReportService reportService;

    @Autowired
    private ReportRepository reportRepository;

    @Test
    void createReport() {
        Report report = new Report.Builder()
                .setReportName("Monthly Attendance")
                .setRoleType(RoleType.ADMIN)
                .setGeneratedAt(LocalDate.now())
                .setParametersJson("{\"month\": \"September\", \"year\": 2025}")
                .setFilePath("Documents/reports/sept.pdf")
                .build();

        Report created = reportService.create(report);
        assertNotNull(created);
        assertNotNull(created.getReportId());
        assertEquals("Monthly Attendance", created.getReportName());


    }

    @Test
    void readReport() {
        Report created = reportRepository.findAll().get(0);
        Report readReport = reportService.read(created.getReportId());
        assertNotNull(readReport);
        assertEquals(created.getReportName(), readReport.getReportName());

        //update the report
        Report updatedReport = new Report.Builder()
                .copy(readReport)
                .setReportName("Monthly Attendance - Updated")
                .build();
        Report updated = reportService.update(updatedReport);
        assertEquals("Monthly Attendance - Updated", updated.getReportName());
    }

    @Test
    void updateReportById() {
        Report created = reportRepository.findAll().get(0);
        Optional<Report> optionalReport = Optional.ofNullable(reportService.read(created.getReportId()));
        assertTrue(optionalReport.isPresent());
    }
    @Test
    void findReportByName() {
        Report report = new Report.Builder()
                .setReportName("Special Report")
                .setRoleType(RoleType.ADMIN)
                .setGeneratedAt(LocalDate.now())
                .setParametersJson("{\"info\": \"test\"}")
                .setFilePath("Documents/reports/special.pdf")
                .build();

        reportService.create(report);

        List<Report> reports = reportService.findByReportName("Special Report");
        assertFalse(reports.isEmpty());
        assertEquals("Special Report", reports.get(0).getReportName());
    }

    @Test
    void findAllReports() {
        Report report = new Report.Builder()
                .setReportName("Quarterly Report")
                .setRoleType(RoleType.ADMIN)
                .setGeneratedAt(LocalDate.now())
                .setParametersJson("{\"quarter\": \"Q3\", \"year\": 2025}")
                .setFilePath("Documents/reports/q3.pdf")
                .build();

        reportService.create(report);

        List<Report> allReports = reportService.getAll();
        assertTrue(allReports.size() > 0);
    }


    @Test
    void deleteReportById() {
        Report created = reportRepository.findAll().get(0);
        reportService.deleteById(created.getReportId());
        assertNull(reportService.read(created.getReportId()));
    }
}
