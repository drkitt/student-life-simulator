package comp3350.studentlifesimulator.business;

import comp3350.studentlifesimulator.application.DatabaseServices;
import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Course;

import java.util.ArrayList;

public class DatabaseManager {
    private static DatabaseAccessInterface database;

    public static void setDatabase() {
        database = DatabaseServices.getDatabaseAccess();
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

    public static ArrayList<Action> getActions(int key) {
        return database.getActions(key);
    }

    public static Time getTime() {
        return database.getTime();
    }

    public static void updateTime(Time time) {
        database.updateTime(time);
    }
}
