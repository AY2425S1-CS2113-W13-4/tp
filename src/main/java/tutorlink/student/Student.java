package tutorlink.student;

public class Student {
    private String matricNumber;
    private String name;
    private double gpa;

    public Student(String name, String matricNumber) {
        this.name = name;
        this.matricNumber = matricNumber;
    }

    public String getName() {
        return name;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    @Override
    public String toString() {
        return this.name + ", matric no: " + this.matricNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student)) {
            return false;
        }
        Student s = (Student) obj;
        return this.matricNumber.equals(s.getMatricNumber());
    }
}
