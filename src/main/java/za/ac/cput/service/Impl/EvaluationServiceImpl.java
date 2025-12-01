package za.ac.cput.service.Impl;


import org.springframework.stereotype.Service;
import za.ac.cput.domain.Evaluation;
import za.ac.cput.domain.Incident;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.EvaluationRepository;
import za.ac.cput.service.IEvaluationService;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationServiceImpl implements IEvaluationService {

    private final EvaluationRepository repository;

    public EvaluationServiceImpl(EvaluationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Evaluation save(Evaluation evaluation) {
        return repository.save(evaluation);
    }

    @Override
    public Evaluation update(Evaluation evaluation) {
        if (evaluation.getEvaluationId() == null) {
            throw new IllegalArgumentException("Cannot update incident without an ID");
        }
        if (repository.existsById(evaluation.getEvaluationId())) {
            return repository.save(evaluation);
        }
        return null;
    }
    @Override
    public List<Evaluation> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Evaluation> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Evaluation> findByStudent(Student student) {
        return repository.findByStudent(student);
    }
}
