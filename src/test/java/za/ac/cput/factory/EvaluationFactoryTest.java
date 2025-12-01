package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Evaluation;
import za.ac.cput.domain.Placement;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.Student;

import static org.junit.jupiter.api.Assertions.*;

class EvaluationFactoryTest {

    @Test
    void createEvaluation_ShouldReturnValidEvaluation() {
        // Arrange - uses placeholders
        Staff staff = new Staff("staff123");
        Student student = new Student("student123");
        Placement placement = new Placement(1);
        Short rating = (short) 5;
        String feedback = "Great performance!";

        // Act
        Evaluation evaluation = EvaluationFactory.createEvaluation(staff, student, placement, rating, feedback);


        assertNotNull(evaluation);
        assertSame(staff, evaluation.getStaff());
        assertSame(student, evaluation.getStudent());
        assertSame(placement, evaluation.getPlacement());
        assertEquals(rating, evaluation.getRating());
        assertEquals(feedback, evaluation.getFeedback());
        assertNull(evaluation.getEvaluationId());
    }
}
