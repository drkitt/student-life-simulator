package comp3350.studentlifesimulator.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

import comp3350.studentlifesimulator.application.DatabaseServices;
import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;
import comp3350.studentlifesimulator.objects.Weekday;
import comp3350.studentlifesimulator.tests.persistence.StubDatabase;

import static org.junit.Assert.*;

public class TestDatabaseManager extends TestCase {
    DatabaseAccessInterface db;

    public TestDatabaseManager(String arg0) {
        super(arg0);

        db = new StubDatabase();
    }

    public void setDB(DatabaseAccessInterface database) {
        db = database;
    }

    public void testStandardCourseSelection() {
        DatabaseServices.openDatabaseAccess(db);
        ArrayList<Course> courses = DatabaseManager.getAvailableCourses();

        assertEquals("COMP1010", courses.get(0).getCourseID());

        DatabaseManager.addCourse(courses.get(4));
        assertEquals(courses.get(4).getCourseID(),
                DatabaseManager.getSelectedCourses().get(0).getCourseID());

        assertTrue(DatabaseManager.removeCourse(courses.get(4)));
        assertEquals(0, DatabaseManager.getSelectedCourses().size());

        DatabaseManager.addCourse(courses.get(0));
        DatabaseManager.addCourse(courses.get(1));
        DatabaseManager.addCourse(courses.get(2));
        DatabaseManager.addCourse(courses.get(3));

        assertEquals(
                "COMP1010",
                DatabaseManager.getSelectedCourses().get(0).getCourseID()
        );
        assertEquals(
                "COMP1020",
                DatabaseManager.getSelectedCourses().get(1).getCourseID()
        );
        assertEquals(
                "COMP2140",
                DatabaseManager.getSelectedCourses().get(2).getCourseID()
        );
        assertEquals(
                "COMP2150",
                DatabaseManager.getSelectedCourses().get(3).getCourseID()
        );

        DatabaseServices.closeDatabaseAccess();
    }

    public void testCharacterCreation() {
        DatabaseServices.openDatabaseAccess(db);

        assertEquals("eyes_glasses", DatabaseManager.getEyes());
        assertEquals("hair4_medium", DatabaseManager.getHair());
        assertEquals("skin_fair", DatabaseManager.getSkinColour());
        assertEquals("shirt_purple_featuring_whee", DatabaseManager.getShirt());

        DatabaseManager.updateEyes("eye_string");
        DatabaseManager.updateHair("hair_string");
        DatabaseManager.updateSkinColour("skin_string");
        DatabaseManager.updateShirt("shirt_string");
        assertEquals("eye_string", DatabaseManager.getEyes());
        assertEquals("hair_string", DatabaseManager.getHair());
        assertEquals("skin_string", DatabaseManager.getSkinColour());
        assertEquals("shirt_string", DatabaseManager.getShirt());

        DatabaseServices.closeDatabaseAccess();
    }

    public void testStandardSavingRoutine() {
        DatabaseServices.openDatabaseAccess(db);

        assertEquals(24, DatabaseManager.getTime().getCurrentTime());
        assertEquals(0, DatabaseManager.getStudent().getScore());
        assertEquals(12, DatabaseManager.getStudent().getCurrentEnergy());

        assertEquals(1, DatabaseManager.getActions(0).size());
        assertEquals(4,DatabaseManager.getActions(1).size());
        assertEquals(3,DatabaseManager.getActions(2).size());
        assertEquals(6,DatabaseManager.getActions(3).size());

        assertEquals("Nap", DatabaseManager.getActions(0).get("Nap").getActionName());

        Student student = DatabaseManager.getStudent();
        Time time = DatabaseManager.getTime();
        student.doAction(new Action("Test", -5, 1, 100));
        time.addToTime(6);
        student.addToScore(100);
        DatabaseManager.updateTime(time);
        DatabaseManager.updateStudent(student);

        assertEquals(30, DatabaseManager.getTime().getCurrentTime());
        assertEquals(200, DatabaseManager.getStudent().getScore());
        assertEquals(7, DatabaseManager.getStudent().getCurrentEnergy());

        DatabaseServices.closeDatabaseAccess();
    }

    public void testUnexpectedDatabaseRoutines() {
        DatabaseServices.openDatabaseAccess(db);

        assertFalse(DatabaseManager.removeCourse(new Course(
                "",
                "",
                new ArrayList<>(Collections.singletonList(Weekday.Monday)),
                32
        )));
        assertEquals(0, DatabaseManager.getSelectedCourses().size());

        DatabaseManager.updateStudent(
                new Student("", new EnergyBar(10), -100)
        );
        assertEquals(-100, DatabaseManager.getStudent().getScore());

        DatabaseServices.closeDatabaseAccess();
    }

    public void testInvalidDatabaseInteractions() {
        DatabaseServices.openDatabaseAccess(db);

        assertThrows(
                NullPointerException.class,
                ()->DatabaseManager.updateStudent(null)
        );
        assertThrows(
                ArrayIndexOutOfBoundsException.class,
                ()->DatabaseManager.getActions(4)
        );
        assertThrows(
                ArrayIndexOutOfBoundsException.class,
                ()->DatabaseManager.getActions(-1)
        );
        assertThrows(
                NullPointerException.class,
                ()->DatabaseManager.updateTime(null)
        );
        assertThrows(
                NullPointerException.class,
                ()->DatabaseManager.addCourse(null)
        );
        DatabaseManager.addCourse(new Course(
                "TEMP",
                "DATA",
                new ArrayList<>(Collections.singletonList(Weekday.Monday)),
                32
        ));
        assertThrows(
                NullPointerException.class,
                ()->DatabaseManager.removeCourse(null)
        );

        DatabaseServices.closeDatabaseAccess();
    }
}
