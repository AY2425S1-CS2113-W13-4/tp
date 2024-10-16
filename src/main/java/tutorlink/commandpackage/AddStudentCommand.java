package tutorlink.commandpackage;

import tutorlink.exceptionspackage.IllegalValueException;
import tutorlink.resultpackage.CommandResult;
import tutorlink.studentpackage.StudentClass;

public class AddStudentCommand extends Command {

    public static final String COMMAND_WORD = "add_student";
    public static final String FORMAT_ERROR_MESSAGE = "Error, expected format: " + COMMAND_WORD + " n/NAME " +
            "i/MATRIC NUMBER";
    public static final String REGEX = "^add_student\\s+n/(.+?)\\s+i/(A\\d{7}[a-zA-Z])$";

    private static final String SUCCESSFUL_MESSAGE = "Student (%s) added successfully";
    private String matricNumber;
    private String name;

    public AddStudentCommand(String name, String matricNumber) {
        this.name = name;
        this.matricNumber = matricNumber;
    }

    @Override
    public CommandResult execute() {
        try {
            StudentClass studentToAdd = new StudentClass(name, matricNumber);
            students.addStudent(studentToAdd);
            return new CommandResult(String.format(SUCCESSFUL_MESSAGE, studentToAdd));
        } catch (IllegalValueException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
