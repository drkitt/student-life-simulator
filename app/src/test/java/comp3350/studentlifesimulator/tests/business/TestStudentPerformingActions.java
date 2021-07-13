package comp3350.studentlifesimulator.tests.business;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.business.StudentPerformingActions;
import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;

public class TestStudentPerformingActions extends TestCase {
    private Time time;
    private Student student;

    public TestStudentPerformingActions(String arg0) {
        super(arg0);

        student = new Student("John Smithson", new EnergyBar(10), 10);
        time = new Time(10);
    }

    public void testSPAAddToEnergy() {
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("Energizing action", 1, 1, 0),
                time
        ));
        assertEquals(11, student.getCurrentEnergy());
        assertEquals(1, time.getCurrentTime());
    }

    public void testSPAAddPastMaxEnergy() {
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("Very energizing action", 12, 1, 0),
                time
        ));
        assertEquals(EnergyBar.getMaxEnergy(), student.getCurrentEnergy());
        assertEquals(1, time.getCurrentTime());
    }

    public void testSPASubtractFromEnergy() {
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("Draining action", -5, 1, 0),
                time
        ));
        assertEquals(5, student.getCurrentEnergy());
        assertEquals(1, time.getCurrentTime());
    }

    public void testSPAInadequateEnergy() {
        assertFalse(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("Very draining action", -12, 1, 0),
                time
        ));
        assertEquals(10, student.getCurrentEnergy());
        assertEquals(0, time.getCurrentTime());
    }

    public void testSPAIncreaseScore() {
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("Rewarding action", -1, 1, 1),
                time
        ));
        assertEquals(9, student.getCurrentEnergy());
        assertEquals(1, time.getCurrentTime());
        assertEquals(11, student.getScore());
    }

    public void testSPADecreaseScore() {
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("Penalizing action", -1, 1, -1),
                time
        ));
        assertEquals(9, student.getCurrentEnergy());
        assertEquals(1, time.getCurrentTime());
        assertEquals(9, student.getScore());
    }

    public void testSPANegativeScore() {
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("Very penalizing action", -1, 1, -11),
                time
        ));
        assertEquals(9, student.getCurrentEnergy());
        assertEquals(1, time.getCurrentTime());
        assertEquals(-1, student.getScore());
    }

    public void testSPAConsecutiveActionsEnoughEnergy() {
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("First action in sequence", -5, 1, 0),
                time
        ));
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student, new Action("Second action in sequence", -4, 1, 0),
                time
        ));
        assertEquals(1, student.getCurrentEnergy());
        assertEquals(2, time.getCurrentTime());
    }

    public void testSPAConsecutiveActionsInadequateEnergy() {
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("First action in sequence", -5, 1, 0),
                time
        ));
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("Second action in sequence", 3, 1, 0),
                time
        ));
        assertFalse(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("Third action in sequence", -12, 1, 0),
                time
        ));
        assertEquals(8, student.getCurrentEnergy());
        assertEquals(2, time.getCurrentTime());
    }

    public void testSPAConsecutiveActionsScore() {
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("First action in sequence", -1, 1, 2),
                time
        ));
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("Second action in sequence", -1, 1, -1),
                time
        ));
        assertTrue(StudentPerformingActions.makeStudentPerformAction(
                student,
                new Action("Third action in sequence", -1, 1, 0),
                time
        ));
        assertEquals(7, student.getCurrentEnergy());
        assertEquals(3, time.getCurrentTime());
        assertEquals(11, student.getScore());
    }
}