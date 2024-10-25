package tutorLink.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tutorlink.component.Exam;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExamTest {
    private Exam exam;

    @BeforeEach
    void setup() {
        exam = new Exam("Midterm", 100.0, 0.4);
    }

    @Test
    void constructor_validExam_success() {
        assertEquals("Midterm", exam.getName());
        assertEquals(100.0, exam.getMaxScore());
        assertEquals(0.4, exam.getWeight());
    }

    @Test
    void toString_validExam_correctFormat() {
        String expected = "Exam [name=midterm, maxScore=100.0, weight=0.4]";
        assertEquals(expected, exam.toString());
    }
}
