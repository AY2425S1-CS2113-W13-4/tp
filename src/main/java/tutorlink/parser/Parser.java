package tutorlink.parser;

import tutorlink.command.DeleteStudentCommand;
import tutorlink.command.Command;

import java.util.logging.Logger;

public class Parser {
    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    private static final String EMPTY_INPUT_ERROR_MESSAGE = "Error: Input cannot be empty";
    private static final String UNKNOWN_COMMAND_ERROR_MESSAGE = "Error: Unknown command";

    private String extractCommandWord(String input) {
        String[] words = input.split("\\s+");
        return words[0]; //return the first word
    }


    /**
     * Creates a DeleteStudentCommand for deleting a student with the given matriculation number.
     *
     * @param line user input
     * @return a DeleteStudentCommand object to delete the student
     */
    public Command deleteStudentCommand(String line) {
        return new DeleteStudentCommand();
    }


}
