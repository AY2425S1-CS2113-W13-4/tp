package TutorLink.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tutorlink.component.ClassParticipation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassParticipationTest {
    private ClassParticipation participation;

    @BeforeEach
    void setup() {
        participation = new ClassParticipation("Participation", 10.0, 0.1);
    }

    @Test
    void constructor_validParticipation_success() {
        assertEquals("Participation", participation.getName());
        assertEquals(10.0, participation.getMaxScore());
        assertEquals(0.1, participation.getWeight());
    }

    @Test
    void toString_validParticipation_correctFormat() {
        String expected = "ClassParticipation [name=participation, maxScore=10.0, weight=0.1]";
        assertEquals(expected, participation.toString());
    }
}
