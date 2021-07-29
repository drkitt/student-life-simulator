package comp3350.studentlifesimulator.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

import comp3350.studentlifesimulator.application.DatabaseServices;
import comp3350.studentlifesimulator.business.DatabaseManager;
import comp3350.studentlifesimulator.business.StateManager;
import comp3350.studentlifesimulator.objects.ActionStates;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.objects.Weekday;
import comp3350.studentlifesimulator.tests.persistence.StubDatabase;

public class TestStateManager extends TestCase {
    public TestStateManager(String arg0) {
        super(arg0);
    }

    public void testHasClassState() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());
        DatabaseManager.updateTime(new Time(32, 96));
        DatabaseManager.addCourse(new Course(
                "COMP3350",
                "Software Engineering",
                new ArrayList<>(Collections.singletonList(Weekday.Monday)),
                32
        ));
        StateManager.initialize();
        assertEquals(ActionStates.HAS_CLASS, StateManager.getState());
        assertNull(StateManager.getCurrentPossibleActions(ActionStates.HAS_CLASS));
        DatabaseServices.closeDatabaseAccess();
    }

    public void testFreeTimeState() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());
        DatabaseManager.updateTime(new Time(24, 96));
        StateManager.initialize();
        assertEquals(ActionStates.FREE_TIME, StateManager.getState());
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.FREE_TIME).get("Nap"));
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.FREE_TIME).get("Sleep"));
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.FREE_TIME).get("Hibernate"));
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.FREE_TIME).get("Quick Study"));
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.FREE_TIME).get("Study"));
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.FREE_TIME).get("Marathon Study"));
        assertEquals(6, StateManager.getCurrentPossibleActions(ActionStates.FREE_TIME).size());
        DatabaseServices.closeDatabaseAccess();
    }

    public void testLowEnergyState() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());
        DatabaseManager.updateStudent(new Student("Test Student", new EnergyBar(0), 1));
        StateManager.initialize();
        assertEquals(ActionStates.LOW_ENERGY, StateManager.getState());
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.LOW_ENERGY).get("Nap"));
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.LOW_ENERGY).get("Sleep"));
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.LOW_ENERGY).get("Hibernate"));
        assertEquals(3, StateManager.getCurrentPossibleActions(ActionStates.LOW_ENERGY).size());
        DatabaseServices.closeDatabaseAccess();
    }

    public void testInClassLowState() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());
        DatabaseManager.updateStudent(new Student("Test Student", new EnergyBar(0), 1));
        StateManager.initialize();
        StateManager.switchInClass();
        assertEquals(ActionStates.IN_CLASS_LOW, StateManager.getState());
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.IN_CLASS_LOW).get("Nap"));
        assertEquals(1, StateManager.getCurrentPossibleActions(ActionStates.IN_CLASS_LOW).size());
        DatabaseServices.closeDatabaseAccess();
    }

    public void testInClassHighState() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());
        DatabaseManager.updateStudent(new Student("Test Student", new EnergyBar(EnergyBar.getMaxEnergy()), 1));
        StateManager.initialize();
        StateManager.switchInClass();
        assertEquals(ActionStates.IN_CLASS_HIGH, StateManager.getState());
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.IN_CLASS_HIGH).get("Quick Study"));
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.IN_CLASS_HIGH).get("Nap"));
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.IN_CLASS_HIGH).get("Listen to Instructor"));
        assertNotNull(StateManager.getCurrentPossibleActions(ActionStates.IN_CLASS_HIGH).get("Talk with Friends"));
        assertEquals(4, StateManager.getCurrentPossibleActions(ActionStates.IN_CLASS_HIGH).size());
        DatabaseServices.closeDatabaseAccess();
    }

    public void testEdgeCase() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());
        DatabaseManager.updateTime(new Time(32, 96));
        StateManager.initialize();
        assertEquals(ActionStates.FREE_TIME, StateManager.getState());
        DatabaseServices.closeDatabaseAccess();
    }

    public void testStateChange() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());
        DatabaseManager.updateStudent(new Student("Test Student", new EnergyBar(EnergyBar.getMaxEnergy()), 1));
        DatabaseManager.updateTime(new Time(28, 96));
        DatabaseManager.addCourse(new Course(
                "COMP3350",
                "Software Engineering",
                new ArrayList<>(Collections.singletonList(Weekday.Monday)),
                32
        ));

        DatabaseManager.addCourse(new Course(
                "COMP1010",
                "Introduction to Programming 1",
                new ArrayList<>(Collections.singletonList(Weekday.Tuesday)),
                44));
        DatabaseManager.addCourse(new Course(
                "COMP1020",
                "Introduction to Programming 2",
                new ArrayList<>(Collections.singletonList(Weekday.Wednesday)),
                60
        ));
        StateManager.initialize();
        assertEquals(ActionStates.FREE_TIME, StateManager.getState());
        StateManager.getTime().addToTime(4);
        assertEquals(ActionStates.HAS_CLASS, StateManager.getState());
        StateManager.switchSkipped();
        assertEquals(ActionStates.FREE_TIME, StateManager.getState());
        StateManager.getCurrentStudent().doAction(
                StateManager.getCurrentPossibleActions(ActionStates.FREE_TIME).get("Marathon Study")
        );
        StateManager.getCurrentStudent().doAction(
                StateManager.getCurrentPossibleActions(ActionStates.FREE_TIME).get("Study")
        );
        StateManager.getCurrentStudent().doAction(
                StateManager.getCurrentPossibleActions(ActionStates.FREE_TIME).get("Quick Study")
        );
        StateManager.getCurrentStudent().doAction(
                StateManager.getCurrentPossibleActions(ActionStates.IN_CLASS_HIGH).get("Listen to Instructor")
        );
        assertEquals(ActionStates.LOW_ENERGY, StateManager.getState());
        StateManager.getCurrentStudent().doAction(
                StateManager.getCurrentPossibleActions(ActionStates.LOW_ENERGY).get("Hibernate")
        );
        StateManager.switchSkipped();

        StateManager.getTime().addToTime(108);
        assertEquals(ActionStates.HAS_CLASS, StateManager.getState());
        StateManager.switchInClass();
        assertEquals(ActionStates.IN_CLASS_HIGH, StateManager.getState());
        StateManager.switchInClass();
        StateManager.getTime().addToTime(1);
        assertEquals(ActionStates.FREE_TIME, StateManager.getState());
        DatabaseServices.closeDatabaseAccess();
    }
}
