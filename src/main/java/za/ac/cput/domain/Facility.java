package za.ac.cput.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id_INT")
    private Integer facilityId;

    @Column(name = "name_VARCHAR(255)")
    private String name;

    @Column(name = "type_VARCHAR(50)")
    private String type;

    @Column(name = "address_TEXT")
    private String address;

    @Column(name = "contact_person_VARCHAR(150)")
    private String contactPerson;

    @Column(name = "contact_phone_VARCHAR(30)")
    private String contactPhone;

    @Column(name = "latitude_DECIMAL(9,6)")
    private Double latitude;

    @Column(name = "longitude_DECIMAL(9,6)")
    private Double longitude;


    protected Facility() {}

    private Facility(Builder builder) {
        this.facilityId = builder.facilityId;
        this.name = builder.name;
        this.type = builder.type;
        this.address = builder.address;
        this.contactPerson = builder.contactPerson;
        this.contactPhone = builder.contactPhone;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;

    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public static class Builder {
        private Integer facilityId;
        private String name;
        private String type;
        private String address;
        private String contactPerson;
        private String contactPhone;
        private Double latitude;
        private Double longitude;


        public Builder setFacilityId(Integer facilityId) { this.facilityId = facilityId; return this; }
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setType(String type) { this.type = type; return this; }
        public Builder setAddress(String address) { this.address = address; return this; }
        public Builder setContactPerson(String contactPerson) { this.contactPerson = contactPerson; return this; }
        public Builder setContactPhone(String contactPhone) { this.contactPhone = contactPhone; return this; }
        public Builder setLatitude(Double latitude) { this.latitude = latitude; return this; }
        public Builder setLongitude(Double longitude) { this.longitude = longitude; return this; }

        public Builder copy(Facility facility) {
            this.facilityId = facility.getFacilityId();
            this.name = facility.getName();
            this.type = facility.getType();
            this.address = facility.getAddress();
            this.contactPerson = facility.getContactPerson();
            this.contactPhone = facility.getContactPhone();
            this.latitude = facility.getLatitude();
            this.longitude = facility.getLongitude();
            return this;
        }

        public Facility build() { return new Facility(this); }
    }
}
