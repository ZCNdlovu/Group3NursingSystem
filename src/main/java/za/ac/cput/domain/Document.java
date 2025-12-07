package za.ac.cput.domain;

import com.itextpdf.text.Paragraph;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Integer documentId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "placement_id")
    private Placement placement;

    @Column(name = "file_path",nullable = false)
    private String filePath;

    @Column(name = "file_name",nullable = false)
    private String fileName;

    @Column(name = "size_bytes",nullable = false)
    private Long sizeBytes;

    @Column(name = "uploaded_at",nullable = false)
    private LocalDateTime uploadedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    @ManyToOne // Many Documents belong to One Report
    @JoinColumn(name = "report_id", nullable = false) // Defines the foreign key column name
    private Report report;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private Staff approvedByStaff;

    public Document() {}

    @Override
    public String toString() {
        return "Document{" +
                "documentId='" + documentId + '\'' +
                ", student=" + student +
                ", staff=" + staff +
                ", placement=" + placement +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", sizeBytes=" + sizeBytes +
                ", uploadedAt=" + uploadedAt +
                ", status=" + status +
                ", report=" + report +
                ", approvedByStaff=" + approvedByStaff +
                '}';
    }

    private Document(Builder builder) {
        this.documentId = builder.documentId;
        this.student = builder.student;
        this.staff = builder.staff;
        this.placement = builder.placement;
        this.filePath = builder.filePath;
        this.fileName = builder.fileName;
        this.sizeBytes = builder.sizeBytes;
        this.uploadedAt = builder.uploadedAt;
        this.status = builder.status;
        this.approvedByStaff = builder.approvedByStaff;
        this.report = builder.report;
    }


    public Integer getDocumentId() {return documentId;
    }

    public Student getStudent() {
        return student;
    }

    public Staff getStaff() {
        return staff;
    }

    public Placement getPlacement() {
        return placement;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getSizeBytes() {
        return sizeBytes;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public StatusType getStatus() {
        return status;
    }

    public Staff getApprovedByStaff() {
        return approvedByStaff;
    }

     public Report getReport(){return report;}




    public static class Builder {
        private Integer documentId;
        private Student student;
        private Staff staff;
        private Placement placement;
        private String filePath;
        private String fileName;
        private Long sizeBytes;
        private LocalDateTime uploadedAt;
        private StatusType status;
        private Staff approvedByStaff;
        private Report report;

        public Builder setDocumentId(Integer documentId) { this.documentId = documentId; return this; }
        public Builder setStudent(Student student) { this.student = student; return this; }
        public Builder setStaff(Staff staff) { this.staff = staff; return this; }
        public Builder setPlacement(Placement placement) { this.placement = placement; return this; }
        public Builder setFilePath(String filePath) { this.filePath = filePath; return this; }
        public Builder setFileName(String fileName) { this.fileName = fileName; return this; }
        public Builder setSizeBytes(Long sizeBytes) { this.sizeBytes = sizeBytes; return this; }
        public Builder setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; return this; }
        public Builder setStatus(StatusType status) { this.status = status; return this; }
        public Builder setApprovedByStaff(Staff approvedByStaff) { this.approvedByStaff = approvedByStaff; return this; }
        public Builder setReport(Report report){this.report = report; return this;}

        public Builder copy(Document document) {
            this.student = document.getStudent();
            this.staff = document.getStaff();
            this.placement = document.getPlacement();
            this.filePath = document.getFilePath();
            this.fileName = document.getFileName();
            this.sizeBytes = document.getSizeBytes();
            this.uploadedAt = document.getUploadedAt();
            this.status = document.getStatus();
            this.approvedByStaff = document.getApprovedByStaff();
            this.report = document.getReport();
            return this;
        }

        public Document build() { return new Document(this); }
    }
}
