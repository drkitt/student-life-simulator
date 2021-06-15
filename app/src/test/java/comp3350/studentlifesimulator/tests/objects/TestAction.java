package comp3350.studentlifesimulator.tests.objects;

import org.junit.Test;

import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Student;

import static org.junit.Assert.*;

public class TestAction {

    @Test
    public void testValidActionValues() {
        Action study = new Action("Study");
        assertEquals("Study", study.getActionName());
        assertEquals(1, study.getTimeUnit());
        assertEquals(1, study.getEnergyUnit());

        Action eat = new Action("Eat", 2, 3);
        assertEquals("Eat", eat.getActionName());
        assertEquals(2, eat.getEnergyUnit());
        assertEquals(3, eat.getTimeUnit());

        Action sleep = new Action("Sleep", Student.getMaxEnergy(), 3);
        assertEquals("Sleep", sleep.getActionName());
        assertEquals(Student.getMaxEnergy(), sleep.getEnergyUnit());
        assertEquals(3, sleep.getTimeUnit());

        Action jog = new Action("Jog", -5, 3);
        assertEquals("Jog", jog.getActionName());
        assertEquals(-5, jog.getEnergyUnit());
        assertEquals(3, jog.getTimeUnit());

        Action run = new Action("Run", -Student.getMaxEnergy(), 3);
        assertEquals("Run", run.getActionName());
        assertEquals( -Student.getMaxEnergy(), run.getEnergyUnit());
        assertEquals(3, run.getTimeUnit());
    }

    @Test
    public void testInvalidActionValues() {
        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", 0, 0 )
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", 4, 0 )
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", 4, 0 )
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", -Student.getMaxEnergy() - 1, 3)
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", Student.getMaxEnergy() + 1, 3)
        );
    }
}