package tutorlink.list;

import tutorlink.course.Course;

import java.util.ArrayList;

public class CourseList {
    private int numberOfCourses;
    ArrayList<Course> courseArrayList;

    public CourseList() {
        this.numberOfCourses = 0;
        this.courseArrayList = new ArrayList<>();
    }

    public void dropCourse(Course course) {
        this.courseArrayList.remove(course);
    }

    public void addCourse(Course course) {
        this.courseArrayList.add(course);
    }
}
