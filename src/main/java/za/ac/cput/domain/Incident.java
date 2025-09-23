package za.ac.cput.domain;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "incident")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incident_id_INT")
    private Integer incidentId;

    @ManyToOne
    @JoinColumn(name = "student_id_CHAR(36)")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "reported_by_staff_CHAR(36)")
    private Staff reportedByStaff;

    @ManyToOne
    @JoinColumn(name = "facility_id_INT")
    private Facility facility;

    @Column(name = "incident_date_TIMESTAMP")
    private LocalDate incidentDate;

    @Column(name = "description_TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_VARCHAR(20)")
    private StatusType status;


    protected Incident() {}

    private Incident(Builder builder) {
        this.incidentId = builder.incidentId;
        this.student = builder.student;
        this.reportedByStaff = builder.reportedByStaff;
        this.facility = builder.facility;
        this.incidentDate = builder.incidentDate;
        this.description = builder.description;
        this.status = builder.status;
       }

    public Integer getIncidentId() {
        return incidentId;
    }

    public Student getStudent() {
        return student;
    }

    public Staff getReportedByStaff() {
        return reportedByStaff;
    }

    public Facility getFacility() {
        return facility;
    }

    public LocalDate getIncidentDate() {
        return incidentDate;
    }

    public String getDescription() {
        return description;
    }

    public StatusType getStatus() {
        return status;
    }

    public static class Builder {
        private Integer incidentId;
        private Student student;
        private Staff reportedByStaff;
        private Facility facility;
        private LocalDate incidentDate;
        private String description;
        private StatusType status;

        public Builder setIncidentId(Integer incidentId) { this.incidentId = incidentId; return this; }
        public Builder setStudent(Student student) { this.student = student; return this; }
        public Builder setReportedByStaff(Staff reportedByStaff) { this.reportedByStaff = reportedByStaff; return this; }
        public Builder setFacility(Facility facility) { this.facility = facility; return this; }
        public Builder setIncidentDate(LocalDate incidentDate) { this.incidentDate = incidentDate; return this; }
        public Builder setDescription(String description) { this.description = description; return this; }
        public Builder setStatus(StatusType status) { this.status = status; return this; }

        public Builder copy(Incident incident) {
            this.incidentId = incident.getIncidentId();
            this.student = incident.getStudent();
            this.reportedByStaff = incident.getReportedByStaff();
            this.facility = incident.getFacility();
            this.incidentDate = incident.getIncidentDate();
            this.description = incident.getDescription();
            this.status = incident.getStatus();
            return this;
        }

        public Incident build() { return new Incident(this); }
    }
}
