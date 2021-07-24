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
                           32);
        assertEquals("COMP3350", newCourse.getCourseID());
        assertEquals("Software Engineering", newCourse.getCourseName());
        assertFalse(newCourse.equals(new Course(
                "COMP3250",
                "Software Development",
                new ArrayList<>(Collections.singletonList(Weekday.Tuesday)),
                40
        )));
        assertTrue(newCourse.equals(new Course(
                "COMP3350",
                "Software Engineering",
                new ArrayList<>(Collections.singletonList(Weekday.Wednesday)),
                44
        )));

        Course validCourse = new Course(
                "1234" ,
                "Valid Test" ,
                new ArrayList<>(Arrays.asList(Weekday.Monday , Weekday.Wednesday , Weekday.Friday)),
                40
        );

        assertEquals(
                new ArrayList<>(Arrays.asList(Weekday.Monday , Weekday.Wednesday , Weekday.Friday)) ,
                validCourse.getClassDays()
        );

        assertEquals(3 , validCourse.getClassDays().size());
        assertEquals(40 , validCourse.getClassTime());
    }

    public void testNull() {
        assertThrows(
                NullPointerException.class,
                () -> new Course("3310", "Test",
                        new ArrayList<>() , 0)
        );
        assertThrows(
                NullPointerException.class,
                () -> new Course("COMP4550", "Unknown Course" ,
                        new ArrayList<>(Collections.singletonList(Weekday.Thursday)),
                        32).equals(null)
        );

        assertThrows(
                NullPointerException.class,
                () -> new Course("1234",
                        "Invalid Test",
                        new ArrayList<>(Collections.singletonList(Weekday.Saturday)) ,
                        96)
        );
    }

    public void testEdgeCases() {
        Course invalidCourse = new Course(
                "",
                "" ,
                new ArrayList<>(Collections.singletonList(Weekday.Monday)),
                32
        );
        assertEquals("", invalidCourse.getCourseName());
        assertEquals("", invalidCourse.getCourseName());
        assertTrue(invalidCourse.equals(new Course("  ", "" ,
                                        new ArrayList<>(Collections.singletonList(Weekday.Monday)),
                                        32)));
        assertTrue(invalidCourse.equals(new Course(
                "",
                "",
                new ArrayList<>(Collections.singletonList(Weekday.Monday)),
                32
        )));
    }

}
