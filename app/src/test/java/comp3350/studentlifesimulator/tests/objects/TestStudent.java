package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Action;

import static org.junit.Assert.*;

public class TestStudent extends TestCase {
    public TestStudent(String arg0) { super(arg0); }

    public void testStudentCredentials() {
        Student playerNoID = new Student("John Smithson");
        assertEquals("John Smithson",playerNoID.getStudentName());

        Student playerWithID = new Student( "12345","Smith Johnson");
        assertEquals("Smith Johnson",playerWithID.getStudentName());
        assertEquals("12345",playerWithID.getStudentID());
    }

    public void testDoAction() {
        Student student = new Student("Son Johnsmith");
        Action possibleAction = new Action("Action that takes less than the student's total energy", 1, 1);
        student.doAction(possibleAction);
        assertEquals(Student.getMaxEnergy() - possibleAction.getEnergyUnit(), student.getCurrentEnergy());

        Action impossibleAction = new Action("Action that takes more than the student's remaining energy", 10, 1);
        assertThrows(
            IllegalArgumentException.class,
            () -> student.doAction(impossibleAction)
        );
    }
}
