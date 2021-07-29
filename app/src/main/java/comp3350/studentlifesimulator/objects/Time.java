package comp3350.studentlifesimulator.objects;

public class Time {
    private final int unitsPerDay;
    private final int startTime;
    private int day;
    private int currentUnit;

    public Time(int startTime, int unitsPerDay) {
       this.unitsPerDay = unitsPerDay;
       this.day = 1;
       this.currentUnit = 0;
       addToTime(startTime);
       this.startTime = currentUnit;
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

    public void incrementTime() {
        if (currentUnit + 1 < unitsPerDay) {
            currentUnit++;
        }
        else if (currentUnit + 1 == unitsPerDay) {
            day++;
            currentUnit = 0;
        }
    }

    public void addToTime(int timeStep) {
        int newTime = currentUnit + timeStep;

        if (timeStep < 0) {
            throw new IllegalArgumentException("Time step must be non-negative");
        }

        while (newTime >= unitsPerDay) {
            day++;
            newTime -= unitsPerDay;
        }

        currentUnit = newTime;
    }
}
