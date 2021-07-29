package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Action;

import static org.junit.Assert.*;

public class TestStudent extends TestCase {
    private final Student student;

    public TestStudent(String arg0) {
        super(arg0);

        student = new Student("John Braico", new EnergyBar(10), 10);
    }

    public void testStudentCredentials() {
        assertEquals("John Braico", student.getStudentName());
        assertEquals(12, Student.getMaxEnergy());
        student.addToScore(3);
        assertEquals(13, student.getScore());
        assertEquals(10, student.getCurrentEnergy());

        assertTrue(student.canDoAction(new Action("Possible action", -5, 1, 1)));
        assertFalse(student.canDoAction(new Action("Impossible action", -12, 1, 0)));
    }

    public void testAddToEnergy() {
        Action energizingAction = new Action("Energizing action", 1, 1, 0);
        assertTrue(student.canDoAction(energizingAction));
        student.doAction(energizingAction);
        assertEquals(11, student.getCurrentEnergy());
    }

    public void testAddPastMaxEnergy() {
        Action veryEnergizingAction = new Action("Very energizing action", 12, 1, 0);
        assertTrue(student.canDoAction(veryEnergizingAction));
        student.doAction(veryEnergizingAction);
        assertEquals(12, student.getCurrentEnergy());
    }

    public void testSubtractFromEnergy() {
        Action drainingAction = new Action("Draining action", -5, 1, 0);
        assertTrue(student.canDoAction(drainingAction));
        student.doAction(drainingAction);
        assertEquals(5, student.getCurrentEnergy());
    }

    public void testInadequateEnergy() {
        Action veryDrainingAction = new Action("Very draining action", -12, 1, 0);
        assertFalse(student.canDoAction(veryDrainingAction));
        assertThrows(
                IllegalArgumentException.class,
                () -> student.doAction(veryDrainingAction)
        );
        assertEquals(10, student.getCurrentEnergy());
    }

    public void testIncreaseScore() {
        Action rewardingAction = new Action("Rewarding action", -1, 1, 1);
        assertTrue(student.canDoAction(rewardingAction));
        student.doAction(rewardingAction);
        assertEquals(9, student.getCurrentEnergy());
        assertEquals(11, student.getScore());
    }

    public void testDecreaseScore() {
        Action penalizingAction = new Action("Penalizing action", -1, 1, -1);
        assertTrue(student.canDoAction(penalizingAction));
        student.doAction(penalizingAction);
        assertEquals(9, student.getCurrentEnergy());
        assertEquals(9, student.getScore());
    }

    public void testNegativeScore() {
        Action veryPenalizingAction = new Action("Very penalizing action", -1, 1, -11);
        assertTrue(student.canDoAction(veryPenalizingAction));
        student.doAction(veryPenalizingAction);
        assertEquals(9, student.getCurrentEnergy());
        assertEquals(-1, student.getScore());
    }

    public void testConsecutiveActionsEnoughEnergy() {
        Action action1 = new Action("First action in sequence", -5, 1, 0);
        Action action2 = new Action("Second action in sequence", -4, 1, 0);

        assertTrue(student.canDoAction(action1));
        assertTrue(student.canDoAction(action2));

        student.doAction(action1);
        student.doAction(action2);
        assertEquals(1, student.getCurrentEnergy());
    }

    public void testConsecutiveActionsInadequateEnergy() {
        Action action1 = new Action("First action in sequence", -5, 1, 0);
        Action action2 = new Action("Second action in sequence", 3, 1, 0);
        Action action3 = new Action("Third action in sequence", -12, 1, 0);

        assertTrue(student.canDoAction(action1));
        assertTrue(student.canDoAction(action2));
        assertFalse(student.canDoAction(action3));

        student.doAction(action1);
        student.doAction(action2);
        assertThrows(
                IllegalArgumentException.class,
                () -> student.doAction(action3)
        );
        assertEquals(8, student.getCurrentEnergy());
    }

    public void testConsecutiveActionsScore() {
        Action action1 = new Action("First action in sequence", -1, 1, 2);
        Action action2 = new Action("Second action in sequence", -1, 1, -1);
        Action action3 = new Action("Third action in sequence", -1, 1, 0);

        assertTrue(student.canDoAction(action1));
        assertTrue(student.canDoAction(action2));
        assertTrue(student.canDoAction(action3));

        student.doAction(action1);
        student.doAction(action2);
        student.doAction(action3);
        assertEquals(7, student.getCurrentEnergy());
        assertEquals(11, student.getScore());
    }
}
