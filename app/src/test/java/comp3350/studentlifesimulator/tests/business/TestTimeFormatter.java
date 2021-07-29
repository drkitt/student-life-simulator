package comp3350.studentlifesimulator.tests.business;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.application.DatabaseServices;
import comp3350.studentlifesimulator.business.StateManager;
import comp3350.studentlifesimulator.business.TimeFormatter;
import comp3350.studentlifesimulator.tests.persistence.StubDatabase;

public class TestTimeFormatter extends TestCase {
    public TestTimeFormatter(String arg0) {
        super(arg0);
    }

    public void testHourFormatting() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());
        TimeFormatter timeFormatter;

        StateManager.initialize();
        timeFormatter = new TimeFormatter();

        assertEquals(6, timeFormatter.getHour12());
        assertEquals(6, timeFormatter.getHour24());
        assertEquals("AM", timeFormatter.getSuffix());

        StateManager.getTime().addToTime(56);
        assertEquals(8, timeFormatter.getHour12());
        assertEquals(20, timeFormatter.getHour24());
        assertEquals("PM", timeFormatter.getSuffix());

        StateManager.getTime().addToTime(18);
        assertEquals(12, timeFormatter.getHour12());
        assertEquals(0, timeFormatter.getHour24());
        assertEquals("AM", timeFormatter.getSuffix());

        DatabaseServices.closeDatabaseAccess();
    }

    public void testMinuteFormatting() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());
        TimeFormatter timeFormatter;

        StateManager.initialize();
        timeFormatter = new TimeFormatter();

        assertEquals(0, timeFormatter.getMinute());
        StateManager.getTime().addToTime(1);
        assertEquals(15, timeFormatter.getMinute());
        StateManager.getTime().addToTime(1);
        assertEquals(30, timeFormatter.getMinute());
        StateManager.getTime().addToTime(1);
        assertEquals(45, timeFormatter.getMinute());
        StateManager.getTime().addToTime(1);
        assertEquals(0, timeFormatter.getMinute());


        DatabaseServices.closeDatabaseAccess();
    }

    public void testWeekDayFormatting() {
        DatabaseServices.openDatabaseAccess(new StubDatabase());
        TimeFormatter timeFormatter;

        StateManager.initialize();
        timeFormatter = new TimeFormatter();

        assertEquals(1, timeFormatter.getWeekCount());
        assertEquals("Monday", timeFormatter.getDayInWeek());
        StateManager.getTime().addToTime(96);
        assertEquals("Tuesday", timeFormatter.getDayInWeek());
        StateManager.getTime().addToTime(96);
        assertEquals("Wednesday", timeFormatter.getDayInWeek());
        StateManager.getTime().addToTime(96);
        assertEquals("Thursday", timeFormatter.getDayInWeek());
        StateManager.getTime().addToTime(96);
        assertEquals("Friday", timeFormatter.getDayInWeek());
        StateManager.getTime().addToTime(96);
        assertEquals("Saturday", timeFormatter.getDayInWeek());
        StateManager.getTime().addToTime(96);
        assertEquals("Sunday", timeFormatter.getDayInWeek());
        StateManager.getTime().addToTime(96);
        assertEquals(2, timeFormatter.getWeekCount());
        assertEquals("Monday", timeFormatter.getDayInWeek());
        StateManager.getTime().addToTime(768);
        assertEquals(3, timeFormatter.getWeekCount());
        assertEquals("Tuesday", timeFormatter.getDayInWeek());

        DatabaseServices.closeDatabaseAccess();
    }
}
