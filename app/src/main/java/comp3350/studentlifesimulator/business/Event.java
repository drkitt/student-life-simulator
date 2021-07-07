package comp3350.studentlifesimulator.business;

import java.util.ArrayList;

import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;

public class Event {

    private final Time entireDay;
    private Action currentActivity;
    private final ArrayList<Course> listOfCourses;
    private final ArrayList<Course> studentCourses;
    private final ArrayList<Boolean> classTime;
    private final Student currentStudent;
    private int currTimeIndex;

    Event(Student currStudent) {
        entireDay = new Time(96);
        listOfCourses = DatabaseManager.getAvailableCourses();
        studentCourses = DatabaseManager.getSelectedCourses();
        classTime = new ArrayList<>();
        currentStudent = currStudent;
        setClassTime();
    }



    public ArrayList<Action> getCurrentPossibleEvent() {

        ArrayList<Action> possibleEvents = new ArrayList<>();

        if (hasClass() && currentStudent.getCurrentEnergy() > calculatePercentage(10, EnergyBar.getMaxEnergy())) {

            possibleEvents.add(new Action("Attend Class",
                    -calculatePercentage(10, EnergyBar.getMaxEnergy()),
                    calculatePercentage(5, entireDay.getTimePerDay()) , 0));

            possibleEvents.add(new Action("Skip Class",
                    -calculatePercentage(1, EnergyBar.getMaxEnergy()),
                    calculatePercentage(1, entireDay.getTimePerDay()) , 0 )); //TODO: Skip class should be no activity
        }
        else if (currentActivity != null && currentActivity.getActionName().equalsIgnoreCase("Attend Class")) {

            if (currentStudent.getCurrentEnergy() > calculatePercentage(5, EnergyBar.getMaxEnergy())) {

                possibleEvents.add(new Action("Quick Study",
                        -calculatePercentage(5, EnergyBar.getMaxEnergy()),
                        calculatePercentage(5, entireDay.getTimePerDay()) , 0));

                possibleEvents.add(new Action("Listen to Instructor",
                        -calculatePercentage(5, EnergyBar.getMaxEnergy()),
                        calculatePercentage(5, entireDay.getTimePerDay()) , 0));

                possibleEvents.add(new Action("Talk with Friends",
                        -calculatePercentage(5, EnergyBar.getMaxEnergy()),
                        calculatePercentage(5, entireDay.getTimePerDay()) , 0));
            }

            possibleEvents.add(new Action("Nap",
                    calculatePercentage(5, EnergyBar.getMaxEnergy()),
                    calculatePercentage(5, entireDay.getTimePerDay()) , 0));

        }
        else if (currentStudent.getCurrentEnergy() <= calculatePercentage(15, EnergyBar.getMaxEnergy())) {

            possibleEvents.add(new Action("Hibernate (Long sleep)", EnergyBar.getMaxEnergy(),
                    calculatePercentage(50, entireDay.getTimePerDay()) , 0));

            possibleEvents.add(new Action("Sleep",
                    calculatePercentage(15, EnergyBar.getMaxEnergy()),
                    calculatePercentage(15, entireDay.getTimePerDay()), 0));

            possibleEvents.add(new Action("Nap",
                    calculatePercentage(5, EnergyBar.getMaxEnergy()),
                    calculatePercentage(5, entireDay.getTimePerDay()), 0));
        }
        else {

            possibleEvents.add(new Action("Marathon Study",
                    currentStudent.getCurrentEnergy(), entireDay.getCurrentTime() , 0));

            possibleEvents.add(new Action("Medium Study",
                    -calculatePercentage(15, EnergyBar.getMaxEnergy()),
                    calculatePercentage(15, entireDay.getTimePerDay()) , 0));

            possibleEvents.add(new Action("Quick Study",
                    -calculatePercentage(5, EnergyBar.getMaxEnergy()),
                    calculatePercentage(5, entireDay.getTimePerDay()) , 0));

            possibleEvents.add(new Action("Hibernate (Long sleep)", EnergyBar.getMaxEnergy(),
                    calculatePercentage(50, entireDay.getTimePerDay()), 0));

            possibleEvents.add(new Action("Sleep",
                    calculatePercentage(15, EnergyBar.getMaxEnergy()),
                    calculatePercentage(15, entireDay.getTimePerDay()) , 0));

            possibleEvents.add(new Action("Nap",
                    calculatePercentage(5, EnergyBar.getMaxEnergy()),
                    calculatePercentage(5, entireDay.getTimePerDay()) , 0));
        }

        currTimeIndex = (currTimeIndex + 1) % listOfCourses.size();
        return possibleEvents;
    }

    public void setCurrentActivity(Action currActivity) {
        currentActivity = currActivity;
    }

    private boolean hasClass() {
        return classTime.get(currTimeIndex);
    }

    private void setClassTime() {

        for (int index = 0; index < listOfCourses.size(); index++) {

            if (index < studentCourses.size() && listOfCourses.get(index).equals(studentCourses.get(index))) {
                classTime.add(true);
            }
            else {
                classTime.add(false);
            }
        }
    }

    private int calculatePercentage(int value, int total) {
        return (value / total) * 100;
    }
}
