package comp3350.studentlifesimulator.tests.objects;

import org.junit.Test;

import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Student;

import static org.junit.Assert.*;

public class TestAction {

    @Test
    public void testValidActionValues() {
        Student student = DatabaseManager.getStudent();

        Action study = new Action("Study");
        assertEquals("Study", study.getActionName());
        assertEquals(1, study.getTimeUnit());
        assertEquals(1, study.getEnergyUnit());

        Action eat = new Action("Eat", 2, 3, student.getMaxEnergy());
        assertEquals("Eat", eat.getActionName());
        assertEquals(2, eat.getEnergyUnit());
        assertEquals(3, eat.getTimeUnit());

        Action sleep = new Action("Sleep", student.getMaxEnergy(), 3, student.getMaxEnergy());
        assertEquals("Sleep", sleep.getActionName());
        assertEquals(student.getMaxEnergy(), sleep.getEnergyUnit());
        assertEquals(3, sleep.getTimeUnit());

        Action jog = new Action("Jog", -5, 3, student.getMaxEnergy());
        assertEquals("Jog", jog.getActionName());
        assertEquals(-5, jog.getEnergyUnit());
        assertEquals(3, jog.getTimeUnit());

        Action run = new Action("Run", -student.getMaxEnergy(), 3, student.getMaxEnergy());
        assertEquals("Run", run.getActionName());
        assertEquals( -student.getMaxEnergy(), run.getEnergyUnit());
        assertEquals(3, run.getTimeUnit());
    }

    @Test
    public void testInvalidActionValues() {
        Student student = DatabaseManager.getStudent();

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", 0, 0, student.getMaxEnergy())
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", 4, 0, student.getMaxEnergy())
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", 4, 0, student.getMaxEnergy())
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", -student.getMaxEnergy() - 1, 3, student.getMaxEnergy())
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", student.getMaxEnergy() + 1, 3, student.getMaxEnergy())
        );
    }
}