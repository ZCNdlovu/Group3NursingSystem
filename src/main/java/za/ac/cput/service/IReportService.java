package za.ac.cput.service;

import za.ac.cput.domain.Report;
import java.util.*;

public interface IReportService extends IService<Report, Integer> {

    void deleteById(Integer reportId);
    List<Report> getAll();
    List<Report> findByReportName(String reportName);
}
