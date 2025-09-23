package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Student;

import java.util.*;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    void delete(String studentId);
    Optional<Student> findByEmail(String email);
    Optional<Student> findByStudentNumber(String studentNumber);
    List<Student> findByEmailAndPassword(String email, String password);
}
