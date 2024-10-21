package tutorlink.command.studentcommand;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import tutorlink.appstate.AppState;
import tutorlink.command.AddStudentCommand;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.result.CommandResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;


public class AddStudentCommandTest {



    @Test
    void execute_addOne_expectOne() {
        AppState appState = new AppState();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("i/","A1234567X");
        arguments.put("n/", "John");
        AddStudentCommand command = new AddStudentCommand();
        CommandResult result = command.execute(appState, arguments);
        assertNotNull(result);
        assertEquals(result.toString(), "Student John (A1234567X) added successfully!");
        assertEquals(appState.students.getStudentArrayList().size(), 1);
    }

    @Test
    void execute_emptyInput_exceptionThrown() {
        AppState appState = new AppState();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("i/","A1234567X");
        AddStudentCommand command = new AddStudentCommand();

        try {
            CommandResult result = command.execute(appState, arguments);
            fail("Expected an exception to be thrown due to empty input");
        } catch (IllegalValueException e) {
            // Assert that the exception message matches the expected outcome
            assertEquals("Error! Either parameter passed is null!", e.getMessage());
        } catch (Exception e) {
            // If any other type of exception is thrown, fail the test
            fail("Expected IllegalArgumentException, but got: " + e.getClass().getSimpleName());
        }
    }
}
