package TutorLink.component;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import tutorlink.component.Assignment;
import tutorlink.component.ClassParticipation;
import tutorlink.component.Component;
import tutorlink.component.Exam;
import tutorlink.exceptions.InvalidComponentException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class ComponentTest {
    private Component assignment;
    private Component exam;
    private Component participation;

    @BeforeEach
    void setup() {
        assignment = new Assignment("Assignment 1", 50.0, 0.3);
        exam = new Exam("Final Exam", 100.0, 0.4);
        participation = new ClassParticipation("Class Participation", 20.0, 0.1);
    }

    @Test
    void constructor_validInputs_success() {
        assertEquals("Assignment 1", assignment.getName());
        assertEquals(50.0, assignment.getMaxScore());
        assertEquals(0.3, assignment.getWeight());
    }

    @Test
    void constructor_invalidName_exceptionThrown() {
        assertThrows(InvalidComponentException.class, () ->
                new Assignment("", 50.0, 0.3));
        assertThrows(InvalidComponentException.class, () ->
                new Assignment(null, 50.0, 0.3));
    }

    @Test
    void constructor_invalidMaxScore_exceptionThrown() {
        assertThrows(InvalidComponentException.class, () ->
                new Assignment("Assignment 1", -50.0, 0.3));
        assertThrows(InvalidComponentException.class, () ->
                new Assignment("Assignment 1", 0.0, 0.3));
    }

    @Test
    void constructor_invalidWeight_exceptionThrown() {
        assertThrows(InvalidComponentException.class, () ->
                new Assignment("Assignment 1", 50.0, -0.1));
        assertThrows(InvalidComponentException.class, () ->
                new Assignment("Assignment 1", 50.0, 1.1));
    }

    @Test
    void equals_sameComponent_returnsTrue() {
        Component duplicateAssignment = new Assignment("Assignment 1", 50.0, 0.3);
        assertEquals(assignment, duplicateAssignment);
    }

    @Test
    void equals_differentComponent_returnsFalse() {
        Component differentAssignment = new Assignment("Assignment 2", 50.0, 0.3);
        assertNotEquals(assignment, differentAssignment);
        assertNotEquals(assignment, exam);
        assertNotEquals(assignment, null);
        assertNotEquals(assignment, "not a component");
    }

    @Test
    void toString_validComponent_correctFormat() {
        String expectedAssignment = "Assignment [name=Assignment 1, maxScore=50.0, weight=0.3]";
        String expectedExam = "Exam [name=final exam, maxScore=100.0, weight=0.4]";
        String expectedParticipation = "ClassParticipation [name=class participation, maxScore=20.0, weight=0.1]";

        assertEquals(expectedAssignment, assignment.toString());
        assertEquals(expectedExam, exam.toString());
        assertEquals(expectedParticipation, participation.toString());
    }
}
