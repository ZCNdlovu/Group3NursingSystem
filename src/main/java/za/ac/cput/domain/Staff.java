package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.util.IdGenerator;

@Entity
@Table(name = "staff")
public class Staff extends User {
    @Id
    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "staff_number")
    private String staffNumber;

    @Column(name = "is_clinical")
    private Boolean isClinical;


    public Staff(String staff123) { }//made changes

    public Staff() {

    }
    public Staff(Builder builder) {
        super(builder.email, builder.firstName, builder.lastName, builder.phone,builder.password, builder.role);
        this.staffId = builder.staffId;
        this.staffNumber = builder.staffNumber;
        this.isClinical = builder.isClinical;
    }



    public String getStaffId() {
        return staffId;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public Boolean getClinical() {
        return isClinical;
    }

    public static class Builder {
        private String staffId;
        private String email;
        private String firstName;
        private String lastName;
        private String phone;
        private String password;
        private RoleType role;
        private String staffNumber;
        private Boolean isClinical;


        public Builder setStaffId(String staffId) { this.staffId = staffId; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setPhone(String phone) { this.phone = phone; return this; }
        public Builder setPassword(String password){this.password = password; return this; }
        public Builder setRole(RoleType role){this.role = role; return this; }
        public Builder setStaffNumber(String staffNumber) { this.staffNumber = staffNumber; return this; }
        public Builder setClinical(Boolean isClinical) { this.isClinical = isClinical; return this; }

        public Builder copy(Staff staff) {
            this.staffId = staff.getStaffId();
            this.email = staff.getEmail();
            this.firstName = staff.getFirstName();
            this.lastName = staff.getLastName();
            this.phone = staff.getPhone();
            this.password = staff.getPassword();
            this.role = staff.getRole();
            this.staffNumber = staff.getStaffNumber();
            this.isClinical = staff.getClinical();
            return this;
        }

        public Staff build() { return new Staff(this); }
    }
}
