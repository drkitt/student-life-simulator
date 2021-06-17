package comp3350.studentlifesimulator.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import comp3350.studentlifesimulator.business.StudentPerformingActions;
import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class TestStudentPerformingActions extends TestCase {
    public TestStudentPerformingActions(String arg0) { super(arg0); }

    public void testPerformingAction() {
        StudentPerformingActions spa = new StudentPerformingActions();
        EnergyBar energyBar = new EnergyBar(Student.getMaxEnergy());
        Student student = new Student("Son Johnsmith's near-identical twin", energyBar);
        Time time = new Time(10);

        Action possibleAction = new Action("Action that takes less than the student's total energy", -5, 1);
        boolean result = spa.makeStudentPerformAction(student, possibleAction, time);
        assertTrue(result);
        assertEquals(Student.getMaxEnergy() + possibleAction.getEnergyUnit(), student.getCurrentEnergy());

        Action anotherPossibleAction = new Action("Action that adds to the student's total energy", 1, 1);
        result = spa.makeStudentPerformAction(student, anotherPossibleAction, time);
        assertTrue(result);
        assertEquals(Student.getMaxEnergy() + possibleAction.getEnergyUnit() + anotherPossibleAction.getEnergyUnit(), student.getCurrentEnergy());

        Action impossibleAction = new Action("Action that takes more than the student's remaining energy", -10, 1);
        assertFalse(spa.makeStudentPerformAction(student, impossibleAction, time));
        assertEquals(Student.getMaxEnergy() + possibleAction.getEnergyUnit() + anotherPossibleAction.getEnergyUnit(), student.getCurrentEnergy());

        Action anotherImpossibleAction = new Action("Action that adds too much to the student's remaining energy", 10, 1);
        assertFalse(spa.makeStudentPerformAction(student, anotherImpossibleAction, time));
        assertEquals(Student.getMaxEnergy() + possibleAction.getEnergyUnit() + anotherPossibleAction.getEnergyUnit(), student.getCurrentEnergy());
    }
}
