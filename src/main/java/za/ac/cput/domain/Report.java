package za.ac.cput.domain;

import jakarta.persistence.*;
import org.hibernate.usertype.UserType;

import java.time.LocalDate;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id_INT")
    private Integer reportId;

    @Enumerated(EnumType.STRING)
    @Column(name = "generatedByUser_VARCHAR(20)")
    private RoleType generatedByUser; // Need to be changed and make Student,Staff and Admin

    @Column(name = "report_name_VARCHAR(255)")
    private String reportName;

    @Column(name = "generated_at_TIMESTAMP")
    private LocalDate generatedAt;

    @Column(name = "parameters_JSON")
    private String parametersJson;

    @Column(name = "file_path_TEXT")
    private String filePath;

    protected Report() {}

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", generatedByUser=" + generatedByUser +
                ", reportName='" + reportName + '\'' +
                ", generatedAt=" + generatedAt +
                ", parametersJson='" + parametersJson + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    private Report(Builder builder) {
        this.reportId = builder.reportId;
        this.generatedByUser = builder.generatedByUser;
        this.reportName = builder.reportName;
        this.generatedAt = builder.generatedAt;
        this.parametersJson = builder.parametersJson;
        this.filePath = builder.filePath;
    }

    public Integer getReportId() {
        return reportId;
    }
public RoleType getGeneratedByUser() {
        return generatedByUser;
}
    public String getReportName() {
        return reportName;
    }

    public LocalDate getGeneratedAt() {
        return generatedAt;
    }

    public String getParametersJson() {
        return parametersJson;
    }

    public String getFilePath() {
        return filePath;
    }

    public static class Builder {
        private Integer reportId;
        private RoleType generatedByUser;
        private String reportName;
        private LocalDate generatedAt;
        private String parametersJson;
        private String filePath;

        public Builder setReportId(Integer reportId) { this.reportId = reportId; return this; }
      public Builder setRoleType(RoleType generatedByUser) { this.generatedByUser = generatedByUser; return this; }
       public Builder setReportName(String reportName) { this.reportName = reportName; return this; }
        public Builder setGeneratedAt(LocalDate generatedAt) { this.generatedAt = generatedAt; return this; }
        public Builder setParametersJson(String parametersJson) { this.parametersJson = parametersJson; return this; }
        public Builder setFilePath(String filePath) { this.filePath = filePath; return this; }

        public Builder copy(Report report) {
            this.reportId = report.getReportId();
            this.generatedByUser = report.getGeneratedByUser();
            this.reportName = report.getReportName();
            this.generatedAt = report.getGeneratedAt();
            this.parametersJson = report.getParametersJson();
            this.filePath = report.getFilePath();
            return this;
        }

        public Report build() { return new Report(this); }
    }
}
