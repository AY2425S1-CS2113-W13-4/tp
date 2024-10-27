package tutorlink.command;

import java.util.HashMap;

import tutorlink.appstate.AppState;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.result.CommandResult;

public class DeleteStudentCommand extends Command {

    public static final String[] ARGUMENT_PREFIXES = {"i/"};
    public static final String COMMAND_WORD = "delete_student";
    public static final String ERROR_MATRIC_NUMBER_NULL = "Error! Matric number is null";
    public static final String SUCCESS_MESSAGE = "Student %s successfully deleted";

    @Override
    public CommandResult execute(AppState appState, HashMap<String, String> hashmap) throws TutorLinkException {
        String matricNumber = hashmap.get(ARGUMENT_PREFIXES[0]);
        if (matricNumber == null) {
            throw new IllegalValueException(ERROR_MATRIC_NUMBER_NULL);
        }

        appState.students.deleteStudent(matricNumber);
        appState.grades.deleteGradesByMatric(matricNumber);

        return new CommandResult(String.format(SUCCESS_MESSAGE, matricNumber));
    }

    @Override
    public String[] getArgumentPrefixes() {
        return ARGUMENT_PREFIXES;
    }
}
