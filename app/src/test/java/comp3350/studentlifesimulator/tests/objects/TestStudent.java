package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Action;

import static org.junit.Assert.*;

public class TestStudent extends TestCase {
    public TestStudent(String arg0) { super(arg0); }

    public void testStudentCredentials() {
        Student student = new Student("John Smithson", new EnergyBar(8), 6);

        assertEquals("John Smithson", student.getStudentName());
        assertEquals(12, Student.getMaxEnergy());
        student.addToScore(3);
        assertEquals(9, student.getScore());
        assertEquals(8, student.getCurrentEnergy());

        Action possibleAction1 = new Action("Action that takes less than the student's current energy", -5, 1, 1);
        boolean canDoAction = student.canDoAction(possibleAction1);
        assertTrue(canDoAction);

        Action impossibleAction1 = new Action("Action that takes more than the student's current energy", -9, 1, 0);
        canDoAction = student.canDoAction(impossibleAction1);
        assertFalse(canDoAction);
    }

    public void testAddToEnergy(){
        Student student = new Student("Son Johnsmith", new EnergyBar(8), 0);
        Action possibleAction = new Action("Action that add to the student's current energy", 3, 1, 0);

        student.doAction(possibleAction);

        assertEquals(11, student.getCurrentEnergy());
    }

    public void testAddToEnoughEnergy(){
        Student student = new Student("Son Johnsmith", new EnergyBar(EnergyBar.getMaxEnergy()), 0);
        Action possibleAction = new Action("Action that takes less than the student's current energy", 3, 1, 0);

        student.doAction(possibleAction);

        assertEquals(12, student.getCurrentEnergy());
    }

    public void testEnoughEnergy(){
        Student student = new Student("Son Johnsmith", new EnergyBar(EnergyBar.getMaxEnergy()), 0);
        Action possibleAction = new Action("Action that takes less than the student's current energy", -5, 1, 0);

        student.doAction(possibleAction);

        assertEquals(7, student.getCurrentEnergy());
    }

    public void testInadequateEnergy(){
        Student student = new Student("Son Johnsmith", new EnergyBar(8), 0);
        Action impossibleAction = new Action("Action that takes more than the student's current energy", -12, 1, 0);

        boolean canDoAction = student.canDoAction(impossibleAction);
        assertFalse(canDoAction);

        assertEquals(8, student.getCurrentEnergy());
    }

    public void testConsequentActionsEnoughEnergy(){
        Student student = new Student("Son Johnsmith", new EnergyBar(EnergyBar.getMaxEnergy()), 0);
        Action possibleAction1 = new Action("Action that takes less than the student's current energy", -5, 1, 0);
        Action impossibleAction2 = new Action("Action that takes less than the student's current energy", -3, 1, 0);

        student.doAction(possibleAction1);
        student.doAction(impossibleAction2);

        assertEquals(4, student.getCurrentEnergy());
    }

    public void testConsequentActionsInadequateEnergy(){
        Student student = new Student("Son Johnsmith", new EnergyBar(8), 0);
        Action possibleAction1 = new Action("Action that takes less than the student's current energy", -5, 1, 0);
        Action impossibleAction1 = new Action("Action that takes less than the student's current energy", -4, 1, 0);

        student.doAction(possibleAction1);
        boolean canDoAction = student.canDoAction(impossibleAction1);
        assertFalse(canDoAction);

        assertEquals(3, student.getCurrentEnergy());
    }



    public void testTakeAwayScore(){
        Student student = new Student("Son Johnsmith", new EnergyBar(8), 1);
        Action possibleAction1 = new Action("Action that takes less than the student's current energy", -5, 1, 0);
        Action possibleAction2 = new Action("Action that takes away from  student's score", -2, 1, -1);

        student.doAction(possibleAction1);
        student.doAction(possibleAction2);

        assertEquals(0, student.getScore());
    }

    public void testConsequentActionsScore(){
        Student student = new Student("Son Johnsmith", new EnergyBar(EnergyBar.getMaxEnergy()), 1);
        Action possibleAction1 = new Action("Action that takes less than the student's current energy", -5, 1, 0);
        Action possibleAction2 = new Action("Action that adds to the student's score", -2, 1, 2);
        Action possibleAction3 = new Action("Action that takes away from  student's score", -2, 1, -1);

        student.doAction(possibleAction1);
        student.doAction(possibleAction2);
        student.doAction(possibleAction3);

        assertEquals(2, student.getScore());
    }

}
