package za.ac.cput.domain;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "staff_allocation")
public class StaffAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allocation_id_INT")
    private Integer allocationId;

    @ManyToOne
    @JoinColumn(name = "staff_id_CHAR(36)")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "facility_id_INT")
    private Facility facility;

    @Column(name = "year_level_SMALLINT")
    private Short yearLevel;

    @Column(name = "start_date_DATE")
    private LocalDate startDate;

    @Column(name = "end_date_DATE")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_VARCHAR(20)")
    private StatusType status;

    protected StaffAllocation() {}

    private StaffAllocation(Builder builder) {
        this.allocationId = builder.allocationId;
        this.staff = builder.staff;
        this.facility = builder.facility;
        this.yearLevel = builder.yearLevel;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.status = builder.status;

    }

    public Integer getAllocationId() {
        return allocationId;
    }

    public Staff getStaff() {
        return staff;
    }

    public Facility getFacility() {
        return facility;
    }

    public Short getYearLevel() {
        return yearLevel;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public StatusType getStatus() {
        return status;
    }

    public static class Builder {
        private Integer allocationId;
        private Staff staff;
        private Facility facility;
        private Short yearLevel;
        private LocalDate startDate;
        private LocalDate endDate;
        private StatusType status;


        public Builder setAllocationId(Integer allocationId) { this.allocationId = allocationId; return this; }
        public Builder setStaff(Staff staff) { this.staff = staff; return this; }
        public Builder setFacility(Facility facility) { this.facility = facility; return this; }
        public Builder setYearLevel(Short yearLevel) { this.yearLevel = yearLevel; return this; }
        public Builder setStartDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public Builder setEndDate(LocalDate endDate) { this.endDate = endDate; return this; }
        public Builder setStatus(StatusType status) { this.status = status; return this; }

        public Builder copy(StaffAllocation staffAllocation) {
            this.allocationId = staffAllocation.getAllocationId();
            this.staff = staffAllocation.getStaff();
            this.facility = staffAllocation.getFacility();
            this.yearLevel = staffAllocation.getYearLevel();
            this.startDate = staffAllocation.getStartDate();
            this.endDate = staffAllocation.getEndDate();
            this.status = staffAllocation.getStatus();
            return this;
        }

        public StaffAllocation build() { return new StaffAllocation(this); }
    }
}
