package za.ac.cput.factory;

import za.ac.cput.domain.Report;
import za.ac.cput.domain.RoleType;

import java.time.LocalDate;

public class ReportFactory{
    public static Report createReport(RoleType generatedByUser,
                                      String reportName,
                                      LocalDate generatedAt,
                                      String parametersJson,
                                      String filePath) {

        return new Report.Builder()
                .setRoleType(generatedByUser)
                .setReportName(reportName)
                .setGeneratedAt(LocalDate.now())
                .setParametersJson(parametersJson)
                .setFilePath(filePath)
                .build();
    }
}
