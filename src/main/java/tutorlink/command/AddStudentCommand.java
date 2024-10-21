package tutorlink.command;

import java.util.HashMap;

import tutorlink.appstate.AppState;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.result.CommandResult;

public class AddStudentCommand extends Command {

    public static final String[] ARGUMENT_PREFIXES = {"i/", "n/"};
    public static final String COMMAND_WORD = "add_student";

    private static final String ERROR_EITHER_NULL = "Error! Either parameter passed is null!";
    private static final String SUCCESS_MESSAGE = "Student %s (%s) added successfully!";

    @Override
    public CommandResult execute(AppState appstate, HashMap<String, String> hashmap) throws TutorLinkException {
        String matricNumber = hashmap.get(ARGUMENT_PREFIXES[0]);
        String name = hashmap.get(ARGUMENT_PREFIXES[1]);
        if (matricNumber == null || name == null) {
            throw new IllegalValueException(ERROR_EITHER_NULL);
        }
        appstate.students.addStudent(matricNumber, name);
        return new CommandResult(String.format(SUCCESS_MESSAGE, name, matricNumber));
    }

    @Override
    public String[] getArgumentPrefixes() {
        return ARGUMENT_PREFIXES;
    }
}
