package comp3350.studentlifesimulator.business;

import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Course;

import java.util.ArrayList;

public class DatabaseManager {
    private static DatabaseAccessInterface database;

    public static void setDatabase(DatabaseAccessInterface newDatabase) {
        database = newDatabase;
    }

    public static void updateStudent(Student student) {
        database.updateStudent(student);
    }

    public static void addCourse(Course course) {
        database.addSelectedCourse(course);
    }

    public static boolean removeCourse(Course course) {
        return database.removeSelectedCourse(course);
    }

    public static Student getStudent() {
        return database.getStudent();
    }

    public static ArrayList<Course> getAvailableCourses() {
        return database.getCourses();
    }

    public static ArrayList<Course> getSelectedCourses() {
        return database.getSelectedCourses();
    }
}
