package factoryTest;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Report;
import za.ac.cput.domain.RoleType;
import za.ac.cput.factory.ReportFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReportFactoryTest {

    @Test
    void createReport() {
        Report report = ReportFactory.createReport(
                RoleType.ADMIN,
                "Monthly Performance",
                LocalDate.now(),
                "{\"month\": \"September\", \"year\": 2025}",
                "Documents/reports/septRepo.pdf");

        assertNotNull(report);
        System.out.println(report.toString());
    }
}
