package tutorlink.command;

import java.util.HashMap;

import tutorlink.appstate.AppState;
import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.StudentNotFoundException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.lists.StudentList;
import tutorlink.result.CommandResult;

import static tutorlink.lists.StudentList.STUDENT_NOT_FOUND;

public class DeleteGradeCommand extends Command {

    public static final String[] ARGUMENT_PREFIXES = {"i/", "c/"};
    public static final String COMMAND_WORD = "delete_grade";
    public static final String SUCCESS_MESSAGE = "Grade: Component %s for student %s successfully deleted";

    private static final String ERROR_EITHER_NULL = "Error! Either parameter passed is null!";
    private static final String ERROR_DUPLICATE_MATRIC_NUMBER =
            "Error! There is more than 1 student with the Matric Number, %s!";

    @Override
    public CommandResult execute(AppState appState, HashMap<String, String> hashmap) throws TutorLinkException {
        String matricNumber = hashmap.get(ARGUMENT_PREFIXES[0]);
        String componentDescription = hashmap.get(ARGUMENT_PREFIXES[1]);

        if (matricNumber == null || componentDescription == null) {
            throw new IllegalValueException(ERROR_EITHER_NULL);
        }

        //Check number of students with matricNumber
        StudentList filteredList = appState.students.findStudentByMatricNumber(matricNumber);

        if (filteredList.size() == 0) {
            throw new StudentNotFoundException(String.format(STUDENT_NOT_FOUND, matricNumber));
        }

        //Attempt to delete grade
        appState.grades.deleteGrade(matricNumber, componentDescription);
        return new CommandResult(String.format(SUCCESS_MESSAGE, componentDescription, matricNumber));
    }

    @Override
    public String[] getArgumentPrefixes() {
        return ARGUMENT_PREFIXES;
    }
}
