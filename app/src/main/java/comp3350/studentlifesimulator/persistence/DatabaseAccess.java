package comp3350.studentlifesimulator.persistence;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Connection;

import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;

public class DatabaseAccess implements DatabaseAccessInterface {
    private String database;

    private Connection connection;
    private Statement statement;
    private ResultSet results;
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            command = "SHUTDOWN COMPACT";
            statement.executeQuery(command);
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student getStudent() {
        Student student = null;
        String studentName;
        int maxEnergy;
        int currEnergy;

        try {
            command = "SELECT * FROM STUDENTS";
            results = statement.executeQuery(command);

            if(results.next()) {
                studentName = results.getString("STUDENTNAME");
                maxEnergy = results.getInt("MAXENERGY"); // TODO: do something with max energy
                currEnergy = results.getInt("CURRENTENERGY");
                student = new Student(studentName,new EnergyBar(currEnergy));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    public void updateStudent(Student newStudent) {
        try {
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Course> getCourses() {
        try {
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Course> getSelectedCourses() {
        try {
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addSelectedCourse(Course course) {
        try {
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean removeSelectedCourse(Course course) {
        try {
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
