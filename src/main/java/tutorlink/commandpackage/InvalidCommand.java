package tutorlink.commandpackage;

import tutorlink.resultpackage.CommandResult;

public class InvalidCommand extends Command {
    private String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(errorMessage);
    }
}
