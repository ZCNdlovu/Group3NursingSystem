package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id_INT")
    private Integer evaluationId;

    @ManyToOne
    @JoinColumn(name = "staff_id_CHAR(36)")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "student_id_CHAR(36)")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "placement_id_INT")
    private Placement placement;

    @Column(name = "rating_SMALLINT")
    private Short rating;

    @Column(name = "feedback_TEXT")
    private String feedback;


    protected Evaluation() {}

    private Evaluation(Builder builder) {
        this.evaluationId = builder.evaluationId;
        this.staff = builder.staff;
        this.student = builder.student;
        this.placement = builder.placement;
        this.rating = builder.rating;
        this.feedback = builder.feedback;
        }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public Staff getStaff() {
        return staff;
    }

    public Student getStudent() {
        return student;
    }

    public Placement getPlacement() {
        return placement;
    }

    public Short getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public static class Builder {
        private Integer evaluationId;
        private Staff staff;
        private Student student;
        private Placement placement;
        private Short rating;
        private String feedback;

        public Builder setEvaluationId(Integer evaluationId) { this.evaluationId = evaluationId; return this; }
        public Builder setStaff(Staff staff) { this.staff = staff; return this; }
        public Builder setStudent(Student student) { this.student = student; return this; }
        public Builder setPlacement(Placement placement) { this.placement = placement; return this; }
        public Builder setRating(Short rating) { this.rating = rating; return this; }
        public Builder setFeedback(String feedback) { this.feedback = feedback; return this; }

        public Builder copy(Evaluation evaluation) {
            this.evaluationId = evaluation.getEvaluationId();
            this.staff = evaluation.getStaff();
            this.student = evaluation.getStudent();
            this.placement = evaluation.getPlacement();
            this.rating = evaluation.getRating();
            this.feedback = evaluation.getFeedback();
            return this;
        }

        public Evaluation build() { return new Evaluation(this); }
    }
}
