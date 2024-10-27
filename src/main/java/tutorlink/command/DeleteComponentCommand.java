package tutorlink.command;

import java.util.HashMap;

import tutorlink.appstate.AppState;
import tutorlink.exceptions.ComponentNotFoundException;
import tutorlink.exceptions.DuplicateComponentException;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.lists.ComponentList;
import tutorlink.result.CommandResult;

public class DeleteComponentCommand extends Command {
    public static final String[] ARGUMENT_PREFIXES = {"n/"};
    public static final String COMMAND_WORD = "delete_component";
    public static final String ERROR_COMPONENT_NAME_NULL = "Error! Component name is null";
    public static final String COMPONENT_NOT_FOUND = "Error! Component (Name Number %s) not found";
    public static final String ERROR_DUPLICATE_COMPONENT = "Error! Component already exists in the list!";
    public static final String SUCCESS_MESSAGE = "Component %s successfully deleted";

    @Override
    public CommandResult execute(AppState appState, HashMap<String, String> hashmap) throws TutorLinkException {
        String componentName = hashmap.get(ARGUMENT_PREFIXES[0]);
        if (componentName == null) {
            throw new IllegalValueException(ERROR_COMPONENT_NAME_NULL);
        }

        ComponentList componentsToDelete = appState.components.findComponent(componentName);
        if (componentsToDelete.size() == 0) {
            throw new ComponentNotFoundException(String.format(COMPONENT_NOT_FOUND, componentName));
        } else if (componentsToDelete.size() > 1) {
            throw new DuplicateComponentException(ERROR_DUPLICATE_COMPONENT);
        }

        appState.components.deleteComponent(componentsToDelete.getComponentArrayList().get(0));
        appState.grades.deleteGradesByComponent(componentName);

        return new CommandResult(String.format(SUCCESS_MESSAGE, componentName));
    }

    @Override
    public String[] getArgumentPrefixes() {
        return ARGUMENT_PREFIXES;
    }
}
