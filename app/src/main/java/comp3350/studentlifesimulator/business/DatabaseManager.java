package comp3350.studentlifesimulator.business;

import comp3350.studentlifesimulator.persistence.Database;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Course;

import java.util.ArrayList;

public class DatabaseManager {
    private final static Database DATABASE = new Database();

    public static void updateStudent(Student student) {
        DATABASE.updateStudentState(student);
    }

    public static void addCourse(Course course) {
        DATABASE.addSelectedCourse(course);
    }

    public static void removeCourse(Course course) {
        DATABASE.removeSelectedCourse(course);
    }

    public static Student getStudent() {
        return DATABASE.getStudent();
    }

    public static ArrayList<Course> getAvailableCourses() {
        return DATABASE.getCourses();
    }

    public static ArrayList<Course> getSelectedCourses() {
        return DATABASE.getSelectedCourses();
    }
}
