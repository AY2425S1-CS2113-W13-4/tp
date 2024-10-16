package tutorlink.parserpackage;

import tutorlink.commandpackage.AddAssignmentCommand;
import tutorlink.commandpackage.FindStudentCommand;
import tutorlink.commandpackage.AddStudentCommand;
import tutorlink.commandpackage.DeleteStudentCommand;
import tutorlink.commandpackage.InvalidCommand;
import tutorlink.commandpackage.ListAssignmentCommand;
import tutorlink.commandpackage.ListStudentCommand;
import tutorlink.commandpackage.DeleteAssignmentCommand;
import tutorlink.commandpackage.ExitCommand;
import tutorlink.commandpackage.Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String EMPTY_INPUT_ERROR_MESSAGE = "Error: Input cannot be empty";
    private static final String UNKNOWN_COMMAND_ERROR_MESSAGE = "Error: Unknown command";

    public Command parse(String line) {
        if (line.trim().isEmpty()) {
            return new InvalidCommand(EMPTY_INPUT_ERROR_MESSAGE);
        }
        String commandWord = extractCommandWord(line);
        switch (commandWord) {
        case AddStudentCommand.COMMAND_WORD:
            return addStudentCommand(line);
        case DeleteStudentCommand.COMMAND_WORD:
            return deleteStudentCommand(line);
        case ListStudentCommand.COMMAND_WORD:
            return new ListStudentCommand();
        case FindStudentCommand.COMMAND_WORD:
            return findStudentCommand(line);
        case AddAssignmentCommand.COMMAND_WORD:
            return addAssignmentCommand(line);
        case ListAssignmentCommand.COMMAND_WORD:
            return listAssignmentCommand(line);
        case DeleteAssignmentCommand.COMMAND_WORD:
            return deleteAssignmentCommand(line);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand(UNKNOWN_COMMAND_ERROR_MESSAGE);
        }
    }

    private Command listAssignmentCommand(String line) {
        Pattern pattern = Pattern.compile(ListAssignmentCommand.REGEX);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return new ListAssignmentCommand();
        } else {
            return new InvalidCommand(ListAssignmentCommand.FORMAT_ERROR_MESSAGE);
        }
    }

    private String extractCommandWord(String input) {
        String[] words = input.split("\\s+");
        return words[0]; //return the first word
    }

    public Command addAssignmentCommand(String line) {
        Pattern pattern = Pattern.compile(AddAssignmentCommand.REGEX);
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
            return new InvalidCommand(AddAssignmentCommand.FORMAT_ERROR_MESSAGE);
        }
        String matricNumber = matcher.group(1);
        String courseID = matcher.group(2);
        double receivedScore = Double.parseDouble(matcher.group(3));
        double totalScore = Double.parseDouble(matcher.group(5));
        double weighting = Double.parseDouble(matcher.group(7));
        if (weighting > 1.0) {
            return new InvalidCommand(AddAssignmentCommand.INVALID_WEIGHTING_MESSAGE);
        }
        return new AddAssignmentCommand(matricNumber, courseID, receivedScore, totalScore, weighting);
    }

    public Command findStudentCommand(String line) {
        Pattern pattern = Pattern.compile(FindStudentCommand.REGEX);
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches()) {
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
        if (!matcher.find()) {
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
        if (!matcher.matches()) {
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

    private Command deleteAssignmentCommand(String line) {
        Pattern pattern = Pattern.compile(DeleteAssignmentCommand.REGEX);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            return new InvalidCommand(DeleteAssignmentCommand.FORMAT_ERROR_MESSAGE);
        }

        String matricNumber = matcher.group(1);
        String courseID = matcher.group(2);
        String assignmentDesc = matcher.group(3);
        return new DeleteAssignmentCommand(matricNumber, courseID, assignmentDesc);
    }

}
