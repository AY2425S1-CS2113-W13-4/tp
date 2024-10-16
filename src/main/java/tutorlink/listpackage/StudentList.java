package tutorlink.listpackage;

import tutorlink.exceptionspackage.ItemNotFoundException;
import tutorlink.studentpackage.StudentClass;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudentList extends ItemList {
    private ArrayList<StudentClass> studentArrayList;

    public StudentList() {
        this.studentArrayList = new ArrayList<>();
    }

    public boolean deleteStudent(StudentClass student) {
        return studentArrayList.remove(student);
    }

    public void addStudent(StudentClass student) {
        studentArrayList.add(student);
    }

    public int getNumberOfStudents() {
        return studentArrayList.size();
    }

    public ArrayList<StudentClass> getStudentArrayList() {
        return studentArrayList;
    }

    @Override
    public String toString() {
        return IntStream.range(0, studentArrayList.size())
                .mapToObj(i -> (i + 1) + ": " + studentArrayList.get(i)) // 1-based index
                .collect(Collectors.joining("\n\t"));
    }

    public StudentList filterList(String name, String matricNumber) {
        StudentList filteredList = new StudentList();
        filteredList.studentArrayList = studentArrayList
                .stream()
                .filter(student -> {
                    boolean matchesName = (name == null || student.getName().contains(name));
                    boolean matchesMatricNumber = (matricNumber == null || student.getMatricNumber()
                            .equals(matricNumber));
                    return matchesName && matchesMatricNumber;
                })
                .collect(Collectors.toCollection(ArrayList::new));
        return filteredList;
    }

    public StudentClass getStudent(String matricNumber) {
        for (StudentClass student : studentArrayList) {
            if (student.getMatricNumber().equals(matricNumber)) {
                return student;
            }
        }
        throw new ItemNotFoundException(String.format("Student with Matric Number: %s not found",
                matricNumber));
    }
}
