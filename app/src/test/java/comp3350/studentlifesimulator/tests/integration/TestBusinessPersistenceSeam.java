package comp3350.studentlifesimulator.tests.integration;

import junit.framework.TestCase;

import static org.junit.Assert.*;

import comp3350.studentlifesimulator.application.DatabaseServices;
import comp3350.studentlifesimulator.application.Main;
import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.persistence.DatabaseAccess;
import comp3350.studentlifesimulator.tests.business.TestDatabaseManager;

public class TestBusinessPersistenceSeam extends TestCase {
    TestDatabaseManager testDatabaseManager;
    DatabaseAccess db;

    public TestBusinessPersistenceSeam(String arg0) {
        super(arg0);

        initializeDB();
    }

    public void testStudentAccess() {
        Student initialStudent;

        initializeDB();
        initialStudent = DatabaseManager.getStudent();

        DatabaseManager.updateStudent(new Student("What a name!", new EnergyBar(9), 999));
        assertEquals("What a name!", db.getStudent().getStudentName());
        assertEquals(9, db.getStudent().getCurrentEnergy());
        assertEquals(999, db.getStudent().getScore());

        db.updateStudent(initialStudent);
        assertEquals("Anne Otherstudent", DatabaseManager.getStudent().getStudentName());
        assertEquals(12, DatabaseManager.getStudent().getCurrentEnergy());
        assertEquals(0, DatabaseManager.getStudent().getScore());

        DatabaseServices.closeDatabaseAccess();
    }

    public void testCoursesAccess() {
        initializeDB();

        testDatabaseManager.testStandardCourseSelection();
        DatabaseServices.closeDatabaseAccess();
        initializeDB();
        assertEquals("COMP1010", db.getSelectedCourses().get(0).getCourseID());
        assertEquals("COMP1020", db.getSelectedCourses().get(1).getCourseID());
        assertEquals("COMP2140", db.getSelectedCourses().get(2).getCourseID());
        assertEquals("COMP2150", db.getSelectedCourses().get(3).getCourseID());

        assertTrue(db.removeSelectedCourse(DatabaseManager.getSelectedCourses().get(0)));
        assertEquals("COMP1020", DatabaseManager.getSelectedCourses().get(0).getCourseID());
        assertTrue(db.removeSelectedCourse(DatabaseManager.getSelectedCourses().get(0)));
        assertEquals("COMP2140", DatabaseManager.getSelectedCourses().get(0).getCourseID());
        assertTrue(db.removeSelectedCourse(DatabaseManager.getSelectedCourses().get(0)));
        assertEquals("COMP2150", DatabaseManager.getSelectedCourses().get(0).getCourseID());
        assertTrue(db.removeSelectedCourse(DatabaseManager.getSelectedCourses().get(0)));
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> DatabaseManager.getSelectedCourses().get(0).getCourseID()
        );

        DatabaseServices.closeDatabaseAccess();
    }

    public void testTimeAccess() {
        Time initialTime;

        initializeDB();
        initialTime = DatabaseManager.getTime();

        DatabaseManager.updateTime(new Time(13, 5));
        assertEquals(3, db.getTime().getCurrentTime());
        assertEquals(5, db.getTime().getTimePerDay());
        assertEquals(3, db.getTime().getDays());

        db.updateTime(initialTime);
        assertEquals(24, DatabaseManager.getTime().getCurrentTime());
        assertEquals(96, DatabaseManager.getTime().getTimePerDay());
        assertEquals(1, DatabaseManager.getTime().getDays());

        DatabaseServices.closeDatabaseAccess();
    }

    public void testActionAccess() {
        initializeDB();

        assertEquals(
                db.getActions(0).get("Nap").getActionName(),
                DatabaseManager.getActions(0).get("Nap").getActionName()
        );
        assertEquals(
                db.getActions(1).get("Listen").getActionName(),
                DatabaseManager.getActions(1).get("Listen").getActionName()
        );
        assertEquals(
                db.getActions(2).get("Hibernate").getActionName(),
                DatabaseManager.getActions(2).get("Hibernate").getActionName()
        );
        assertEquals(
                db.getActions(3).get("Nap").getActionName(),
                DatabaseManager.getActions(3).get("Nap").getActionName()
        );

        DatabaseServices.closeDatabaseAccess();
    }

    public void testSavingRoutine() {
        Student initialStudent;
        Time initialTime;

        initializeDB();
        initialStudent = DatabaseManager.getStudent();
        initialTime = DatabaseManager.getTime();

        testDatabaseManager.testStandardSavingRoutine();
        DatabaseServices.closeDatabaseAccess();
        initializeDB();
        assertEquals(30, db.getTime().getCurrentTime());
        assertEquals(200, db.getStudent().getScore());
        assertEquals(7, db.getStudent().getCurrentEnergy());

        db.updateStudent(initialStudent);
        db.updateTime(initialTime);
        assertEquals(24, DatabaseManager.getTime().getCurrentTime());
        assertEquals(0, DatabaseManager.getStudent().getScore());
        assertEquals(12, DatabaseManager.getStudent().getCurrentEnergy());

        DatabaseServices.closeDatabaseAccess();
    }

    private void initializeDB() {
        testDatabaseManager = new TestDatabaseManager(null);

        DatabaseServices.openDatabaseAccess(Main.getDBName());
        db = (DatabaseAccess)DatabaseServices.getDatabaseAccess();
        testDatabaseManager.setDB(db);
    }
}
