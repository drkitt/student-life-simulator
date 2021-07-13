package comp3350.studentlifesimulator.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.studentlifesimulator.application.DatabaseServices;
import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.tests.persistence.StubDatabase;

import static org.junit.Assert.*;

public class TestDatabaseManager extends TestCase {
    public TestDatabaseManager(String arg0) { super(arg0); }

    public void testStandardCourseSelection() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());
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
        for (int i = 0; i < 4; i++) {
            assertEquals(courses.get(i).getCourseID(),
                    DatabaseManager.getSelectedCourses().get(i).getCourseID());
        }

        DatabaseServices.closeDatabaseAccess();
    }

    public void testStandardSavingRoutine() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());

        assertEquals(24, DatabaseManager.getTime().getCurrentTime());
        assertEquals(0, DatabaseManager.getStudent().getScore());
        assertEquals(12, DatabaseManager.getStudent().getCurrentEnergy());
        for (int i = 0; i < 4; i++) {
            assertTrue(DatabaseManager.getActions(i).size() > 0);
        }
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
        DatabaseServices.openDatabaseAccess(new StubDatabase());

        assertFalse(DatabaseManager.removeCourse(new Course("", "")));
        assertEquals(0, DatabaseManager.getSelectedCourses().size());

        DatabaseManager.updateStudent(new Student("", new EnergyBar(10),
                -100));
        assertEquals(-100, DatabaseManager.getStudent().getScore());

        DatabaseServices.closeDatabaseAccess();
    }

    public void testInvalidDatabaseInteractions() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());

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
        DatabaseManager.addCourse(new Course("TEMP", "DATA"));
        assertThrows(
                NullPointerException.class,
                ()->DatabaseManager.removeCourse(null)
        );

        DatabaseServices.closeDatabaseAccess();
    }
}
