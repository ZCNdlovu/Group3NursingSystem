package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @Column(name = "document_id_CHAR(36)")
    private String documentId;

    @ManyToOne
    @JoinColumn(name = "student_id_CHAR(36)")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "staff_id_CHAR(36)")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "placement_id_INT")
    private Placement placement;

    @Column(name = "file_path_TEXT")
    private String filePath;

    @Column(name = "file_name_VARCHAR(50)")
    private String fileName;

    @Column(name = "size_bytes_BIGINT")
    private Long sizeBytes;

    @Column(name = "uploaded_at_TIMESTAMP")
    private LocalDate uploadedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_VARCHAR(20)")
    private StatusType status;

    @ManyToOne
    @JoinColumn(name = "approved_by_staff_CHAR(36)")
    private Staff approvedByStaff;

    protected Document() {}

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
    }

    public String getDocumentId() {
        return documentId;
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

    public LocalDate getUploadedAt() {
        return uploadedAt;
    }

    public StatusType getStatus() {
        return status;
    }

    public Staff getApprovedByStaff() {
        return approvedByStaff;
    }

    public static class Builder {
        private String documentId;
        private Student student;
        private Staff staff;
        private Placement placement;
        private String filePath;
        private String fileName;
        private Long sizeBytes;
        private LocalDate uploadedAt;
        private StatusType status;
        private Staff approvedByStaff;

        public Builder setDocumentId(String documentId) { this.documentId = documentId; return this; }
        public Builder setStudent(Student student) { this.student = student; return this; }
        public Builder setStaff(Staff staff) { this.staff = staff; return this; }
        public Builder setPlacement(Placement placement) { this.placement = placement; return this; }
        public Builder setFilePath(String filePath) { this.filePath = filePath; return this; }
        public Builder setFileName(String fileName) { this.fileName = fileName; return this; }
        public Builder setSizeBytes(Long sizeBytes) { this.sizeBytes = sizeBytes; return this; }
        public Builder setUploadedAt(LocalDate uploadedAt) { this.uploadedAt = uploadedAt; return this; }
        public Builder setStatus(StatusType status) { this.status = status; return this; }
        public Builder setSpprovedByStaff(Staff approvedByStaff) { this.approvedByStaff = approvedByStaff; return this; }

        public Builder copy(Document document) {
            this.documentId = document.getDocumentId();
            this.student = document.getStudent();
            this.staff = document.getStaff();
            this.placement = document.getPlacement();
            this.filePath = document.getFilePath();
            this.fileName = document.getFileName();
            this.sizeBytes = document.getSizeBytes();
            this.uploadedAt = document.getUploadedAt();
            this.status = document.getStatus();
            this.approvedByStaff = document.getApprovedByStaff();
            return this;
        }

        public Document build() { return new Document(this); }
    }
}
