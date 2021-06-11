package comp3350.studentlifesimulator.objects;

public class Time {

    private final int timeMaxTime;
    private int timeUnitTime;
    private int timeDays;
    private int timeCurrentUnit;
    private int timeCurrentTime;
    private int timeStartTime;

    public Time(int unitTime, int maxTime) {
       if(unitTime != 30 && unitTime !=60){
           throw new IllegalArgumentException("unit time must be 30 or 60 minutes");
       }

       if(maxTime <= 0 || maxTime%30 != 0 || maxTime>480){
           throw new IllegalArgumentException("max time must be positive, less than 480 minutes and multiple of 30");
       }
       this.timeMaxTime = maxTime;
       this.timeUnitTime = unitTime;
       this.timeDays = 1;
       this.timeCurrentUnit = 0;
       this.timeCurrentTime = 0;
       this.timeStartTime = 9;
    }

    public int getTimeDays() {
        return timeDays;
    }

    public int getTimeUnitTime() {
        return timeUnitTime;
    }

    public int getTimeMaxTime() {
        return timeMaxTime;
    }

    public int getTimeCurrentUnit() {
        return timeCurrentUnit;
    }

    public int getTimeCurrentTime() {
        return timeCurrentTime;
    }

    public int getTimeStartTime() {
        return timeStartTime;
    }

    public int timeUnitIncrement(){

        int timeMaxUnit = timeMaxTime/timeUnitTime;

        if(timeCurrentUnit+1<timeMaxUnit){

            timeCurrentUnit = timeCurrentUnit+1;
            timeCurrentTime = timeCurrentTime + timeUnitTime;

        }else if(timeCurrentUnit+1==timeMaxUnit){
            timeDays++;
            timeCurrentUnit = 0;
            timeCurrentTime = 0;
        }
        return timeCurrentUnit;
    }

}
