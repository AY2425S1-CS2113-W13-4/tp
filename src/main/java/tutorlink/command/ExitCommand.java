package tutorlink.command;

import tutorlink.appstate.AppState;
import tutorlink.exceptions.TutorLinkException;
import tutorlink.result.CommandResult;

import java.util.HashMap;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {
    }

    @Override
    public CommandResult execute(AppState state, HashMap<String, String> arguments) throws TutorLinkException {
        return new CommandResult("Goodbye! See you soon!");
    }

    @Override
    public String[] getArgumentPrefixes() {
        return new String[0];
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
