package comp3350.studentlifesimulator.business;

import java.util.ArrayList;

import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;

public class StateManager {

    private static Time clock;
    private static boolean inClass;
    private static ArrayList<Course> studentCourses;
    private static Student currentStudent;
    private static ArrayList<Action> inClassLow;
    private static ArrayList<Action> inClassHigh;
    private static ArrayList<Action> lowEnergy;
    private static ArrayList<Action> freeTime;

    public static void initialize() {
        inClass = false;
        clock = DatabaseManager.getTime();
        studentCourses =  DatabaseManager.getSelectedCourses();
        currentStudent = DatabaseManager.getStudent();
        inClassLow = DatabaseManager.getActions(0);
        inClassHigh = DatabaseManager.getActions(1);
        lowEnergy = DatabaseManager.getActions(2);
        freeTime = DatabaseManager.getActions(3);
    }

    public static Student getCurrentStudent() {
        return currentStudent;
    }

    public static Time getTime() {
        return clock;
    }

    public static int getState() {
        int state;

        if (hasClass() && currentStudent.getCurrentEnergy() > calculatePercentage(10, EnergyBar.getMaxEnergy())) {
            state = 4;
        }
        else if (inClass) {

            if (currentStudent.getCurrentEnergy() > calculatePercentage(5, EnergyBar.getMaxEnergy())) {
                state = 1;
            }
            else{
               state = 0;
            }

        }
        else if (currentStudent.getCurrentEnergy() <= calculatePercentage(15, EnergyBar.getMaxEnergy())) {
            state = 2;
        }
        else {
            state = 3;
        }

        return state;
    }

    public static ArrayList<Action> getCurrentPossibleActions(int currState){
        ArrayList<Action> possibleEvent = null;
        if(currState == 0){
            possibleEvent = inClassLow;
        }
        else if(currState == 1){
            possibleEvent = inClassHigh;
        }
        else if(currState == 2){
            possibleEvent = lowEnergy;
        }
        else if(currState == 3) {
            possibleEvent = freeTime;
        }

        return possibleEvent;
    }

    public static void setInClass(boolean classTime) {
        inClass = classTime;
    }

    private static boolean hasClass() {
        boolean classTime = false;

        for (int i = 0; i < studentCourses.size() && !classTime; i++) {
            if (clock.getCurrentTime() == 32 + (i * 4)) {
                classTime = true;
            }
        }

        return classTime;
    }

    private static int calculatePercentage(int value, int total) {
        return (value / total) * 100;
    }
}
