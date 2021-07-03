package comp3350.studentlifesimulator.persistence;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.Student;

public class DatabaseAccess implements DatabaseAccessInterface {
    private String database;

    private Statement statement1, statement2, statement3;
    private Connection connection;
    private ResultSet result1;

    private String command;

    public DatabaseAccess(String databaseName) {
        database = databaseName;
    }

    public void openDB(String databasePath) {
        String url;

        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + databasePath;
            connection = DriverManager.getConnection(url, "SA", "");
            statement1 = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Opened DB, with path: " + database);
    }

    public void closeDB() {
        try {
            command = "shutdown compact";
            result1 = statement1.executeQuery(command);
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Closed DB, with path: " + database);
    }

    public Student getStudent() {
        return null;
    }

    public void updateStudent(Student newStudent) {
    }

    public ArrayList<Course> getCourses() {
        return null;
    }

    public ArrayList<Course> getSelectedCourses() {
        return null;
    }

    public void addSelectedCourse(Course course) {
    }

    public boolean removeSelectedCourse(Course course) {
        return false;
    }
}
