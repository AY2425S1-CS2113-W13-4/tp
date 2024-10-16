package tutorlink.studentpackage;

public class StudentClass {
    private String matricNumber;
    private String name;
    private double gpa;

    public StudentClass(String name, String matricNumber) {
        this.name = name;
        this.matricNumber = matricNumber;
        this.gpa = 5.0; // Default gpa value if not given
    }

    public StudentClass(String name, String matricNumber, double gpa) {
        this.matricNumber = matricNumber;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return this.name + ", matric no: " + this.matricNumber + ", GPA: " + this.gpa;
    }
}
