package comp3350.studentlifesimulator.business;

import java.util.ArrayList;
import java.util.Dictionary;

import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.ActionStates;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;

public class StateManager {

    private static Time clock;
    private static boolean inClass;
    private static boolean skipped;
    private static ArrayList<Course> studentCourses;
    private static Student currentStudent;
    private static Dictionary<String, Action> inClassLow;
    private static Dictionary<String, Action> inClassHigh;
    private static Dictionary<String, Action> lowEnergy;
    private static Dictionary<String, Action> freeTime;

    public static void initialize() {
        inClass = false;
        skipped = false;
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

    public static ActionStates getState() {
        ActionStates state;

        if (inClass) {
            if (currentStudent.getCurrentEnergy() > calculatePercentage(5, EnergyBar.getMaxEnergy())) {
                state = ActionStates.IN_CLASS_HIGH;
            }
            else {
               state = ActionStates.IN_CLASS_LOW;
            }
        }
        else if (skipped) {
            if (currentStudent.getCurrentEnergy() <= calculatePercentage(15, EnergyBar.getMaxEnergy())) {
                state = ActionStates.LOW_ENERGY;
            }
            else {
                state = ActionStates.FREE_TIME;
            }
        }
        else if (hasClass()) {
            state = ActionStates.HAS_CLASS;
        }
        else if (currentStudent.getCurrentEnergy() <= calculatePercentage(15, EnergyBar.getMaxEnergy())) {
            state = ActionStates.LOW_ENERGY;
        }
        else {
            state = ActionStates.FREE_TIME;
        }

        return state;
    }

    public static Dictionary<String, Action> getCurrentPossibleActions(ActionStates currState) {
        Dictionary<String, Action> possibleEvent = null;

        if (currState == ActionStates.IN_CLASS_LOW) {
            possibleEvent = inClassLow;
        }
        else if (currState == ActionStates.IN_CLASS_HIGH) {
            possibleEvent = inClassHigh;
        }
        else if (currState == ActionStates.LOW_ENERGY) {
            possibleEvent = lowEnergy;
        }
        else if (currState == ActionStates.FREE_TIME) {
            possibleEvent = freeTime;
        }

        return possibleEvent;
    }

    public static boolean getInClass() {
        return inClass;
    }

    public static void switchInClass() {
        inClass = !inClass;
    }

    public static boolean getSkipped() {
        return skipped;
    }

    public static void switchSkipped() {
        skipped = !skipped;
    }

    public static void dataWriteBack() {
        DatabaseManager.updateStudent(currentStudent);
        DatabaseManager.updateTime(clock);
    }

    private static boolean hasClass() {
        boolean classTime = false;

        for (int i = 0; i < studentCourses.size() && !classTime; i++) {
            classTime = clock.getCurrentTime() == 32 + (i * 4);
        }

        return classTime;
    }

    private static int calculatePercentage(int value, int total) {
        return (value / 100) * total;
    }
}
