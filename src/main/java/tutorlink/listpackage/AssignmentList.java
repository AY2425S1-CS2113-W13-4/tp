package tutorlink.listpackage;

import tutorlink.assignmentpackage.Assignment;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AssignmentList extends ItemList {
    private ArrayList<Assignment> assignmentArrayList;

    public AssignmentList() {
        this.assignmentArrayList = new ArrayList<>();
    }

    public void deleteAssignment(Assignment assignment) {
        assignmentArrayList.remove(assignment);
    }

    public void dropAssignmentByDesc(String assignmentDesc) {
        Assignment assignmentToRemove = null;
        for (Assignment assignment : this.assignmentArrayList) {
            if (assignment.getassignmentDescription().equals(assignmentDesc)) {
                assignmentToRemove = assignment;
            }
        }
        if (assignmentToRemove != null) {
            assignmentArrayList.remove(assignmentToRemove);
        }
    }

    public void addAssignment(Assignment assignment) {
        assignmentArrayList.add(assignment);
    }

    public ArrayList<Assignment> getAssignmentArrayList() {
        return assignmentArrayList;
    }

    @Override
    public String toString() {
        return IntStream
                .range(0, assignmentArrayList.size())
                .mapToObj(i -> (i + 1) + ": " + assignmentArrayList.get(i)) // 1-based index
                .collect(Collectors.joining("\n\t"));
    }
}
