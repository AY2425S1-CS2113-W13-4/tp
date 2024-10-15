package tutorlink.listpackage;

import tutorlink.coursepackage.Course;
import tutorlink.exceptionspackage.ItemNotFoundException;

import java.util.ArrayList;

public class CourseList extends ItemList {
    private ArrayList<Course> courseArrayList;

    public CourseList() {
        this.courseArrayList = new ArrayList<>();
    }

    public void dropCourse(String courseID) {
        Course courseToRemove = null;
        for (Course course : this.courseArrayList) {
            if (course.getCourseID().equals(courseID)) {
                courseToRemove = course;
            }
        }
        if(courseToRemove != null) {
            courseArrayList.remove(courseToRemove);
        }
    }

    public void addCourse(Course course) {
        this.courseArrayList.add(course);
    }

    public ArrayList<Course> getCourseArrayList() {
        return courseArrayList;
    }

    public Course getCourseByID(String courseID) {
        for (Course course : courseArrayList) {
            if (course.getCourseID().equals(courseID)) {
                return course;
            }
        }
        throw new ItemNotFoundException(String.format("Course with course ID: %s not found",
                courseID));
    }
}
