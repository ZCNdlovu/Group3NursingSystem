package za.ac.cput.domain;


import jakarta.persistence.*;
import za.ac.cput.util.IdGenerator;

@Entity
@Table(name = "admin")
public class Admin extends User {
    @Id
    @Column(name = "admin_id")
    private String adminId;

    protected Admin() { super(); }

    private Admin(Builder builder) {
        super(builder.email, builder.firstName, builder.lastName, builder.phone,builder.password,
                builder.role);
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
        private String password;
        private RoleType role;


        public Builder setAdminId(String adminId) { this.adminId = adminId; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setPhone(String phone) { this.phone = phone; return this; }
public Builder setPassword(String password){this.password = password; return this;}
        public Builder setRole(RoleType role) { this.role = role; return this;}
        public Builder copy(Admin admin) {
            this.adminId = admin.getAdminId();
            this.email = admin.getEmail();
            this.firstName = admin.getFirstName();
            this.lastName = admin.getLastName();
            this.phone = admin.getPhone();
            this.role = admin.getRole();
            return this;
        }

        public Admin build() { return new Admin(this); }
    }
}
