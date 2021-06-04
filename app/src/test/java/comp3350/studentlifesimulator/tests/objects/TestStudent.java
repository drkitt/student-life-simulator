package comp3350.studentlifesimulator.tests.objects;

import org.junit.Test;

import comp3350.studentlifesimulator.objects.Student;

import static org.junit.Assert.*;

public class TestStudent {

    @Test
    public void testStudentCredentials() {
        Student playerNoID = new Student("John Smithson");
        assertEquals("John Smithson",playerNoID.getStudentName());

        Student playerWithID = new Student( "12345","Smith Johnson");
        assertEquals("Smith Johnson",playerWithID.getStudentName());
        assertEquals("12345",playerWithID.getStudentID());
    }
}
