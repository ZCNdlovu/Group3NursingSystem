package za.ac.cput.factory;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import za.ac.cput.domain.Evaluation;
import za.ac.cput.domain.Placement;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.Student;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluationFactoryTest {

    private Staff staff;
    private Student student;
    private Placement placement;

    @BeforeEach
    void setUp() {

        staff = new Staff();
        student = new Student();
        placement = new Placement();
    }

    @Test
    void testCreateEvaluation() {

        Short testRating = (short) 4;
        String testFeedback = "Excellent performance";


        Evaluation evaluation = EvaluationFactory.createEvaluation(staff, student, placement, testRating, testFeedback);


        assertNotNull(evaluation);
        assertNull(evaluation.getEvaluationId());
        assertEquals(staff, evaluation.getStaff());
        assertEquals(student, evaluation.getStudent());
        assertEquals(placement, evaluation.getPlacement());
        assertEquals(testRating, evaluation.getRating());
        assertEquals(testFeedback, evaluation.getFeedback());
    }

    @Test
    void testCreateEvaluationWithId() {

        Integer Id = 123;
        Short Rating = (short) 3;
        String Feedback = "Needs improvement";


        Evaluation evaluation = EvaluationFactory.createEvaluationWithId(Id, staff, student, placement, Rating, Feedback);


        assertNotNull(evaluation);
        assertEquals(Id, evaluation.getEvaluationId());
        assertEquals(staff, evaluation.getStaff());
        assertEquals(student, evaluation.getStudent());
        assertEquals(placement, evaluation.getPlacement());
        assertEquals(Rating, evaluation.getRating());
        assertEquals(Feedback, evaluation.getFeedback());
    }

    @Test
    void testCreateDefaultEvaluation() {
        // When
        Evaluation evaluation = EvaluationFactory.createDefaultEvaluation();

        assertNotNull(evaluation);
        assertNull(evaluation.getEvaluationId());
        assertNotNull(evaluation.getStaff());
        assertNotNull(evaluation.getStudent());
        assertNotNull(evaluation.getPlacement());
        assertEquals((short) 5, evaluation.getRating());
        assertEquals("Good job!", evaluation.getFeedback());
    }
}
