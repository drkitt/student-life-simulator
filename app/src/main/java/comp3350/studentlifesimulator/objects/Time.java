package comp3350.studentlifesimulator.objects;

public class Time {
    private final int unitsPerDay;
    private int day;
    private int currentUnit;
    private int startTime;

    public Time(int unitsPerDay) {
       this.unitsPerDay = unitsPerDay;
       this.day = 1;
       this.currentUnit = 0;
       this.startTime = 32;
    }

    public int getDays() {
        return day;
    }

    public int getTimePerDay() {
        return unitsPerDay;
    }

    public int getCurrentTime() {
        return currentUnit;
    }

    public int getStartTime() {
        return startTime;
    }

    public int incrementTime() {
        if (currentUnit + 1 < unitsPerDay) {
            currentUnit++;
        }
        else if (currentUnit + 1 == unitsPerDay) {
            day++;
            currentUnit = 0;
        }

        return currentUnit;
    }

}
