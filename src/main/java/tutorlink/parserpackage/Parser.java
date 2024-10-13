package tutorlink.parserpackage;

import tutorlink.commandpackage.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final String EMPTY_INPUT_ERROR_MESSAGE = "Error: Input cannot be empty";
    private final String UNKNOWN_COMMAND_ERROR_MESSAGE = "Error: Unknown command";

    public Command parse(String line) {
        if(line.trim().isEmpty()){
            return new InvalidCommand(EMPTY_INPUT_ERROR_MESSAGE);
        }
        String commandWord = extractCommandWord(line);
        switch(commandWord){
        case AddStudentCommand.COMMAND_WORD:
            return addStudentCommand(line);
        case DeleteStudentCommand.COMMAND_WORD:
            return deleteStudentCommand(line);
        case ListStudentCommand.COMMAND_WORD:
            return new ListStudentCommand();
        case FindStudentCommand.COMMAND_WORD:
            return findStudentCommand(line);
//        case AddCourseCommand.COMMAND_WORD:
//            break;
//        case DeleteCourseCommand.COMMAND_WORD:
//            break;
//        case ListCourseCommand.COMMAND_WORD:
//            break;
//        case AddAssignmentCommand.COMMAND_WORD:
//            break;
//        case DeleteAssignmentCommand.COMMAND_WORD:
//            break;
//        case ListAssignmentCommand.COMMAND_WORD:
//            break;
        default:
            return new InvalidCommand(UNKNOWN_COMMAND_ERROR_MESSAGE);
        }
    }

    private String extractCommandWord(String input) {
        String words[] = input.split("\\s+");
        return words[0]; //return the first word
    }

    public Command findStudentCommand(String line) {
        Pattern pattern = Pattern.compile(FindStudentCommand.REGEX);
        Matcher matcher = pattern.matcher(line);
        if(!matcher.matches()) {
            return new InvalidCommand(FindStudentCommand.FORMAT_ERROR_MESSAGE);
        }
        int nameIndexStart = line.indexOf("n/") + FindStudentCommand.PREFIX_INDEX;
        int matricIndexStart = line.indexOf("i/") + FindStudentCommand.PREFIX_INDEX;
        String name = null;
        String matricNumber = null;
        if (nameIndexStart < FindStudentCommand.PREFIX_INDEX) {
            matricNumber = line.substring(matricIndexStart).trim();
        } else if (matricIndexStart < FindStudentCommand.PREFIX_INDEX) {
            name = line.substring(nameIndexStart).trim();
        } else {
            name = line.substring(nameIndexStart, matricIndexStart - FindStudentCommand.PREFIX_INDEX).trim();
            matricNumber = line.substring(matricIndexStart).trim();
        }
        return new FindStudentCommand(name, matricNumber);
    }

    /**
     * Creates an AddStudentCommand for adding a new student.
     *
     * @param line input line from the user
     * @return an AddStudentCommand object to add the new student
     */
    public Command addStudentCommand(String line) {
        Pattern pattern = Pattern.compile(AddStudentCommand.REGEX);
        Matcher matcher = pattern.matcher(line);
        if(!matcher.find()) {
            return new InvalidCommand(AddStudentCommand.FORMAT_ERROR_MESSAGE);
        }
        String name = matcher.group(1);
        String matricNumber = matcher.group(2);
        return new AddStudentCommand(name, matricNumber);
    }

    /**
     * Creates a DeleteStudentCommand for deleting a student with the given matriculation number.
     *
     * @param line user input
     * @return a DeleteStudentCommand object to delete the student
     */
    public Command deleteStudentCommand(String line) {
        Pattern pattern = Pattern.compile(DeleteStudentCommand.REGEX);
        Matcher matcher = pattern.matcher(line);
        if(!matcher.matches()) {
            return new InvalidCommand(FindStudentCommand.FORMAT_ERROR_MESSAGE);
        }
        int nameIndexStart = line.indexOf("n/") + FindStudentCommand.PREFIX_INDEX;
        int matricIndexStart = line.indexOf("i/") + FindStudentCommand.PREFIX_INDEX;
        String name = null;
        String matricNumber = null;
        if (nameIndexStart < FindStudentCommand.PREFIX_INDEX) {
            matricNumber = line.substring(matricIndexStart).trim();
        } else if (matricIndexStart < FindStudentCommand.PREFIX_INDEX) {
            name = line.substring(nameIndexStart).trim();
        } else {
            name = line.substring(nameIndexStart, matricIndexStart - FindStudentCommand.PREFIX_INDEX).trim();
            matricNumber = line.substring(matricIndexStart).trim();
        }
        return new DeleteStudentCommand(name, matricNumber);
    }

}
