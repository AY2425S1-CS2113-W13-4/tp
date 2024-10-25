package tutorlink.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssignmentTest {
    private Assignment assignment;

    @BeforeEach
    void setup() {
        assignment = new Assignment("Homework", 30.0, 0.2);
    }

    @Test
    void constructor_validAssignment_success() {
        assertEquals("homework", assignment.getName());
        assertEquals(30.0, assignment.getMaxScore());
        assertEquals(0.2, assignment.getWeight());
    }

    @Test
    void toString_validAssignment_correctFormat() {
        String expected = "[name=homework, maxScore=30.0, weight=0.2]";
        assertEquals(expected, assignment.toString());
    }
}

