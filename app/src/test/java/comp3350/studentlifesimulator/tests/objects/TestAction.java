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

        Action exercise = new Action("Exercise", 2, 3);
        assertEquals("Exercise", exercise.getActionName());
        assertEquals(2, exercise.getEnergyUnit());
        assertEquals(3, exercise.getTimeUnit());
    }

    @Test
    public void testInvalidActionValues() {
        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", 0, 0 )
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", -1, -1)
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", Student.getStartingEnergy() + 1, 10)
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", 0, 2)
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Action("InvalidAction", 2, 10)
        );
    }
}