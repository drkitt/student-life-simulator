package comp3350.studentlifesimulator.persistence;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Connection;

import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;

public class DatabaseAccess implements DatabaseAccessInterface {
    private String database;

    private Connection connection;
    private Statement statement1, statement2, statement3;
    private ResultSet results1, results2, results3;
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
    }

    public void closeDB() {
        try {
            command = "SHUTDOWN COMPACT";
            statement1.executeQuery(command);
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
            results1 = statement1.executeQuery(command);

            if(results1.next()) {
                studentName = results1.getString("STUDENTNAME");
                maxEnergy = results1.getInt("MAXENERGY"); // TODO: Pull max energy from database!!!
                currEnergy = results1.getInt("CURRENTENERGY");
                student = new Student(studentName, new EnergyBar(currEnergy));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    public void updateStudent(Student newStudent) {
        try {
            command = "UPDATE STUDENTS SET STUDENTNAME = " + newStudent.getStudentName() +
                    ", MAXENERGY = " + Student.getMaxEnergy() + ", CURRENTENERGY = " +
                    newStudent.getCurrentEnergy();
            statement1.executeUpdate(command);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Course> getCourses() {
        ArrayList<Course> courses = null;
        String courseID;
        String courseName;

        try {
            command = "SELECT * FROM COURSES";
            results2 = statement2.executeQuery(command);

            courses = new ArrayList<>();
            while (results2.next()) {
                courseID = results2.getString("COURSEID");
                courseName = results2.getString("COURSENAME");
                courses.add(new Course(courseID, courseName));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    public ArrayList<Course> getSelectedCourses() {
        ArrayList<Course> courses = null;
        String courseID;
        String courseName;

        try {
            command = "SELECT * FROM SELECEDCOURSES";
            results3 = statement3.executeQuery(command);

            courses = new ArrayList<>();
            while (results3.next()) {
                courseID = results3.getString("COURSEID");
                courseName = results3.getString("COURSENAME");
                courses.add(new Course(courseID, courseName));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    public void addSelectedCourse(Course course) {
        try {
            command = "INSERT INTO SELECTEDCOURSES VALUES ('" + course.getCourseID() +
                    "', '" + course.getCourseName() + "')";
            statement3.executeUpdate(command);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean removeSelectedCourse(Course course) {
        boolean deleted = false;

        try {
            command = "DELETE FROM SELECTEDCOURSES WHERE COURSEID = " + course.getCourseID();
            statement3.executeUpdate(command);

            deleted = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return deleted;
    }
}
