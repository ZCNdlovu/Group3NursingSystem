package za.ac.cput.domain;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "placement")
public class Placement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "placement_id_INT")
    private Integer placementId;

    @ManyToOne
    @JoinColumn(name = "student_id_CHAR(36)")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "facility_id_INT")
    private Facility facility;

    @Column(name = "start_date_DATE")
    private LocalDate startDate;

    @Column(name = "end_date_DATE")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "allocated_by_staff_CHAR(36)")
    private Staff allocatedByStaff;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_VARCHAR(20)")
    private StatusType status;


    public Placement(int i) {}//made changes


    public Placement(Builder builder) {
        this.placementId = builder.placementId;
        this.student = builder.student;
        this.facility = builder.facility;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.allocatedByStaff = builder.allocatedByStaff;
        this.status = builder.status;

    }

    public Placement() {

    }

    public Integer getPlacementId() {
        return placementId;
    }

    public Student getStudent() {
        return student;
    }

    public Facility getFacility() {
        return facility;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Staff getAllocatedByStaff() {
        return allocatedByStaff;
    }

    public StatusType getStatus() {
        return status;
    }

    public static class Builder {
        private Integer placementId;
        private Student student;
        private Facility facility;
        private LocalDate startDate;
        private LocalDate endDate;
        private Staff allocatedByStaff;
        private StatusType status;


        public Builder setPlacementId(Integer placementId) { this.placementId = placementId; return this; }
        public Builder setStudent(Student student) { this.student = student; return this; }
        public Builder setFacility(Facility facility) { this.facility = facility; return this; }
        public Builder setStartDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public Builder setEndDate(LocalDate endDate) { this.endDate = endDate; return this; }
        public Builder setAllocatedByStaff(Staff allocatedByStaff) { this.allocatedByStaff = allocatedByStaff; return this; }
        public Builder setStatus(StatusType status) { this.status = status; return this; }

        public Builder copy(Placement placement) {
            this.placementId = placement.getPlacementId();
            this.student = placement.getStudent();
            this.facility = placement.getFacility();
            this.startDate = placement.getStartDate();
            this.endDate = placement.getEndDate();
            this.allocatedByStaff = placement.getAllocatedByStaff();
            this.status = placement.getStatus();
            return this;
        }

        public Placement build() { return new Placement(this); }
    }
}
