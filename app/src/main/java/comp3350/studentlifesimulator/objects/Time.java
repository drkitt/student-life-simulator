package comp3350.studentlifesimulator.objects;

public class Time {
    private final int unitsPerDay;
    private int day;
    private int currentUnit;
    private int startTime;

    public Time(int startTime, int unitsPerDay) {
       this.unitsPerDay = unitsPerDay;
       this.day = 1;
       this.startTime = startTime;
       this.currentUnit = startTime;
    }

    public Time(int unitsPerDay) {
        this(0, unitsPerDay);
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

    public int addToTime(int timeStep) {
        if (timeStep < 0) {
            throw new IllegalArgumentException("Time step must be non-negative");
        }

        int newTime = currentUnit + timeStep;

        while (newTime >= unitsPerDay) {
            day++;
            newTime -= unitsPerDay;
        }

        currentUnit = newTime;
        return currentUnit;
    }
}
