package comp3350.studentlifesimulator.tests.persistence;

import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.objects.Weekday;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

public class StubDatabase implements DatabaseAccessInterface {
    private Student student;
    private Time time;
    private ArrayList<Course> courses;
    private ArrayList<Course> selected;
    private Dictionary<String, Action>[] actions;
    private String[] assets;

    public void openDB(String databasePath) {
        initializeData();
    }

    public void closeDB() {
        student = null;
        time = null;
        courses = null;
        selected = null;
        actions = null;
        assets = null;
    }

    public void updateStudent(Student newStudent) {
        student = new Student(newStudent.getStudentName(),
                new EnergyBar(newStudent.getCurrentEnergy()), newStudent.getScore());
    }

    public void addSelectedCourse(Course course) {
        selected.add(new Course(course.getCourseID(), course.getCourseName() , course.getClassDays() , course.getClassTime()));
    }

    public boolean removeSelectedCourse(Course course) {
        int index = 0;
        int count = 0;
        boolean removed = false;

        while (count < selected.size()) {
            if (selected.get(count).equals(course)) {
                index = count;
                removed = true;
                count = selected.size();
            }

            count++;
        }

        if (removed) {
            selected.remove(index);
        }

        return removed;
    }

    public Student getStudent() {
        return new Student(
                student.getStudentName(),
                new EnergyBar(student.getCurrentEnergy()),
                student.getScore()
        );
    }

    public ArrayList<Course> getCourses() {
        return copyCourseList(courses);
    }

    public ArrayList<Course> getSelectedCourses() {
        return copyCourseList(selected);
    }


    public Dictionary<String, Action> getActions(int key) {
        Dictionary<String, Action> actionList = actions[key];

        initializeActions();

        return actionList;
    }

    public Time getTime() {
        return new Time(
                time.getCurrentTime() + (time.getTimePerDay() * (time.getDays() - 1)),
                time.getTimePerDay()
        );
    }

    public void updateTime(Time newTime) {
        time = new Time(
                newTime.getCurrentTime() + (newTime.getTimePerDay() * (newTime.getDays() - 1)),
                newTime.getTimePerDay()
        );
    }

    public String getCharacterAsset(int type) {
        return assets[type];
    }

    public void updateCharacterAsset(int type, String newAsset) {
        assets[type] = newAsset;
    }

    private ArrayList<Course> copyCourseList(ArrayList<Course> courseList) {
        ArrayList<Course> tempCourses = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++) {
            tempCourses.add(new Course(courseList.get(i).getCourseID(), courseList.get(i).getCourseName(),
                            courseList.get(i).getClassDays(), courseList.get(i).getClassTime()));
        }

        return tempCourses;
    }

    private void initializeData() {
        Course course;

        student = new Student("Anne Otherstudent", new EnergyBar(12), 0);

        time = new Time(24, 96);

        courses = new ArrayList<>();

        course = new Course(
                "COMP1010",
                "Introductory Computer Science 1",
                new ArrayList<>(Arrays.asList(Weekday.Monday , Weekday.Wednesday)),
                32
        );
        courses.add(course);
        course = new Course(
                "COMP1020",
                "Introductory Computer Science 2",
                new ArrayList<>(Arrays.asList(Weekday.Thursday , Weekday.Friday)),
                72
        );
        courses.add(course);
        course = new Course(
                "COMP2140",
                "Data Structures and Algorithms",
                new ArrayList<>(Arrays.asList(Weekday.Tuesday, Weekday.Thursday)),
                40
        );
        courses.add(course);
        course = new Course(
                "COMP2150",
                "Object Orientation",
                new ArrayList<>(Arrays.asList(Weekday.Monday, Weekday.Wednesday)),
                44
        );
        courses.add(course);
        course = new Course(
                "COMP2160",
                "Programming Practices",
                new ArrayList<>(Arrays.asList(Weekday.Wednesday, Weekday.Friday)),
                60
        );
        courses.add(course);

        selected = new ArrayList<>();

        initializeActions();

        assets = new String[4];

        assets[0] = "eyes_glasses";
        assets[1] = "hair4_medium";
        assets[2] = "skin_fair";
        assets[3] = "shirt_purple_featuring_whee";
    }

    private void initializeActions() {
        Action action;

        actions = new Hashtable[4];

        actions[0] = new Hashtable<>();

        action = new Action("Nap", 1, 4, 2);
        actions[0].put(action.getActionName(), action);

        actions[1] = new Hashtable<>();

        action = new Action("Nap", 1, 4, -2);
        actions[1].put(action.getActionName(), action);
        action = new Action("Quick Study", -1, 4, 0);
        actions[1].put(action.getActionName(), action);
        action = new Action("Listen to Instructor", -2, 4, 6);
        actions[1].put(action.getActionName(), action);
        action = new Action("Talk with Friends", -1, 4, -3);
        actions[1].put(action.getActionName(), action);

        actions[2] = new Hashtable<>();

        action = new Action("Hibernate", 12, 48, 12);
        actions[2].put(action.getActionName(), action);
        action = new Action("Sleep", 5, 16, 6);
        actions[2].put(action.getActionName(), action);
        action = new Action("Nap", 1, 4, 2);
        actions[2].put(action.getActionName(), action);

        actions[3] = new Hashtable<>();

        action = new Action("Marathon Study", -6, 32, 24);
        actions[3].put(action.getActionName(), action);
        action = new Action("Study", -3, 16, 18);
        actions[3].put(action.getActionName(), action);
        action = new Action("Quick Study", -1, 4, 5);
        actions[3].put(action.getActionName(), action);
        action = new Action("Hibernate", 12, 48, 12);
        actions[3].put(action.getActionName(), action);
        action = new Action("Sleep", 5, 16, 6);
        actions[3].put(action.getActionName(), action);
        action = new Action("Nap", 1, 4, 2);
        actions[3].put(action.getActionName(), action);
    }
}
