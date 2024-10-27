package tutorlink.command;

import java.util.HashMap;

import tutorlink.exceptions.IllegalValueException;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.lists.StudentList;
import tutorlink.result.CommandResult;
import tutorlink.appstate.AppState;

public class FindStudentCommand extends Command {

    public static final String[] ARGUMENT_PREFIXES = {"i/", "n/"};
    public static final String COMMAND_WORD = "find_student";
    public static final String ERROR_BOTH_NULL = "Error! Both parameters passed are null!";

    @Override
    public CommandResult execute(AppState appstate, HashMap<String, String> hashmap) throws TutorLinkException {
        String matricNumber = hashmap.get(ARGUMENT_PREFIXES[0]);
        String name = hashmap.get(ARGUMENT_PREFIXES[1]);

        if (name == null && matricNumber == null) {
            throw new IllegalValueException(ERROR_BOTH_NULL);
        }

        StudentList students;
        if (hashmap.containsKey(ARGUMENT_PREFIXES[0])) {
            students = appstate.students.findStudentByMatricNumber(matricNumber);
            assert students.getStudentArrayList().size() <= 1; //there should only be 1 unique matric number
        } else {
            students = appstate.students.findStudentByName(name);
        }

        return new CommandResult(students.toString());
    }

    @Override
    public String[] getArgumentPrefixes() {
        return ARGUMENT_PREFIXES;
    }
}
