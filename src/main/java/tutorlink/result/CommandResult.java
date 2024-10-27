package tutorlink.result;

public class CommandResult {

    public final String message;

    public CommandResult(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
