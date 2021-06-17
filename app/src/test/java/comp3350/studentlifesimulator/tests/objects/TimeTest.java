package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;
import comp3350.studentlifesimulator.objects.Time;
import static org.junit.Assert.*;
import org.junit.Test;

public class TimeTest extends TestCase {
    public TimeTest(String arg0) { super(arg0); }

    public void testTimeConstructor() {
        Time timeTest = new Time(10);
        assertEquals(10, timeTest.getTimePerDay());
        assertEquals(1, timeTest.getDays());
        assertEquals(0, timeTest.getCurrentTime());
        assertEquals(0, timeTest.getStartTime());

        timeTest = new Time(12, 10);
        assertEquals(10, timeTest.getTimePerDay());
        assertEquals(2 , timeTest.getDays());
        assertEquals(2, timeTest.getCurrentTime());
        assertEquals(2, timeTest.getStartTime());
    }

    public void testTimeUnitIncrement() {
        Time timeTest2 = new Time(2);
        timeTest2.incrementTime();
        assertEquals(1, timeTest2.getCurrentTime());
        timeTest2.incrementTime();
        assertEquals(0, timeTest2.getCurrentTime());
        assertEquals(2, timeTest2.getDays());
    }

    public void testAddToTimeUnit() {
        Time timeTest3 = new Time(10);
        assertEquals(0, timeTest3.getCurrentTime());
        timeTest3.addToTime(1);
        assertEquals(1, timeTest3.getCurrentTime());
        timeTest3.addToTime(5);
        assertEquals(6, timeTest3.getCurrentTime());

        timeTest3.addToTime(4);
        assertEquals(0, timeTest3.getCurrentTime());
        timeTest3.addToTime(12);
        assertEquals(2, timeTest3.getCurrentTime());

        timeTest3.addToTime(0);
        assertEquals(2, timeTest3.getCurrentTime());

        assertThrows(IllegalArgumentException.class, () -> timeTest3.addToTime(-1));
    }
}