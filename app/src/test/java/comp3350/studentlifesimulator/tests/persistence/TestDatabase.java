package comp3350.studentlifesimulator.tests.persistence;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;

import static org.junit.Assert.*;

public class TestDatabase extends TestCase {
    private static DatabaseAccessInterface db;

    public TestDatabase(String arg0) {
        super(arg0);

        setDb(new StubDatabase());
        db.openDB(null);
    }

    public static void setDb(DatabaseAccessInterface database) {
        db = database;
    }

    public void testStudent() {
        assertEquals("Anne Otherstudent", db.getStudent().getStudentName());
        assertEquals(12, db.getStudent().getCurrentEnergy());
        assertEquals(0, db.getStudent().getScore());

        db.updateStudent(new Student("Over 9000",
                new EnergyBar(0), 9999));
        assertEquals("Over 9000", db.getStudent().getStudentName());
        assertEquals(0, db.getStudent().getCurrentEnergy());
        assertEquals(9999, db.getStudent().getScore());
    }

    public void testCourses() {
        assertEquals(5, db.getCourses().size());
        assertEquals("COMP1010", db.getCourses().get(0).getCourseID());
        assertEquals(
                "Introductory Computer Science 1",
                db.getCourses().get(0).getCourseName()
        );
        assertEquals("COMP1020", db.getCourses().get(1).getCourseID());
        assertEquals(
                "Introductory Computer Science 2",
                db.getCourses().get(1).getCourseName()
        );
        assertEquals("COMP2140", db.getCourses().get(2).getCourseID());
        assertEquals(
                "Data Structures and Algorithms",
                db.getCourses().get(2).getCourseName()
        );
        assertEquals("COMP2150", db.getCourses().get(3).getCourseID());
        assertEquals(
                "Object Orientation",
                db.getCourses().get(3).getCourseName()
        );
        assertEquals("COMP2160", db.getCourses().get(4).getCourseID());
        assertEquals(
                "Programming Practices",
                db.getCourses().get(4).getCourseName()
        );

        assertEquals(0, db.getSelectedCourses().size());
        db.addSelectedCourse(db.getCourses().get(4));
        assertEquals("COMP2160", db.getSelectedCourses().get(0).getCourseID());
        assertEquals(
                "Programming Practices",
                db.getSelectedCourses().get(0).getCourseName()
        );

        db.addSelectedCourse(db.getCourses().get(0));
        assertTrue(db.removeSelectedCourse(db.getCourses().get(4)));
        assertEquals("COMP1010", db.getSelectedCourses().get(0).getCourseID());
        assertEquals(
                "Introductory Computer Science 1",
                db.getSelectedCourses().get(0).getCourseName()
        );
        assertFalse(db.removeSelectedCourse(db.getCourses().get(4)));
    }

    public void testTime() {
        assertEquals(24, db.getTime().getCurrentTime());
        assertEquals(1, db.getTime().getDays());
        assertEquals(96, db.getTime().getTimePerDay());
        assertEquals(24, db.getTime().getStartTime());

        db.updateTime(new Time(1042, 100));
        assertEquals(42, db.getTime().getCurrentTime());
        assertEquals(11, db.getTime().getDays());
        assertEquals(100, db.getTime().getTimePerDay());
        assertEquals(42, db.getTime().getStartTime());
    }

    public void testActions() {
        assertEquals(1, db.getActions(0).size());
        assertEquals("Nap", db.getActions(0).get("Nap").getActionName());
        assertEquals(1, db.getActions(0).get("Nap").getEnergyUnit());
        assertEquals(4, db.getActions(0).get("Nap").getTimeUnit());
        assertEquals(2, db.getActions(0).get("Nap").getPointsUnit());

        assertEquals(4, db.getActions(1).size());
        assertEquals("Quick Study", db.getActions(1).get("Quick Study").getActionName());
        assertEquals(-1, db.getActions(1).get("Quick Study").getEnergyUnit());
        assertEquals(4, db.getActions(1).get("Quick Study").getTimeUnit());
        assertEquals(0, db.getActions(1).get("Quick Study").getPointsUnit());

        assertEquals(3, db.getActions(2).size());
        assertEquals("Hibernate", db.getActions(2).get("Hibernate").getActionName());
        assertEquals(12, db.getActions(2).get("Hibernate").getEnergyUnit());
        assertEquals(48, db.getActions(2).get("Hibernate").getTimeUnit());
        assertEquals(12, db.getActions(2).get("Hibernate").getPointsUnit());

        assertEquals(6, db.getActions(3).size());
        assertEquals("Sleep", db.getActions(3).get("Sleep").getActionName());
        assertEquals(5, db.getActions(3).get("Sleep").getEnergyUnit());
        assertEquals(16, db.getActions(3).get("Sleep").getTimeUnit());
        assertEquals(6, db.getActions(3).get("Sleep").getPointsUnit());

        assertThrows(
                ArrayIndexOutOfBoundsException.class,
                ()->db.getActions(-1)
        );
        assertThrows(
                ArrayIndexOutOfBoundsException.class,
                ()->db.getActions(4)
        );
    }
}
