package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.util.IdGenerator;

@Entity
@Table(name = "student")
public class Student extends User {
    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "student_number")
    private String studentNumber;

    @Column(name = "year_level")
    private Short yearLevel;

    @Column(name = "program")
    private String program;


    public Student(String student123) {  }//made changes


    public Student(Builder builder) {
        super(builder.email, builder.firstName, builder.lastName, builder.phone,builder.password,
                builder.role);
        this.studentId = builder.studentId;
        this.studentNumber = builder.studentNumber;
        this.yearLevel = builder.yearLevel;
        this.program = builder.program;
    }

    protected Student() {

    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public Short getYearLevel() {
        return yearLevel;
    }

    public String getProgram() {
        return program;
    }



    public static class Builder {
        private String studentId;
        private String email;
        private String firstName;
        private String lastName;
        private String phone;
        private String password;
        private String studentNumber;
        private Short yearLevel;
        private String program;
        private RoleType role;


        public Builder setStudentId(String studentId) { this.studentId = studentId; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public Builder setFirstName(String firstName) { this.firstName = firstName; return this; }
        public Builder setLastName(String lastName) { this.lastName = lastName; return this; }
        public Builder setPhone(String phone) { this.phone = phone; return this; }
        public Builder setPassword(String password){ this.password = password; return this; }
        public Builder setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; return this; }
        public Builder setYearLevel(Short yearLevel) { this.yearLevel = yearLevel; return this; }
        public Builder setProgram(String program) { this.program = program; return this; }
        public Builder setRole(RoleType role) { this.role = role; return this; }

        public Builder copy(Student student) {
            this.studentId = student.getStudentId();
            this.email = student.getEmail();
            this.firstName = student.getFirstName();
            this.lastName = student.getLastName();
            this.phone = student.getPhone();
            this.password = student.getPassword();
            this.studentNumber = student.getStudentNumber();
            this.yearLevel = student.getYearLevel();
            this.program = student.getProgram();
            this.role = student.getRole();
            return this;
        }

        public Student build() { return new Student(this); }
    }
}
