package za.ac.cput.service;

import com.itextpdf.text.Paragraph;
import za.ac.cput.domain.Document;
import za.ac.cput.domain.Report;
import java.util.*;

public interface IReportService extends IService<Report, Integer> {

    void deleteById(Integer reportId);
    List<Report> getAll();

    byte[] generatePlacementReport();

    List<Report> findByReportName(String reportName);
    //Optional<Report> findById(Integer reportId);
    byte[] generateReportPdf(Integer reportId);
    // void add(Paragraph title);

}
