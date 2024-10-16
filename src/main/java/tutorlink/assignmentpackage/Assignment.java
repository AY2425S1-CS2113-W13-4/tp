package tutorlink.assignmentpackage;

import java.util.Objects;

public class Assignment {
    public static final double PERCENTAGE_MARGIN_OF_ERROR_DOUBLE = 0.5;
    private String assignmentDescription;
    private double receivedScore;
    private double totalScore;
    private double weighting;

    public Assignment(String assignmentDescription, double receivedScore, double totalScore, double weighting) {
        assert (weighting >= (0 - PERCENTAGE_MARGIN_OF_ERROR_DOUBLE)
                && weighting <= (100 + PERCENTAGE_MARGIN_OF_ERROR_DOUBLE))
                : "Assignment weighting out of range (0 - 100%)";

        this.assignmentDescription = assignmentDescription;
        this.receivedScore = receivedScore;
        this.totalScore = totalScore;
        this.weighting = weighting;
    }

    public double getWeightedScore() {
        return (receivedScore / totalScore) * weighting;
    }

    public String getassignmentDescription() {
        return assignmentDescription;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Assignment that = (Assignment) obj;
        return Objects.equals(assignmentDescription, that.assignmentDescription);
    }

    @Override
    public String toString() {
        return this.assignmentDescription + ", score: " + this.receivedScore + "/" + this.totalScore
                + ", weighting: " + this.weighting;
    }

    public double getWeighting() {
        return weighting;
    }

    public enum LetterGrade {
        A_plus, A, A_minus,
        B_plus, B, B_minus,
        C_plus, C,
        D_plus, D,
        F,
        IP; //In-Progress, no grade available

        public double getGPA(LetterGrade grade) {
            double result = 0.0;
            switch (grade) {
            case A_plus -> result = 5.0;
            case A -> result = 5.0;
            case A_minus -> result = 4.5;
            case B_plus -> result = 4.0;
            case B -> result = 3.5;
            case B_minus -> result = 3.0;
            case C_plus -> result = 2.5;
            case C -> result = 2.0;
            case D_plus -> result = 1.5;
            case D -> result = 1.0;
            case F -> result = 0.0;
            default -> result = -1.0; //IP case will be -1.0
            }
            return result;
        }

        @Override
        public String toString() {
            return switch (this) {
            case A_plus -> "A+";
            case A -> "A";
            case A_minus -> "A-";
            case B_plus -> "B+";
            case B -> "B";
            case B_minus -> "B-";
            case C_plus -> "C+";
            case C -> "C";
            case D_plus -> "D+";
            case D -> "D";
            case F -> "F";
            case IP -> "In-Progress";
            };
        }
    }
}
