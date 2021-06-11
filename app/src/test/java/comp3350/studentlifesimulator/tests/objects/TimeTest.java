package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;
import comp3350.studentlifesimulator.objects.Time;
import static org.junit.Assert.*;
import org.junit.Test;

public class TimeTest {
    @Test
    public void testTimeConstructor() {
        Time timeTest = new Time(10);
        assertEquals(10, timeTest.getTimePerDay());
        assertEquals(1, timeTest.getDays());
        assertEquals(0, timeTest.getCurrentTime());
        assertEquals(9, timeTest.getStartTime());
    }

    @Test
    public void testTimeUnitIncrement() {
        Time timeTest2 = new Time(2);
        timeTest2.incrementTime();
        assertEquals(1, timeTest2.getCurrentTime());
        timeTest2.incrementTime();
        assertEquals(0, timeTest2.getCurrentTime());
        assertEquals(2, timeTest2.getDays());
    }
}