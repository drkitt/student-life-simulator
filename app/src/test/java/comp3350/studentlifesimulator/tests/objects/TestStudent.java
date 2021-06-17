package comp3350.studentlifesimulator.tests.objects;

import org.junit.Test;

import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Action;

import static org.junit.Assert.*;

public class TestStudent {

    @Test
    public void testStudentCredentials() {
        Student player = new Student("John Smithson", new EnergyBar((Student.getMaxEnergy())));
        assertEquals("John Smithson",player.getStudentName());
    }

    @Test
    public void testDoAction() {
        Student student = new Student("Son Johnsmith", new EnergyBar(Student.getMaxEnergy()));
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
