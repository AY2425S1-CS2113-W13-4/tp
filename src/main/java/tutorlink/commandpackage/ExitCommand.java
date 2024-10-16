package tutorlink.commandpackage;

import tutorlink.resultpackage.CommandResult;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public CommandResult execute() {
        return new CommandResult("Goodbye! See you soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
