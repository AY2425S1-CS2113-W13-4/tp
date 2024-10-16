package tutorlink.commandpackage;

import tutorlink.exceptionspackage.ItemNotFoundException;
import tutorlink.resultpackage.CommandResult;

public class ListAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "list_assignment";
    public static final String FORMAT_ERROR_MESSAGE = "Error, expected format: " + COMMAND_WORD;
    public static final String REGEX = "list_assignment";
    private static final String SUCCESS_MESSAGE = "Here are your assignments:";

    public ListAssignmentCommand() {
    }

    @Override
    public CommandResult execute() {
        try {
            return new CommandResult(SUCCESS_MESSAGE, Command.getAssignmentList());
        } catch (ItemNotFoundException e) {
            return new CommandResult(e.getMessage());
        }

    }
}
