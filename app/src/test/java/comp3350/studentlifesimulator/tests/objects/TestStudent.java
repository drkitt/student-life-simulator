package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Action;

import static org.junit.Assert.*;

public class TestStudent extends TestCase {
    public TestStudent(String arg0) { super(arg0); }

    public void testStudentCredentials() {
        Student player = new Student("John Smithson", new EnergyBar((Student.getMaxEnergy())));
        assertEquals("John Smithson", player.getStudentName());
    }

    public void testDoAction() {
        Student student = new Student("Son Johnsmith", new EnergyBar(Student.getMaxEnergy()));
      
        Action possibleAction = new Action("Action that takes less than the student's total energy", -5, 1, 0);
        student.doAction(possibleAction);
        assertEquals(Student.getMaxEnergy() + possibleAction.getEnergyUnit(), student.getCurrentEnergy());
        assertEquals(0, student.getScore());

        Action anotherPossibleAction = new Action("Action that adds to the student's total energy", 1, 1, 0);
        student.doAction(anotherPossibleAction);
        assertEquals(Student.getMaxEnergy() + possibleAction.getEnergyUnit() + anotherPossibleAction.getEnergyUnit(), student.getCurrentEnergy());
        assertEquals(0, student.getScore());

        Action impossibleAction = new Action("Action that takes more than the student's remaining energy", -10, 1, 0);
        assertThrows(
            IllegalArgumentException.class,
            () -> student.doAction(impossibleAction)
        );
        assertEquals(Student.getMaxEnergy() + possibleAction.getEnergyUnit() + anotherPossibleAction.getEnergyUnit(), student.getCurrentEnergy());

        Action anotherImpossibleAction = new Action("Action that adds too much to the student's remaining energy", 10, 1, 0);
        assertThrows(
                IllegalArgumentException.class,
                () -> student.doAction(anotherImpossibleAction)
        );
        assertEquals(Student.getMaxEnergy() + possibleAction.getEnergyUnit() + anotherPossibleAction.getEnergyUnit(), student.getCurrentEnergy());

        Action rewardingAction = new Action("Action that adds to the student's score", -1, 1, 1);
        student.doAction(rewardingAction);
        assertEquals(
                Student.getMaxEnergy() + possibleAction.getEnergyUnit() + anotherPossibleAction.getEnergyUnit() + rewardingAction.getEnergyUnit(),
                student.getCurrentEnergy()
        );
        assertEquals(rewardingAction.getPointsUnit(), student.getScore());

        Action penalizingAction = new Action("Action takes away from the student's score", -1, 1, -2);
        student.doAction(penalizingAction);
        assertEquals(
                Student.getMaxEnergy() + possibleAction.getEnergyUnit() + anotherPossibleAction.getEnergyUnit() + rewardingAction.getEnergyUnit() + penalizingAction.getEnergyUnit(),
                student.getCurrentEnergy()
        );
        assertEquals(rewardingAction.getPointsUnit() + penalizingAction.getPointsUnit(), student.getScore());
    }
}
