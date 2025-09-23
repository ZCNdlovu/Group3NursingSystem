package za.ac.cput.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.RoleType;
import za.ac.cput.repository.StudentRepository;
import za.ac.cput.service.IStudentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student create(Student student) {
        student.setRole(RoleType.STUDENT);
        return repository.save(student);
    }

    @Override
    public Student read(String studentId) {
        return repository.findById(studentId).orElse(null);
    }

    @Override
    public Student update(Student student) {
        if (student.getStudentId()== null) {
            throw new IllegalArgumentException("Cannot update student without an ID");
        }
        if (repository.existsById(student.getStudentId())) {
            return repository.save(student); // ✅ this is UPDATE
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String studentId) {
        repository.deleteById(studentId);
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Student> findByStudentNumber(String studentNumber) {
        return repository.findByStudentNumber(studentNumber);
    }

    @Override
    public List<Student> findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }
}
