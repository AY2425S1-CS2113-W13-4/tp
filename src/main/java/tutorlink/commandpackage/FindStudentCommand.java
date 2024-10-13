package tutorlink.commandpackage;

import tutorlink.listpackage.StudentList;
import tutorlink.resultpackage.CommandResult;

public class FindStudentCommand extends Command{

    protected String name;
    protected String matricNumber;

    public static final String COMMAND_WORD = "find_student";
    public static final String FORMAT_ERROR_MESSAGE = "Error, expected format: " + COMMAND_WORD
            + " n/NAME AND/OR" + "i/MATRIC NUMBER";
    public final static String REGEX = "^findstudent(?:\\s+n/([^\\\\s]+))?(?:\\s+i/(\\d{7}[a-zA-Z]?))?$";
    private final String SUCCESS_MESSAGE = "Found the following students:";
    private final String ERROR_MESSAGE = "No students found";
    public FindStudentCommand(String name, String matricNumber) {
        this.name = name;
        this.matricNumber = matricNumber;
    }

    @Override
    public CommandResult execute(){
        StudentList filteredList = students.filterList(this.name, this.matricNumber);
        if(filteredList.getNumberOfStudents() > 0) {
            return new CommandResult(SUCCESS_MESSAGE, filteredList);
        } else {
            String identifier = (this.name != null ? this.name : "") + ", "
                    + (this.name != null ? this.name : "");
            return new CommandResult(String.format(ERROR_MESSAGE, identifier));
        }
    }
}
