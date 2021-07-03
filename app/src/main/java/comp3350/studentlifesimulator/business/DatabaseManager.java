package comp3350.studentlifesimulator.business;

import comp3350.studentlifesimulator.persistence.Database;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;

import java.util.ArrayList;

public class DatabaseManager {
    private final static DatabaseAccessInterface DATABASE = new Database(); // TODO: modify to work with stub and main

    public static void updateStudent(Student student) {
        DATABASE.updateStudent(student);
    }

    public static void addCourse(Course course) {
        DATABASE.addSelectedCourse(course);
    }

    public static boolean removeCourse(Course course) {
        return DATABASE.removeSelectedCourse(course);
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
