package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.objects.Time;

import static org.junit.Assert.*;

public class TestTime extends TestCase {
    public TestTime(String arg0) { super(arg0); }

    public void testTimeConstructor() {
        Time clock = new Time(10);
        assertEquals(10, clock.getTimePerDay());
        assertEquals(1, clock.getDays());
        assertEquals(0, clock.getCurrentTime());
        assertEquals(0, clock.getStartTime());

        clock = new Time(12, 10);
        assertEquals(10, clock.getTimePerDay());
        assertEquals(2, clock.getDays());
        assertEquals(2, clock.getCurrentTime());
        assertEquals(2, clock.getStartTime());
    }

    public void testTimeUnitIncrement() {
        Time  incrementingClock = new Time(2);
        incrementingClock.incrementTime();
        assertEquals(1, incrementingClock.getCurrentTime());
        incrementingClock.incrementTime();
        assertEquals(0, incrementingClock.getCurrentTime());
        assertEquals(2, incrementingClock.getDays());
    }

    public void testAddToTimeUnit() {
        Time addingClock = new Time(10);
        assertEquals(0, addingClock.getCurrentTime());
        addingClock.addToTime(1);
        assertEquals(1, addingClock.getCurrentTime());
        addingClock.addToTime(5);
        assertEquals(6, addingClock.getCurrentTime());

        addingClock.addToTime(4);
        assertEquals(0, addingClock.getCurrentTime());
        addingClock.addToTime(12);
        assertEquals(2, addingClock.getCurrentTime());

        addingClock.addToTime(0);
        assertEquals(2, addingClock.getCurrentTime());

        assertThrows(
                IllegalArgumentException.class,
                () -> addingClock.addToTime(-1)
        );
    }
}