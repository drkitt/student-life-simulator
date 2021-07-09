package comp3350.studentlifesimulator.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.studentlifesimulator.application.DatabaseServices;
import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.tests.persistence.StubDatabase;

public class TestDatabaseManager extends TestCase {
    public TestDatabaseManager(String arg0) { super(arg0); }

    public void testCourseSelection() {
        StubDatabase database = new StubDatabase();
        DatabaseServices.openDatabaseAccess(database);
        ArrayList<Course> courses = database.getCourses();

        assertEquals(courses.get(0).getCourseID(), "COMP1010");

        database.addSelectedCourse(courses.get(4));
        assertEquals(database.getSelectedCourses().get(0).getCourseID(),
                courses.get(4).getCourseID());

        database.addSelectedCourse(courses.get(0));
        database.addSelectedCourse(courses.get(1));
        database.addSelectedCourse(courses.get(2));
        database.addSelectedCourse(courses.get(3));
        database.addSelectedCourse(courses.get(4));
        for (int i = 0; i < 5; i++) {
            assertEquals(database.getSelectedCourses().get(i + 1).getCourseID(),
                    courses.get(i).getCourseID());
        }
    }

    public void testExpectedInteraction() {
    }

    public void testUnexpectedInteraction() {
    }

    public void testInvalidInteraction() {
    }
}
