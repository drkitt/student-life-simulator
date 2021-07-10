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
        DatabaseServices.openDatabaseAccess(new StubDatabase());
        ArrayList<Course> courses = DatabaseManager.getAvailableCourses();

        assertEquals(courses.get(0).getCourseID(), "COMP1010");

        DatabaseManager.addCourse(courses.get(4));
        assertEquals(DatabaseManager.getSelectedCourses().get(0).getCourseID(),
                courses.get(4).getCourseID());

        DatabaseManager.addCourse(courses.get(0));
        DatabaseManager.addCourse(courses.get(1));
        DatabaseManager.addCourse(courses.get(2));
        DatabaseManager.addCourse(courses.get(3));
        DatabaseManager.addCourse(courses.get(4));
        for (int i = 0; i < 5; i++) {
            assertEquals(DatabaseManager.getSelectedCourses().get(i + 1).getCourseID(),
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
