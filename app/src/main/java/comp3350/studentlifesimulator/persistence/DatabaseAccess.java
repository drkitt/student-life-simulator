package comp3350.studentlifesimulator.persistence;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Dictionary;
import java.util.Hashtable;

import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;

public class DatabaseAccess implements DatabaseAccessInterface {
    private final String database;

    private Connection connection;
    private Statement statement1, statement2, statement3, statement4, statement5;
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
            statement1 = connection.createStatement();
            statement2 = connection.createStatement();
            statement3 = connection.createStatement();
            statement4 = connection.createStatement();
            statement5 = connection.createStatement();
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

    public Connection getConnection() {
        return connection;
    }

    public Student getStudent() {
        Student student = null;
        String studentName;
        int currEnergy;
        int score;

        try {
            command = "SELECT * FROM STUDENTS";
            results = statement1.executeQuery(command);

            if(results.next()) {
                studentName = results.getString("STUDENTNAME");
                currEnergy = results.getInt("CURRENTENERGY");
                score = results.getInt("STUDENTSCORE");
                student = new Student(studentName, new EnergyBar(currEnergy), score);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    public void updateStudent(Student newStudent) {
        try {
            command = "UPDATE STUDENTS SET STUDENTNAME = '" + newStudent.getStudentName() +
                    "', CURRENTENERGY = " + newStudent.getCurrentEnergy() + ", STUDENTSCORE = " +
                    newStudent.getScore() + " WHERE STUDENTID = 0";
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
            results = statement2.executeQuery(command);

            courses = new ArrayList<>();
            while (results.next()) {
                courseID = results.getString("COURSEID");
                courseName = results.getString("COURSENAME");
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
            command = "SELECT * FROM SELECTEDCOURSES";
            results = statement3.executeQuery(command);

            courses = new ArrayList<>();
            while (results.next()) {
                courseID = results.getString("COURSEID");
                courseName = results.getString("COURSENAME");
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
            command = "DELETE FROM SELECTEDCOURSES WHERE COURSEID = '" + course.getCourseID() + "'";
            deleted = statement3.executeUpdate(command) != 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return deleted;
    }

    public Dictionary<String, Action> getActions(int key) {
        Dictionary<String, Action> actions = new Hashtable<>();
        String actionName;
        int energyUnit;
        int timeUnit;
        int score;

        try {
            command = "SELECT * FROM ACTIONS WHERE VIEWID = " + key;
            results = statement4.executeQuery(command);

            while (results.next()) {
                actionName = results.getString("ACTIONNAME");
                energyUnit = results.getInt("ENERGYUNIT");
                timeUnit = results.getInt("TIMEUNIT");
                score = results.getInt("SCORE");
                actions.put(actionName, new Action(actionName, energyUnit, timeUnit, score));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return actions;
    }

    public Time getTime() {
        Time time = null;
        int currentTime;
        int timeInDay;
        int days;

        try {
            command = "SELECT * FROM TIMES";
            results = statement5.executeQuery(command);

            if (results.next()) {
                currentTime = results.getInt("CURRENTTIME");
                timeInDay = results.getInt("TIMEINDAY");
                days = results.getInt("DAYS");
                time = new Time(currentTime + (timeInDay * (days - 1)), timeInDay);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return time;
    }

    public void updateTime(Time time) {
        try {
            command = "UPDATE TIMES SET CURRENTTIME = " + time.getCurrentTime() +
                    ", TIMEINDAY = " + time.getTimePerDay() + ", DAYS = " + time.getDays() +
                    " WHERE TIMEID = 0";
            statement5.executeUpdate(command);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
