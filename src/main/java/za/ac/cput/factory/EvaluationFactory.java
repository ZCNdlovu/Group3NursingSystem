package za.ac.cput.factory;

import za.ac.cput.domain.Evaluation;
import za.ac.cput.domain.Placement;
import za.ac.cput.domain.Staff;
import za.ac.cput.domain.Student;

public class EvaluationFactory {


    public static Evaluation createEvaluation(Staff staff, Student student, Placement placement, Short rating, String feedback) {
        return new Evaluation.Builder()
                .setStaff(staff)
                .setStudent(student)
                .setPlacement(placement)
                .setRating(rating)
                .setFeedback(feedback)
                .build();
    }


    public static Evaluation createEvaluationWithId(Integer evaluationId, Staff staff, Student student, Placement placement, Short rating, String feedback) {
        return new Evaluation.Builder()
                .setEvaluationId(evaluationId)
                .setStaff(staff)
                .setStudent(student)
                .setPlacement(placement)
                .setRating(rating)
                .setFeedback(feedback)
                .build();
    }


    public static Evaluation createDefaultEvaluation() {

        Staff defaultStaff = new Staff();
        Student defaultStudent = new Student();
        Placement defaultPlacement = new Placement();
        return createEvaluation(defaultStaff, defaultStudent, defaultPlacement, (short) 5, "Good job!");
    }
}
