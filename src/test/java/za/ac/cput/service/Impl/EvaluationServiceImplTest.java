package za.ac.cput.service.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.domain.Evaluation;
import za.ac.cput.domain.Placement;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.EvaluationRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EvaluationServiceImplTest {

    @Mock
    private EvaluationRepository repository;

    @InjectMocks
    private EvaluationServiceImpl service;

    private Evaluation sampleEvaluation;
    private Student sampleStudent;

    @BeforeEach
    void setUp() {
        // Your exact code - works with public constructors, no private access
        Staff staff = new Staff();
        sampleStudent = new Student("student123");
        Placement placement = new Placement(1);
        sampleEvaluation = new Evaluation.Builder()
                .setStaff(staff)
                .setStudent(sampleStudent)
                .setPlacement(placement)
                .setRating((short) 5)
                .setFeedback("Great performance!")
                .setEvaluationId(1)
                .build();
    }

    @Test
    void save_ShouldReturnSavedEvaluation() {
        // Arrange
        when(repository.save(any(Evaluation.class))).thenReturn(sampleEvaluation);

        // Act
        Evaluation saved = service.save(sampleEvaluation);

        // Assert - uses Evaluation's public getters only
        assertNotNull(saved);
        assertEquals("Great performance!", saved.getFeedback());
        assertEquals((short) 5, saved.getRating());
        assertEquals(1, saved.getEvaluationId());
        assertSame(sampleStudent, saved.getStudent());  // Reference equality (no private access)
        verify(repository, times(1)).save(any(Evaluation.class));
    }

    @Test
    void findAll_ShouldReturnList() {
        // Arrange
        List<Evaluation> expectedList = Collections.singletonList(sampleEvaluation);
        when(repository.findAll()).thenReturn(expectedList);

        // Act
        List<Evaluation> result = service.findAll();

        // Assert
        assertEquals(1, result.size());
        assertSame(sampleStudent, result.get(0).getStudent());  // Reference equality
        verify(repository, times(1)).findAll();
    }

    @Test
    void findById_WhenExists_ShouldReturnEvaluation() {
        // Arrange
        when(repository.findById(1)).thenReturn(Optional.of(sampleEvaluation));

        // Act
        Optional<Evaluation> result = service.findById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getEvaluationId());
        verify(repository, times(1)).findById(1);
    }

    @Test
    void deleteById_ShouldCallRepository() {
        // Act
        service.deleteById(1);

        // Assert
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void findByStudent_ShouldReturnListForStudent() {
        // Arrange
        List<Evaluation> expectedList = Collections.singletonList(sampleEvaluation);
        when(repository.findByStudent(sampleStudent)).thenReturn(expectedList);

        // Act
        List<Evaluation> result = service.findByStudent(sampleStudent);

        // Assert
        assertEquals(1, result.size());
        assertSame(sampleStudent, result.get(0).getStudent());  // Reference equality
        verify(repository, times(1)).findByStudent(sampleStudent);
    }
}
