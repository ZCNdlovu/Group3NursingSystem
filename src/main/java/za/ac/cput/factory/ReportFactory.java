package za.ac.cput.factory;

import za.ac.cput.domain.Document;
import za.ac.cput.domain.Report;
import za.ac.cput.domain.RoleType;

import java.time.LocalDate;
import java.util.Set;

public class ReportFactory{
    public static Report createReport(RoleType generatedByUser,
                                      String reportName,
                                      LocalDate generatedAt,
                                      String parametersJson,
                                      String filePath,
                                      Set<Document> documents
    ) {

        return new Report.Builder()
                .setRoleType(generatedByUser)
                .setReportName(reportName)
                .setGeneratedAt(LocalDate.now())
                .setParametersJson(parametersJson)
                .setFilePath(filePath)
                .setDocuments(documents)
                .build();
    }
}
