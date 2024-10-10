package TutorLink.command.attendance_command;

import TutorLink.command.Command;
import tutorlink.attendance.Attendance;

public abstract class AttendanceCommand extends Command {
    private Attendance attendance;

    public AttendanceCommand(Attendance attendance) {
        this.attendance = attendance;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
