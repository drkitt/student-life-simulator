package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;
import comp3350.studentlifesimulator.objects.Time;
import static org.junit.Assert.*;
import org.junit.Test;

public class TimeTest{

    @Test
    public void testTimeConstructor(){

        Time timeTest = new Time(30,480);
        assertEquals(30,timeTest.getTimeUnitTime());
        assertEquals(480,timeTest.getTimeMaxTime());
        assertEquals(1,timeTest.getTimeDays());
        assertEquals(0,timeTest.getTimeCurrentTime());
        assertEquals(0,timeTest.getTimeCurrentUnit());
        assertEquals(9,timeTest.getTimeStartTime());
    }

    @Test
    public void testTimeUnitIncrement(){
        Time timeTest2 = new Time(30 , 60);
        timeTest2.timeUnitIncrement();
        timeTest2.timeUnitIncrement();
        assertEquals(0,timeTest2.getTimeCurrentUnit());
        assertEquals(0,timeTest2.getTimeCurrentTime());
        assertEquals(2,timeTest2.getTimeDays());
    }

}