package tutorlink.parser;

import tutorlink.command.AddStudentCommand;
import tutorlink.command.Command;
import tutorlink.command.DeleteComponentCommand;
import tutorlink.command.DeleteGradeCommand;
import tutorlink.command.DeleteStudentCommand;
import tutorlink.command.ExitCommand;
import tutorlink.command.FindStudentCommand;
import tutorlink.command.ListStudentCommand;
import tutorlink.command.AddGradeCommand;
import tutorlink.exceptions.InvalidCommandException;

import java.util.*;
import java.util.logging.Logger;

public class Parser {
    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    private static final String EMPTY_INPUT_ERROR_MESSAGE = "Error: Input cannot be empty";
    private static final String UNKNOWN_COMMAND_ERROR_MESSAGE = "Error: Unknown command";

    private String extractCommandWord(String input) {
        String[] words = input.split("\\s+");
        return words[0]; //return the first word
    }


    public Command getCommand(String line) {
        String commandWord = extractCommandWord(line);

        return switch (commandWord.toLowerCase()) {
        case AddStudentCommand.COMMAND_WORD -> new AddStudentCommand(); // Calls delete command handling method
        case DeleteStudentCommand.COMMAND_WORD -> new DeleteStudentCommand(); // Calls add command handling method
        case FindStudentCommand.COMMAND_WORD -> new FindStudentCommand(); // Lists all students
        case ListStudentCommand.COMMAND_WORD -> new ListStudentCommand(); // Lists all students
        case AddGradeCommand.COMMAND_WORD -> new AddGradeCommand();
        case DeleteGradeCommand.COMMAND_WORD -> new DeleteGradeCommand();
        case DeleteComponentCommand.COMMAND_WORD -> new DeleteComponentCommand();
        case ExitCommand.COMMAND_WORD -> new ExitCommand(); // Lists all students

        default -> throw new InvalidCommandException(UNKNOWN_COMMAND_ERROR_MESSAGE);
        };

    }

    /**
     * Extracts command arguments from the input string based on the given argument prefixes.
     *
     * @param argumentPrefixes an array of valid argument prefixes (e.g., "n/", "i/")
     * @param line             the user input containing command arguments
     * @return a HashMap where keys are the prefixes (e.g., "n/", "i/") and the values are the corresponding arguments
     */
    public HashMap<String, String> getArguments(String[] argumentPrefixes, String line) {
        HashMap<String, String> arguments = new HashMap<>();

        if (argumentPrefixes == null || argumentPrefixes.length == 0) {
            return arguments;
        }

        record PlaceholderValue(String prefix, Integer index) {
        }

        List<PlaceholderValue> prefixIndexes = Arrays.stream(argumentPrefixes).map((arg) ->
                new PlaceholderValue(arg, line.indexOf(arg))
        ).toList();

        prefixIndexes.sort(Comparator.comparingInt(val -> val.index));

        System.out.println(prefixIndexes);

        for (int i = 0; i < prefixIndexes.size(); i++) {
            PlaceholderValue curVal = prefixIndexes.get(i);
            if (curVal.index == -1) {
                continue;
            }

            int nextIndex = i == prefixIndexes.size() - 1 ? line.length() : prefixIndexes.get(i + 1).index;

            arguments.put(curVal.prefix, line.substring(curVal.index + 2, nextIndex));
        }

        return arguments;
    }
}
