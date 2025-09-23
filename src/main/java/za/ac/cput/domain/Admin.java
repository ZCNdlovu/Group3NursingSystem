package za.ac.cput.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends User {
    @Id
    @Column(name = "admin_id_CHAR(36)")
    private String adminId;

    protected Admin() { super(); }

    private Admin(Builder builder) {
        super(builder.email, builder.firstName, builder.lastName, builder.phone,
                RoleType.ADMIN);
        this.adminId = builder.adminId;
    }

    public String getAdminId() {
        return adminId;
    }

    public static class Builder {
        private String adminId;
        private String email;
        private String firstName;
        private String lastName;
        private String phone;


        public Builder setAdminId(String adminId) { this.adminId = adminId; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setPhone(String phone) { this.phone = phone; return this; }

        public Builder copy(Admin admin) {
            this.adminId = admin.getAdminId();
            this.email = admin.getEmail();
            this.firstName = admin.getFirstName();
            this.lastName = admin.getLastName();
            this.phone = admin.getPhone();
            return this;
        }

        public Admin build() { return new Admin(this); }
    }
}
