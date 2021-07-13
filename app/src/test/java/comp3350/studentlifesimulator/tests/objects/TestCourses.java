package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.objects.Course;

import static org.junit.Assert.*;

public class TestCourses extends TestCase {
    public TestCourses(String arg0) {
        super(arg0);
    }

    public void testTypicalCases() {
        Course newCourse = new Course("COMP3350", "Software Engineering");
        assertEquals("COMP3350", newCourse.getCourseID());
        assertEquals("Software Engineering", newCourse.getCourseName());
        assertFalse(newCourse.equals(new Course("COMP3250", "Software Development")));
        assertTrue(newCourse.equals(new Course("COMP3350", "Software Engineering")));
    }

    public void testNull() {
        assertThrows(
                NullPointerException.class,
                () -> new Course(null, null)
        );
        assertThrows(
                NullPointerException.class,
                () -> new Course("COMP4550", "Unknown Course").equals(null)
        );
    }

    public void testEdgeCases() {
        Course invalidCourse = new Course("", "");
        assertEquals("", invalidCourse.getCourseName());
        assertEquals("", invalidCourse.getCourseName());
        assertTrue(invalidCourse.equals(new Course("  ", "")));
        assertTrue(invalidCourse.equals(new Course("", "")));
    }
}
