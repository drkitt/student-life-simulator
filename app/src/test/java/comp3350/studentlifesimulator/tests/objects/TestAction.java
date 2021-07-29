package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Student;

import static org.junit.Assert.*;

public class TestAction extends TestCase {
    public TestAction(String arg0) {
        super(arg0);
    }

    public void testGenericActionValueIsOne() {
        assertEquals("Study", (new Action("Study")).getActionName());
        assertEquals(1, (new Action("Study")).getEnergyUnit());
        assertEquals(1, (new Action("Study")).getPointsUnit());
        assertEquals(1, (new Action("Study")).getTimeUnit());
    }

    public void testReplenishingActionValues() {
        Action eat = new Action("Eat", 2, 3, 3);
        assertEquals("Eat", eat.getActionName());
        assertEquals(2, eat.getEnergyUnit());
        assertEquals(3, eat.getTimeUnit());
        assertEquals(3, eat.getPointsUnit());

        Action sleep = new Action("Sleep", Student.getMaxEnergy(), 3, 6);
        assertEquals("Sleep", sleep.getActionName());
        assertEquals(Student.getMaxEnergy(), sleep.getEnergyUnit());
        assertEquals(3, sleep.getTimeUnit());
        assertEquals(6, sleep.getPointsUnit());
    }

    public void testDepletingActionValues() {
        Action jog = new Action("Jog", -5, 3, 4);
        assertEquals("Jog", jog.getActionName());
        assertEquals(-5, jog.getEnergyUnit());
        assertEquals(3, jog.getTimeUnit());
        assertEquals(4, jog.getPointsUnit());

        Action run = new Action("Run", -Student.getMaxEnergy(), 3, 5);
        assertEquals("Run", run.getActionName());
        assertEquals(-Student.getMaxEnergy(), run.getEnergyUnit());
        assertEquals(3, run.getTimeUnit());
        assertEquals(5, run.getPointsUnit());
    }

    public void testEmptyActionValues() {
        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", 0, 0, 0)
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", 4, 0, 0)
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", 0, 4, 0)
        );
    }

    public void testLimitExceedingActionValues() {
        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", -Student.getMaxEnergy() - 1, 3, 0)
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", Student.getMaxEnergy() + 1, 3, 0)
        );
    }
}