package comp3350.studentlifesimulator.persistence;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.TreeSet;

import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.objects.Weekday;

public class DatabaseAccess implements DatabaseAccessInterface {
    private final String database;

    private Connection connection;
    private Statement statement1, statement2, statement3, statement4, statement5, statement6;
    private ResultSet results1, results2;
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
            statement6 = connection.createStatement();
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
        int currEnergy;
        int score;

        try {
            command = "SELECT * FROM STUDENTS";
            results1 = statement1.executeQuery(command);

            if(results1.next()) {
                studentName = results1.getString("STUDENTNAME");
                currEnergy = results1.getInt("CURRENTENERGY");
                score = results1.getInt("STUDENTSCORE");
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
        ArrayList<Weekday> classDays;
        int classTime;

        try {
            command = "SELECT * FROM COURSES";
            results1 = statement2.executeQuery(command);

            courses = new ArrayList<>();
            while (results1.next()) {
                classDays = new ArrayList<>();
                courseID = results1.getString("COURSEID");
                courseName = results1.getString("COURSENAME");
                classTime = results1.getInt("COURSETIME");

                command = "SELECT * FROM COURSEDATES WHERE COURSEID = '" + courseID + "'";
                results2 = statement6.executeQuery(command);
                while (results2.next()) {
                    classDays.add(Weekday.values()[results2.getInt("DAY")]);
                }

                courses.add(new Course(courseID, courseName , classDays , classTime));
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
        ArrayList<Weekday> classDays;
        int classTime;

        try {
            command = "SELECT * FROM SELECTEDCOURSES";
            results1 = statement3.executeQuery(command);

            courses = new ArrayList<>();
            while (results1.next()) {
                classDays = new ArrayList<>();
                courseID = results1.getString("COURSEID");
                courseName = results1.getString("COURSENAME");
                classTime = results1.getInt("COURSETIME");

                command = "SELECT * FROM COURSEDATES WHERE COURSEID = '" + courseID + "'";
                results2 = statement6.executeQuery(command);
                while (results2.next()) {
                    classDays.add(Weekday.values()[results2.getInt("DAY")]);
                }

                courses.add(new Course(courseID, courseName , classDays , classTime));
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
                    "', '" + course.getCourseName() + "', '" + course.getClassTime() + "')";
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
            statement3.executeUpdate(command);

            deleted = true;
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
            results1 = statement4.executeQuery(command);

            while (results1.next()) {
                actionName = results1.getString("ACTIONNAME");
                energyUnit = results1.getInt("ENERGYUNIT");
                timeUnit = results1.getInt("TIMEUNIT");
                score = results1.getInt("SCORE");
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
            results1 = statement5.executeQuery(command);

            if (results1.next()) {
                currentTime = results1.getInt("CURRENTTIME");
                timeInDay = results1.getInt("TIMEINDAY");
                days = results1.getInt("DAYS");
                System.out.println("Current Time: " + currentTime);
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
