package tutorlink.command;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tutorlink.appstate.AppState;
import tutorlink.commons.Commons;
import tutorlink.component.Component;
import tutorlink.exceptions.ComponentNotFoundException;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.result.CommandResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteComponentCommandTest {
    private AppState appState;
    private DeleteComponentCommand command = new DeleteComponentCommand();
    private HashMap<String,String> arguments;
    private CommandResult result;
    @BeforeEach
    void setup() {
        appState = new AppState();
        appState.totalWeight = 40+10+10;
        appState.components.addComponent(new Component("finals", 40.0, 40));
        appState.components.addComponent(new Component("iP", 20.0, 10));
        appState.components.addComponent(new Component("lectures", 10.0, 10));
    }

    @Test
    void deleteComponent_success() {
        arguments = new HashMap<>();
        arguments.put("c/", "finals");
        int initialWeighting = appState.totalWeight;
        result = command.execute(appState, arguments);
        assertEquals(appState.totalWeight, initialWeighting-40);
        assertNotNull(result);
        assertEquals(appState.components.size(), 2);
        try {
            appState.components.findComponent("finals");
        } catch (ComponentNotFoundException e) {
            assertEquals(e.getMessage(), "Error! Component finals does not exist in the list!");
        } catch (Exception e) {
            fail("Expected: ComponentNotFoundException, actual: " + e.getMessage());
        }
    }

    @Test
    void deleteComponent_notFound_fail() {
        arguments = new HashMap<>();
        arguments.put("c/", "midterms");
        int initialWeighting = appState.totalWeight;
        try {
            command.execute(appState, arguments);
        } catch (ComponentNotFoundException e) {
            assertEquals(e.getMessage(), "Error! Component midterms does not exist in the list!");
        } catch (Exception e) {
            fail("Expected: ComponentNotFoundException, actual: " + e.getMessage());
        } finally {
            assertEquals(appState.totalWeight, initialWeighting);
        }
    }

    @Test
    void deleteComponent_emptyParam_fail() {
        arguments = new HashMap<>();
        int initialWeighting = appState.totalWeight;
        try {
            command.execute(appState, arguments);
        } catch (IllegalValueException e) {
            assertEquals(e.getMessage(), Commons.ERROR_NULL);
        } catch (Exception e) {
            fail("Expected: ComponentNotFoundException, actual: " + e.getMessage());
        } finally {
            assertEquals(appState.totalWeight, initialWeighting);
        }
    }
}
