package za.ac.cput.service;

import za.ac.cput.domain.Evaluation;
import za.ac.cput.domain.Student;

import java.util.List;
import java.util.Optional;

public interface IEvaluationService {
    Evaluation save(Evaluation evaluation);
    List<Evaluation> findAll();
    Optional<Evaluation> findById(Integer id);
    void deleteById(Integer id);
    List<Evaluation> findByStudent(Student student); // New method

    Evaluation update(Evaluation evaluationToUpdate);
}
