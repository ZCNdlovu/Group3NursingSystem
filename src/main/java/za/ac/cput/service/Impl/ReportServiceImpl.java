package za.ac.cput.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Report;
import za.ac.cput.repository.ReportRepository;
import za.ac.cput.service.IReportService;
import java.util.*;

@Service
public class ReportServiceImpl implements IReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report create(Report report) {
        if (report.getGeneratedAt() == null) {
            report = new Report.Builder()
                    .copy(report)
                    .setGeneratedAt(java.time.LocalDate.now())
                    .build();
        }
        return reportRepository.save(report);
    }

    @Override
    public Report read(Integer reportId) {
        return reportRepository.findById(reportId)
                .orElse(null);
    }

    @Override
    public Report update(Report report) {
        if (report.getReportId() == null || !reportRepository.existsById(report.getReportId())) {
            throw new IllegalArgumentException("Report does not exist");
        }
        return reportRepository.save(report);
    }

    @Override
    public List<Report> findByReportName(String reportName) {
        return reportRepository.findByReportName(reportName);
    }

    @Override
    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    @Override
    public void deleteById(Integer reportId) {
        if (reportRepository.existsById(reportId)) {
            reportRepository.deleteById(reportId);
        }
    }
}
