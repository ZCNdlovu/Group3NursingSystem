package za.ac.cput.domain;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {

    @Column(name = "email_VARCHAR(255)", unique = true)
    private String email;

    @Column(name = "first_name_VARCHAR(100)")
    private String firstName;

    @Column(name = "last_name_VARCHAR(100)")
    private String lastName;

    @Column(name = "phone_VARCHAR(30)")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type_VARCHAR(50)")
    private RoleType role;


    protected User() {}

    protected User(String email, String firstName, String lastName, String phone, RoleType role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;

    }

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public RoleType getRole() { return role; }
    public void setRole(RoleType role) { this.role = role; }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }
}
