package tutorlink.commandpackage;

import tutorlink.resultpackage.CommandResult;
import tutorlink.studentpackage.StudentClass;

public class DeleteStudentCommand extends FindStudentCommand {

    public static final String COMMAND_WORD = "delete_student";
    public static final String REGEX = "^delete_student (i/(A\\d{7}[A-Z])?( n/[\\w\\d]+)?|n/[\\w\\d]" +
            "+( i/(A\\d{7}[A-Z])?)?)( )?$";

    private static final String SUCCESS_MESSAGE = "Student %s successfully deleted";

    public DeleteStudentCommand(String name, String matricNumber) {
        super(name, matricNumber);
    }

    @Override
    public CommandResult execute() {
        StudentClass studentToRemove = null;
        for (StudentClass student : students.getStudentArrayList()) {
            boolean match = student.getName().equalsIgnoreCase(this.name)
                    || student.getMatricNumber().equals(this.matricNumber);
            if (match) {
                studentToRemove = student;
            }
        }
        if (studentToRemove != null) {
            students.deleteStudent(studentToRemove);
            return new CommandResult(String.format(SUCCESS_MESSAGE, studentToRemove));
        }
        return new CommandResult(String.format(ERROR_MESSAGE, getIdentifier()));
    }
}
