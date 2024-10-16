package tutorlink.commandpackage;

import tutorlink.listpackage.AssignmentList;
import tutorlink.listpackage.StudentList;
import tutorlink.resultpackage.CommandResult;

/**
 * Represents an interpreted Command from the user. A <code>Command</code> object corresponds to a
 * single user-issued command from the terminal.
 */
public abstract class Command {

    protected static StudentList students = new StudentList();
    protected static AssignmentList assignments = new AssignmentList();

    /**
     * Executes the required operations to perform the command issued by the user.
     */
    public abstract CommandResult execute();

    /**
     * Checks if the command is an exit command.
     *
     * @return whether the current command is an ExitCommand
     */
    public boolean isExit() {
        return false;
    }

    public static StudentList getStudentList() {
        return students;
    }

    public static AssignmentList getAssignmentList() {
        return assignments;
    }
}

