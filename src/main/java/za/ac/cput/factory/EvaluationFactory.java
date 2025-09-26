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
}
