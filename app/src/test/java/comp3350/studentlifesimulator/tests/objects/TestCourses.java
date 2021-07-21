package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;

import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.objects.Weekday;

import static org.junit.Assert.*;

public class TestCourses extends TestCase {

    public TestCourses(String arg0) {
        super(arg0);
    }

    public void testTypicalCases() {
        Course newCourse = new Course("COMP3350", "Software Engineering",
                           new ArrayList<>(Collections.singletonList(Weekday.Monday)),
                           new ArrayList<>(Collections.singletonList(new Time(32, 96))));
        assertEquals("COMP3350", newCourse.getCourseID());
        assertEquals("Software Engineering", newCourse.getCourseName());
        assertFalse(newCourse.equals(new Course("COMP3250", "Software Development" ,
                                     new ArrayList<>(Collections.singletonList(Weekday.Tuesday)),
                                     new ArrayList<>(Collections.singletonList(new Time(40, 96))))));
        assertTrue(newCourse.equals(new Course("COMP3350", "Software Engineering" ,
                                    new ArrayList<>(Collections.singletonList(Weekday.Wednesday)),
                                    new ArrayList<>(Collections.singletonList(new Time(44, 96))))));
    }

    public void testNull() {
        assertThrows(
                NullPointerException.class,
                () -> new Course("3310", "Test",
                        new ArrayList<>() , new ArrayList<>())
        );
        assertThrows(
                NullPointerException.class,
                () -> new Course("COMP4550", "Unknown Course" ,
                        new ArrayList<>(Collections.singletonList(Weekday.Thursday)),
                        new ArrayList<>(Collections.singletonList(new Time(44, 96)))).equals(null)
        );
    }

    public void testEdgeCases() {
        Course invalidCourse = new Course("", "" ,
                new ArrayList<>(Collections.singletonList(Weekday.Monday)),
                new ArrayList<>(Collections.singletonList(new Time(32, 96))));
        assertEquals("", invalidCourse.getCourseName());
        assertEquals("", invalidCourse.getCourseName());
        assertTrue(invalidCourse.equals(new Course("  ", "" ,
                                        new ArrayList<>(Collections.singletonList(Weekday.Monday)),
                                        new ArrayList<>(Collections.singletonList(new Time(32, 96))))));
        assertTrue(invalidCourse.equals(new Course("", "",
                                        new ArrayList<>(Collections.singletonList(Weekday.Monday)),
                                        new ArrayList<>(Collections.singletonList(new Time(32, 96))))));
    }
}
