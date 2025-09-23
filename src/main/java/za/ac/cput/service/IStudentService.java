package za.ac.cput.service;

import za.ac.cput.domain.Student;
import java.util.List;
import java.util.Optional;

public interface IStudentService extends IService<Student, String> {
    void delete(String studentId);

    Optional<Student> findByEmail(String email);

    Optional<Student> findByStudentNumber(String studentNumber);

    //    void delete(String studentId);
//    Optional<Student> findByEmail(String email);
//    Optional<Student> findByStudentNumber(String studentNumber);
    List<Student> findByEmailAndPassword(String email, String password);
}
