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

        student = new Student("Son Johnsmith's near-identical twin", new EnergyBar(10), 10);
        time = new Time(10);
    }

    public void testAddToEnergy() {
        Action energizingAction = new Action("Action that adds to the student's total energy", 1, 1, 0);

        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, energizingAction, time));
        assertEquals(11, student.getCurrentEnergy());
        assertEquals(1, time.getCurrentTime());
    }

    public void testAddPastMaxEnergy() {
        Action veryEnergizingAction = new Action("Action that adds to the student's total energy, going beyond their max energy", 12, 1, 0);

        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, veryEnergizingAction, time));
        assertEquals(EnergyBar.getMaxEnergy(), student.getCurrentEnergy());
        assertEquals(1, time.getCurrentTime());
    }

    public void testSubtractFromEnergy() {
        Action drainingAction = new Action("Action that takes less than the student's total energy", -5, 1, 0);

        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, drainingAction, time));
        assertEquals(5, student.getCurrentEnergy());
        assertEquals(1, time.getCurrentTime());
    }

    public void testInadequateEnergy() {
        Action veryDrainingAction = new Action("Action that takes more than the student's current energy", -12, 1, 0);

        assertFalse(StudentPerformingActions.makeStudentPerformAction(student, veryDrainingAction, time));
        assertEquals(10, student.getCurrentEnergy());
        assertEquals(0, time.getCurrentTime());
    }

    public void testIncreaseScore() {
        Action rewardingAction = new Action("Action that adds to the student's score", -1, 1, 1);

        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, rewardingAction, time));
        assertEquals(9, student.getCurrentEnergy());
        assertEquals(1, time.getCurrentTime());
        assertEquals(11, student.getScore());
    }

    public void testDecreaseScore() {
        Action penalizingAction = new Action("Action that takes away from the student's score", -1, 1, -1);

        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, penalizingAction, time));
        assertEquals(9, student.getCurrentEnergy());
        assertEquals(1, time.getCurrentTime());
        assertEquals(9, student.getScore());
    }

    public void testNegativeScore() {
        Action veryPenalizingAction = new Action("Action that reduces the student's score to a negative number", -1, 1, -11);

        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, veryPenalizingAction, time));
        assertEquals(9, student.getCurrentEnergy());
        assertEquals(1, time.getCurrentTime());
        assertEquals(-1, student.getScore());
    }

    public void testConsecutiveActionsEnoughEnergy() {
        Action action1 = new Action("First action in sequence", -5, 1, 0);
        Action action2 = new Action("Second action in sequence", -4, 1, 0);

        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, action1, time));
        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, action2, time));
        assertEquals(1, student.getCurrentEnergy());
        assertEquals(2, time.getCurrentTime());
    }

    public void testConsecutiveActionsInadequateEnergy() {
        Action action1 = new Action("First action in sequence", -5, 1, 0);
        Action action2 = new Action("Second action in sequence", 3, 1, 0);
        Action action3 = new Action("Third action in sequence", -12, 1, 0);

        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, action1, time));
        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, action2, time));
        assertFalse(StudentPerformingActions.makeStudentPerformAction(student, action3, time));
        assertEquals(8, student.getCurrentEnergy());
        assertEquals(2, time.getCurrentTime());
    }

    public void testConsecutiveActionsScore() {
        Action action1 = new Action("First action in sequence", -1, 1, 2);
        Action action2 = new Action("Second action in sequence", -1, 1, -1);
        Action action3 = new Action("Third action in sequence", -1, 1, 0);

        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, action1, time));
        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, action2, time));
        assertTrue(StudentPerformingActions.makeStudentPerformAction(student, action3, time));
        assertEquals(7, student.getCurrentEnergy());
        assertEquals(3, time.getCurrentTime());
        assertEquals(11, student.getScore());
    }
}