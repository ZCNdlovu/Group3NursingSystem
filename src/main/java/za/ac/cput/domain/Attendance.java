package za.ac.cput.domain;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id_INT")
    private Integer attendanceId;

    @ManyToOne
    @JoinColumn(name = "student_id_CHAR(36)")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "facility_id_INT")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "placement_id_INT")
    private Placement placement;

    @Column(name = "check_in_TIMESTAMP")
    private LocalDateTime checkIn;

    @Column(name = "check_out_TIMESTAMP")
    private LocalDateTime checkOut;

    @Column(name = "hours_DECIMAL(5,2)")
    private Double hours;

    @Column(name = "is_late_TINYINT(1)")
    private Boolean isLate;

    @Column(name = "log_DECIMAL(9,6)")
    private Double log;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_VARCHAR(20)")
    private StatusType status;

    @ManyToOne
    @JoinColumn(name = "verified_by_CHAR(36)")
    private Staff verifiedBy;

    @Column(name = "verified_at_TIMESTAMP")
    private LocalDateTime verifiedAt;

    @Column(name = "notes_TEXT")
    private String notes;

    protected Attendance() {}

    private Attendance(Builder builder) {
        this.attendanceId = builder.attendanceId;
        this.student = builder.student;
        this.facility = builder.facility;
        this.placement = builder.placement;
        this.checkIn = builder.checkIn;
        this.checkOut = builder.checkOut;
        this.hours = builder.hours;
        this.isLate = builder.isLate;
        this.log = builder.log;
        this.status = builder.status;
        this.verifiedBy = builder.verifiedBy;
        this.verifiedAt = builder.verifiedAt;
        this.notes = builder.notes;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public Student getStudent() {
        return student;
    }

    public Facility getFacility() {
        return facility;
    }

    public Placement getPlacement() {
        return placement;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public Double getHours() {
        return hours;
    }

    public Boolean getLate() {
        return isLate;
    }

    public Double getLog() {
        return log;
    }

    public StatusType getStatus() {
        return status;
    }

    public Staff getVerifiedBy() {
        return verifiedBy;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public String getNotes() {
        return notes;
    }

    public static class Builder {
        private Integer attendanceId;
        private Student student;
        private Facility facility;
        private Placement placement;
        private LocalDateTime checkIn;
        private LocalDateTime checkOut;
        private Double hours;
        private Boolean isLate;
        private Double log;
        private StatusType status;
        private Staff verifiedBy;
        private LocalDateTime verifiedAt;
        private String notes;

        public Builder setAttendanceId(Integer attendanceId) { this.attendanceId = attendanceId; return this; }
        public Builder setStudent(Student student) { this.student = student; return this; }
        public Builder setFacility(Facility facility) { this.facility = facility; return this; }
        public Builder setPlacement(Placement placement) { this.placement = placement; return this; }
        public Builder setCheckIn(LocalDateTime checkIn) { this.checkIn = checkIn; return this; }
        public Builder setCheckOut(LocalDateTime checkOut) { this.checkOut = checkOut; return this; }
        public Builder setHours(Double hours) { this.hours = hours; return this; }
        public Builder setLate(Boolean isLate) { this.isLate = isLate; return this; }
        public Builder setLog(Double log) { this.log = log; return this; }
        public Builder setStatus(StatusType status) { this.status = status; return this; }
        public Builder setVerifiedBy(Staff verifiedBy) { this.verifiedBy = verifiedBy; return this; }
        public Builder setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; return this; }
        public Builder setNotes(String notes) { this.notes = notes; return this; }

        public Builder copy(Attendance attendance) {
            this.attendanceId = attendance.getAttendanceId();
            this.student = attendance.getStudent();
            this.facility = attendance.getFacility();
            this.placement = attendance.getPlacement();
            this.checkIn = attendance.getCheckIn();
            this.checkOut = attendance.getCheckOut();
            this.hours = attendance.getHours();
            this.isLate = attendance.getLate();
            this.log = attendance.getLog();
            this.status = attendance.getStatus();
            this.verifiedBy = attendance.getVerifiedBy();
            this.verifiedAt = attendance.getVerifiedAt();
            this.notes = attendance.getNotes();
            return this;
        }

        public Attendance build() { return new Attendance(this); }
    }
}
