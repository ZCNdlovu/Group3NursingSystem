package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.*;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    List<Report> findByReportName(String reportName);
}
