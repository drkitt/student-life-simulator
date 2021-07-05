package comp3350.studentlifesimulator.business;

import comp3350.studentlifesimulator.application.DatabaseServices;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;
import comp3350.studentlifesimulator.persistence.Database;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Course;

import java.util.ArrayList;

public class DatabaseManager {
    private final static String HSQL = "HSQL";
    private final static String STUB = "STUB";

    private static DatabaseAccessInterface HSQL_DATABASE;
    private static DatabaseAccessInterface STUB_DATABASE = new Database();
    private static DatabaseAccessInterface database;

    public static void switchDatabase() {
        if (database == null) {
            HSQL_DATABASE = DatabaseServices.getDatabaseAccess();
            database = HSQL_DATABASE;
        }
        else {
            if (database.getDBType().equals(HSQL)) {
                database = STUB_DATABASE;
            }
            else if (database.getDBType().equals(STUB)) {
                database = HSQL_DATABASE;
            }
        }
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
