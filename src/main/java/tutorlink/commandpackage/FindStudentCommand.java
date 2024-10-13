package tutorlink.commandpackage;

import tutorlink.listpackage.StudentList;
import tutorlink.resultpackage.CommandResult;

public class FindStudentCommand extends Command{

    protected String name;
    protected String matricNumber;

    public static final String COMMAND_WORD = "find_student";
    public static final String FORMAT_ERROR_MESSAGE = "Error, expected format: " + COMMAND_WORD
            + " n/NAME {AND/OR} " + "i/MATRIC NUMBER";
    public static final int PREFIX_INDEX = 2;
    public final static String REGEX = "^find_student (i/(A\\d{7}[A-Z])?( n/[\\w\\d]+)?|n/[\\w\\d]" +
            "+( i/(A\\d{7}[A-Z])?)?)( )?$";
    private final String SUCCESS_MESSAGE = "Found the following students:";
    private final String ERROR_MESSAGE = "Student (%s) not found in Student List";
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
            String identifier = (this.name != null ? this.name : "")
                    + (this.matricNumber != null && this.name != null ? ", " : "")
                    + (this.matricNumber != null ? "Matric no: " + this.matricNumber : "");
            return new CommandResult(String.format(ERROR_MESSAGE, identifier));
        }
    }
}
