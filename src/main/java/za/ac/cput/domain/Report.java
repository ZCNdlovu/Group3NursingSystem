package za.ac.cput.domain;

import jakarta.persistence.*;
import org.hibernate.usertype.UserType;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer reportId;

    @Enumerated(EnumType.STRING)
    @Column(name = "generatedByUser",nullable = false)
    private RoleType generatedByUser; // Need to be changed and make Student,Staff and Admin

    @Column(name = "report_name",nullable = false)
    private String reportName;

    @Column(name = "generated_at",nullable = false)
    private LocalDate generatedAt;

    @Column(name = "parameters",nullable = false)
    private String parametersJson;

    @Column(name = "file_path",nullable = false)
    private String filePath;

    @OneToMany(mappedBy = "report", // Specifies the field in the Document class that owns the relationship
            cascade = CascadeType.ALL, // Operations (like Delete) on Report will cascade to Documents
            fetch = FetchType.LAZY) // Documents are loaded only when explicitly requested
    private Set<Document> documents;

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
                ", documents='" + documents + '\'' +
                '}';
    }

    private Report(Builder builder) {
        this.reportId = builder.reportId;
        this.generatedByUser = builder.generatedByUser;
        this.reportName = builder.reportName;
        this.generatedAt = builder.generatedAt;
        this.parametersJson = builder.parametersJson;
        this.filePath = builder.filePath;
        this.documents = builder.documents;
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
     public Set<Document> getDocuments() {return documents;}

    public static class Builder {
        private Integer reportId;
        private RoleType generatedByUser;
        private String reportName;
        private LocalDate generatedAt;
        private String parametersJson;
        private String filePath;
        private Set<Document> documents;

        public Builder setReportId(Integer reportId) { this.reportId = reportId; return this; }
      public Builder setRoleType(RoleType generatedByUser) { this.generatedByUser = generatedByUser; return this; }
       public Builder setReportName(String reportName) { this.reportName = reportName; return this; }
        public Builder setGeneratedAt(LocalDate generatedAt) { this.generatedAt = generatedAt; return this; }
        public Builder setParametersJson(String parametersJson) { this.parametersJson = parametersJson; return this; }
        public Builder setFilePath(String filePath) { this.filePath = filePath; return this; }
        public Builder setDocuments(Set<Document> documents){this.documents = documents; return this;}

        public Builder copy(Report report) {
            this.reportId = report.getReportId();
            this.generatedByUser = report.getGeneratedByUser();
            this.reportName = report.getReportName();
            this.generatedAt = report.getGeneratedAt();
            this.parametersJson = report.getParametersJson();
            this.filePath = report.getFilePath();
            this.documents = report.getDocuments();
            return this;
        }

        public Report build() { return new Report(this); }
    }
}
