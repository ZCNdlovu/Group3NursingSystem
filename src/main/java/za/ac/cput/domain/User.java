package za.ac.cput.domain;

import jakarta.persistence.*;
import jakarta.persistence.PrePersist;
import za.ac.cput.util.IdGenerator;

@MappedSuperclass
public abstract class User {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "phone",nullable = false)
    private String phone;

    @Column(name = "password",nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType role;


    protected User() {}

    protected User(String email, String firstName, String lastName, String phone, String password, RoleType role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
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
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public RoleType getRole() { return role; }
    public void setRole(RoleType role) { this.role = role; }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
