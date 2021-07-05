package comp3350.studentlifesimulator.persistence;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Connection;

import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.Student;

public class DatabaseAccess implements DatabaseAccessInterface {
    private final static String DB_TYPE = "HSQL";

    private String database;

    private Statement statement;
    private Connection connection;

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
            statement = connection.createStatement();

            System.out.println("Opened DB path: " + databasePath); // TODO: remove print statements
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            command = "shutdown compact";
            statement.executeQuery(command);
            connection.close();

            System.out.println("Closed DB named: " + database);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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

    public String getDBType() {
        return DB_TYPE;
    }
}
