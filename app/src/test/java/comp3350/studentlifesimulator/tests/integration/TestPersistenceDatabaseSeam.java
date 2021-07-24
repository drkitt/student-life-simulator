package comp3350.studentlifesimulator.tests.integration;

import junit.framework.TestCase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import comp3350.studentlifesimulator.application.DatabaseServices;
import comp3350.studentlifesimulator.application.Main;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.persistence.DatabaseAccess;
import comp3350.studentlifesimulator.tests.persistence.TestDatabase;

public class TestPersistenceDatabaseSeam extends TestCase {
    private Statement statement;
    private TestDatabase testDb;
    private DatabaseAccess db;

    public TestPersistenceDatabaseSeam(String arg0) {
        super(arg0);

        initializeDB();
    }

    public void testStudentPersistence() {
        ResultSet results;
        Student initialStudent;

        initializeDB();
        initialStudent = db.getStudent();

        testDb.testStudent();
        DatabaseServices.closeDatabaseAccess();
        initializeDB();
        try {
            results = statement.executeQuery("SELECT * FROM STUDENTS");
            assertTrue(results.next());
            assertEquals("Over 9000", results.getString("STUDENTNAME"));
            assertEquals(0, results.getInt("CURRENTENERGY"));
            assertEquals(9999, results.getInt("STUDENTSCORE"));

            statement.executeUpdate("UPDATE STUDENTS SET STUDENTNAME = '" +
                    initialStudent.getStudentName() + "', CURRENTENERGY = " +
                    initialStudent.getCurrentEnergy() + ", STUDENTSCORE = " +
                    initialStudent.getScore() + " WHERE STUDENTID = 0");
            assertEquals("Anne Otherstudent", db.getStudent().getStudentName());
            assertEquals(12, db.getStudent().getCurrentEnergy());
            assertEquals(0, db.getStudent().getScore());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseServices.closeDatabaseAccess();
    }

    public void testCoursePersistence() {
        ResultSet results;

        initializeDB();

        testDb.testCourses();
        DatabaseServices.closeDatabaseAccess();
        initializeDB();
        try {
            results = statement.executeQuery("SELECT * FROM SELECTEDCOURSES");

            assertTrue(results.next());
            assertEquals("COMP1010", new Course(
                    results.getString("COURSEID"),
                    results.getString("COURSENAME")
            ).getCourseID());
            assertEquals("Introductory Computer Science 1", new Course(
                    results.getString("COURSEID"),
                    results.getString("COURSENAME")
            ).getCourseName());

            assertTrue(results.next());
            assertEquals("COMP1020", new Course(
                    results.getString("COURSEID"),
                    results.getString("COURSENAME")
            ).getCourseID());
            assertEquals("Introductory Computer Science 2", new Course(
                    results.getString("COURSEID"),
                    results.getString("COURSENAME")
            ).getCourseName());

            assertTrue(results.next());
            assertEquals("COMP2140", new Course(
                    results.getString("COURSEID"),
                    results.getString("COURSENAME")
            ).getCourseID());
            assertEquals("Data Structures and Algorithms", new Course(
                    results.getString("COURSEID"),
                    results.getString("COURSENAME")
            ).getCourseName());

            assertTrue(results.next());
            assertEquals("COMP2150", new Course(
                    results.getString("COURSEID"),
                    results.getString("COURSENAME")
            ).getCourseID());
            assertEquals("Object Orientation", new Course(
                    results.getString("COURSEID"),
                    results.getString("COURSENAME")
            ).getCourseName());

            assertTrue(results.next());
            assertEquals("COMP2160", new Course(
                    results.getString("COURSEID"),
                    results.getString("COURSENAME")
            ).getCourseID());
            assertEquals("Programming Practices", new Course(
                    results.getString("COURSEID"),
                    results.getString("COURSENAME")
            ).getCourseName());

            assertEquals("COMP1010", db.getSelectedCourses().get(0).getCourseID());
            assertEquals(
                    "Introductory Computer Science 1",
                    db.getSelectedCourses().get(0).getCourseName()
            );

            assertFalse(statement.executeUpdate("DELETE FROM SELECTEDCOURSES WHERE COURSEID = 'COMP1010'") == 0);
            assertEquals("COMP1020", db.getSelectedCourses().get(0).getCourseID());
            assertEquals(
                    "Introductory Computer Science 2",
                    db.getSelectedCourses().get(0).getCourseName()
            );

            assertFalse(statement.executeUpdate("DELETE FROM SELECTEDCOURSES WHERE COURSEID = 'COMP1020'") == 0);
            assertEquals("COMP2140", db.getSelectedCourses().get(0).getCourseID());
            assertEquals(
                    "Data Structures and Algorithms",
                    db.getSelectedCourses().get(0).getCourseName()
            );

            assertFalse(statement.executeUpdate("DELETE FROM SELECTEDCOURSES WHERE COURSEID = 'COMP2140'") == 0);
            assertEquals("COMP2150", db.getSelectedCourses().get(0).getCourseID());
            assertEquals(
                    "Object Orientation",
                    db.getSelectedCourses().get(0).getCourseName()
            );

            assertFalse(statement.executeUpdate("DELETE FROM SELECTEDCOURSES WHERE COURSEID = 'COMP2150'") == 0);
            assertEquals("COMP2160", db.getSelectedCourses().get(0).getCourseID());
            assertEquals(
                    "Programming Practices",
                    db.getSelectedCourses().get(0).getCourseName()
            );

            assertFalse(statement.executeUpdate("DELETE FROM SELECTEDCOURSES WHERE COURSEID = 'COMP2160'") == 0);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseServices.closeDatabaseAccess();
    }

    public void testActionPersistence() {
        ResultSet results;

        initializeDB();

        testDb.testActions();
        DatabaseServices.closeDatabaseAccess();
        initializeDB();
        try {
            results = statement.executeQuery("SELECT * FROM ACTIONS WHERE VIEWID = 0");
            assertTrue(results.next());
            assertEquals("Nap", results.getString("ACTIONNAME"));
            assertEquals(1, results.getInt("ENERGYUNIT"));
            assertEquals(4, results.getInt("TIMEUNIT"));
            assertEquals(2, results.getInt("SCORE"));

            results = statement.executeQuery("SELECT * FROM ACTIONS WHERE VIEWID = 1");
            assertTrue(results.next());
            assertTrue(results.next());
            assertTrue(results.next());
            assertTrue(results.next());
            assertEquals("Quick Study", results.getString("ACTIONNAME"));
            assertEquals(-1, results.getInt("ENERGYUNIT"));
            assertEquals(4, results.getInt("TIMEUNIT"));
            assertEquals(0, results.getInt("SCORE"));

            results = statement.executeQuery("SELECT * FROM ACTIONS WHERE VIEWID = 2");
            assertTrue(results.next());
            assertEquals("Hibernate", results.getString("ACTIONNAME"));
            assertEquals(12, results.getInt("ENERGYUNIT"));
            assertEquals(48, results.getInt("TIMEUNIT"));
            assertEquals(12, results.getInt("SCORE"));

            results = statement.executeQuery("SELECT * FROM ACTIONS WHERE VIEWID = 3");
            assertTrue(results.next());
            assertTrue(results.next());
            assertTrue(results.next());
            assertTrue(results.next());
            assertTrue(results.next());
            assertEquals("Sleep", results.getString("ACTIONNAME"));
            assertEquals(5, results.getInt("ENERGYUNIT"));
            assertEquals(16, results.getInt("TIMEUNIT"));
            assertEquals(6, results.getInt("SCORE"));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        DatabaseServices.closeDatabaseAccess();
    }

    public void testTimePersistence() {
        ResultSet results;
        Time initialTime;

        initializeDB();
        initialTime = db.getTime();

        testDb.testTime();
        DatabaseServices.closeDatabaseAccess();
        initializeDB();
        try {
            results = statement.executeQuery("SELECT * FROM TIMES");

            assertTrue(results.next());
            assertEquals(42, results.getInt("CURRENTTIME"));
            assertEquals(11, results.getInt("DAYS"));
            assertEquals(100, results.getInt("TIMEINDAY"));
            assertEquals(42, results.getInt("CURRENTTIME"));

            statement.executeUpdate("UPDATE TIMES SET CURRENTTIME = " + initialTime.getCurrentTime() +
                    ", TIMEINDAY = " + initialTime.getTimePerDay() + ", DAYS = " + initialTime.getDays() +
                    " WHERE TIMEID = 0");
            assertEquals(24, db.getTime().getCurrentTime());
            assertEquals(1, db.getTime().getDays());
            assertEquals(96, db.getTime().getTimePerDay());
            assertEquals(24, db.getTime().getStartTime());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseServices.closeDatabaseAccess();
    }

    private void initializeDB() {
        testDb = new TestDatabase(null);

        DatabaseServices.openDatabaseAccess(Main.getDBName());
        db = (DatabaseAccess)DatabaseServices.getDatabaseAccess();
        try {
            statement = db.getConnection().createStatement();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        TestDatabase.setDB(db);
    }
}
