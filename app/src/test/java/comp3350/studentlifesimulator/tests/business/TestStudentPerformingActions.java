package comp3350.studentlifesimulator.tests.business;

import org.junit.Test;

import comp3350.studentlifesimulator.business.StudentPerformingActions;
import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class TestStudentPerformingActions {
    @Test
    public void TestPerformingAction() {
        StudentPerformingActions spa = new StudentPerformingActions();
        Student student = new Student("Son Johnsmith's near-identical twin");
        Time time = new Time(10);

        Action possibleAction = new Action("Action that takes less than the student's total energy", 1, 1);
        boolean result = spa.makeStudentPerformAction(student, possibleAction, time);
        assertTrue(result);
        assertEquals(Student.getMaxEnergy() - possibleAction.getEnergyUnit(), student.getCurrentEnergy());

        Action impossibleAction = new Action("Action that takes more than the student's remaining energy", 10, 1);
        assertFalse(spa.makeStudentPerformAction(student, impossibleAction, time));
        assertThrows(
                IllegalArgumentException.class,
                () -> student.doAction(impossibleAction)
        );
    }
}
