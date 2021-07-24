package comp3350.studentlifesimulator.business;


import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.objects.Weekday;


public class TimeFormatter {
    private static final int MINUTES_PER_TIME_UNIT = 15;
    private final Time time;


    public TimeFormatter() {
        this.time = StateManager.getTime();
    }

    public TimeFormatter(int newTime){
        time = new Time(newTime , 96);
    }

    public int getHour() {
        int hour = twentyFourHour();

        if (hour > 12) {
            hour -= 12;
        }

        return hour;
    }

    public int getMinute() {
        return time.getCurrentTime() * MINUTES_PER_TIME_UNIT % 60;
    }

    public String getSuffix() {
        String suffix;

        if (twentyFourHour() < 12) {
            suffix = "AM";
        }
        else {
            if (twentyFourHour() > 12) {
                decreaseHour();
            }
            suffix = "PM";
        }
        return suffix;
    }

    public String getDayInWeek() {
        int dayIndex = time.getDays() % 7;
        String dayInWeek;

        dayInWeek = Weekday.values()[dayIndex].name();

        return dayInWeek;
    }

    public int getWeekCount() {
        int weekCount = (time.getDays() - 1) / 7;
        weekCount ++;

        return weekCount;
    }

    public int  decreaseHour(){
        return  this.getHour() - 12;
    }

    private int twentyFourHour() {
        return time.getCurrentTime() * MINUTES_PER_TIME_UNIT / 60;
    }
}
