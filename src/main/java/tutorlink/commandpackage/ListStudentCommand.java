package tutorlink.commandpackage;

import tutorlink.resultpackage.CommandResult;

public class ListStudentCommand extends Command {

    public static final String COMMAND_WORD = "list_student";
    private static final String SUCCESS_MESSAGE = "Here are your students:";

    @Override
    public CommandResult execute() {
        return new CommandResult(SUCCESS_MESSAGE, students);
    }

}
