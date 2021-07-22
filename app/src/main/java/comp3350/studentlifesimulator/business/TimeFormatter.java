package comp3350.studentlifesimulator.business;


import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.objects.Weekday;


public class TimeFormatter {
    private static final int MINUTES_PER_TIME_UNIT = 15;
    private final Time time;


    public TimeFormatter() {
        this.time = StateManager.getTime();
    }

    public int getHour() {
        int hour = time.getCurrentTime() * MINUTES_PER_TIME_UNIT / 60;
        return hour;
    }

    public int getMinute() {
        int minute = time.getCurrentTime() * MINUTES_PER_TIME_UNIT % 60;
        return minute;
    }

    public String getSuffix() {
        String suffix;

        if (this.getHour()< 12) {
            suffix = "AM";
        }
        else {
            if (this.getHour() > 12) {
                decreaseHour();
            }
            suffix = "PM";
        }
        return suffix;
    }

    public String getDayInWeek() {
        int dayIndex = time.getDays() % 7;
        String dayInWeek;

        switch (dayIndex){
            case 1:
                dayInWeek = Weekday.Monday.name();
                break;

            case 2:
                dayInWeek = Weekday.Tuesday.name();
                break;

            case 3:
                dayInWeek = Weekday.Wednesday.name();
                break;

            case 4:
                dayInWeek = Weekday.Thursday.name();
                break;

            case 5:
                dayInWeek = Weekday.Friday.name();
                break;

            case 6:
                dayInWeek = Weekday.Saturday.name();
                break;

            case 0:
                dayInWeek = Weekday.Sunday.name();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + dayIndex);
        }

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
}
