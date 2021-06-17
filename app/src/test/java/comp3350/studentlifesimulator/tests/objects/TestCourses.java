package comp3350.studentlifesimulator.tests.objects;
import junit.framework.TestCase;

import comp3350.studentlifesimulator.objects.Course;

import org.junit.Test;
import static org.junit.Assert.*;
public class TestCourses extends TestCase {
    public TestCourses(String arg0) { super(arg0); }

    public void testTypicalCases() {
        Course newCourse = new Course("COMP3350" , "Software Engineering");
        assertEquals("COMP3350" , newCourse.getCourseID());
        assertEquals("Software Engineering" , newCourse.getCourseName());
        assertFalse(newCourse.equals(new Course("COMP3250", "Software Development")));
        assertTrue(newCourse.equals(new Course("COMP3350", "Software Engineering")));
    }

    public void testNull() {
        try {
            Course nullCourse = new Course(null , null);
            new Course("COMP4550" , "Unknown Course").equals(null);
            fail("Null Pointer Exception expected");
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void testEdgeCases() {
        Course invalidCourse = new Course("" , "");
        assertEquals("" , invalidCourse.getCourseName());
        assertEquals("" , invalidCourse.getCourseName());
        assertTrue(invalidCourse.equals(new Course("  " , "")));
        assertTrue(invalidCourse.equals(new Course("" , "")));
    }
}
