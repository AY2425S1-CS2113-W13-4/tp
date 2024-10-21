package tutorlink.command;

import java.util.HashMap;

import tutorlink.appstate.AppState;
import tutorlink.result.CommandResult;

public class ListStudentCommand extends Command {

    public static final String COMMAND_WORD = "list_student";
    private static final String SUCCESS_MESSAGE = "Here are your students:";

    @Override
    public CommandResult execute(AppState appState, HashMap<String, String> hashMap) {
        return new CommandResult(appState.students.toString());
    }

    @Override
    public String[] getArgumentPrefixes() {
        return new String[0];
    }
}
