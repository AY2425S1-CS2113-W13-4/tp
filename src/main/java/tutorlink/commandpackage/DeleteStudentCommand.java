package tutorlink.commandpackage;

import tutorlink.listpackage.StudentList;
import tutorlink.resultpackage.CommandResult;

public class DeleteStudentCommand extends FindStudentCommand{

    public static final String COMMAND_WORD = "delete_student";
    public static final String REGEX = "^delete_student (i/(A\\d{7}[A-Z])?( n/[\\w\\d]+)?|n/[\\w\\d]" +
            "+( i/(A\\d{7}[A-Z])?)?)( )?$";

    private static final String SUCCESS_MESSAGE = "Student %s successfully deleted";

    public DeleteStudentCommand(String name, String matricNumber) {
        super(name, matricNumber);
    }

    @Override
    public CommandResult execute() {
        StudentList result = new StudentList();
        students.getStudentArrayList().removeIf(student -> {
            boolean matches = student.getName().equalsIgnoreCase(this.name) ||
                    student.getMatricNumber().equals(this.matricNumber);
            if (matches) {
                result.getStudentArrayList().add(student); // Add to the removed list
            }
            return matches; // Remove from the original list
        });
        return new CommandResult(SUCCESS_MESSAGE, result);
    }
}
