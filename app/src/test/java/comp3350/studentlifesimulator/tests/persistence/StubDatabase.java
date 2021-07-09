package comp3350.studentlifesimulator.tests.persistence;

import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Time;
import comp3350.studentlifesimulator.persistence.DatabaseAccessInterface;

import java.util.ArrayList;

public class StubDatabase implements DatabaseAccessInterface {
    private Student student;
    private Time time;
    private ArrayList<Course> courses;
    private ArrayList<Course> selected;
    private ArrayList<Action>[] actions;

    public void openDB(String databasePath) {
        initializeData();
    }

    public void closeDB() {
        student = null;
        time = null;
        courses = null;
        selected = null;
        actions = null;
    }

    public void updateStudent(Student newStudent) {
        student = new Student(newStudent.getStudentName(), new EnergyBar(newStudent.getCurrentEnergy()));
    }

    public void addSelectedCourse(Course course) {
        selected.add(new Course(course.getCourseID(), course.getCourseName()));
    }

    public boolean removeSelectedCourse(Course course) {
        int index = 0;
        int count = 0;
        boolean removed = false;

        while (count < courses.size()) {
            if (courses.get(count).equals(course)) {
                index = count;
                removed = true;
                count = courses.size();
            }

            count++;
        }

        if (removed) {
            courses.remove(index);
        }

        return removed;
    }

    public Student getStudent() {
        return new Student(student.getStudentName(), new EnergyBar(student.getCurrentEnergy()));
    }

    public ArrayList<Course> getCourses() {
        return copyCourseList(courses);
    }

    public ArrayList<Course> getSelectedCourses() {
        return copyCourseList(selected);
    }


    public ArrayList<Action> getActions(int key) {
        return actions[key];
    }

    public Time getTime() {
        return time;
    }

    public void updateTime(Time newTime) {
        time = new Time(newTime.getCurrentTime() +
                (newTime.getDays() * newTime.getTimePerDay()), newTime.getTimePerDay());
    }

    private ArrayList<Course> copyCourseList(ArrayList<Course> courseList) {
        ArrayList<Course> tempCourses = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++) {
            tempCourses.add(new Course(courseList.get(i).getCourseID(),
                    courseList.get(i).getCourseName()));
        }

        return tempCourses;
    }

    private void initializeData() {
        Course course;
        Action action;

        student = new Student("Anne Otherstudent", new EnergyBar(12));

        time = new Time(0, 96);

        courses = new ArrayList<>();

        course = new Course("COMP1010", "Introductory Computer Science 1");
        courses.add(course);
        course = new Course("COMP1020", "Introductory Computer Science 2");
        courses.add(course);
        course = new Course("COMP2140", "Data Structures and Algorithms");
        courses.add(course);
        course = new Course("COMP2150", "Object Orientation");
        courses.add(course);
        course = new Course("COMP2160", "Programming Practices");
        courses.add(course);

        selected = new ArrayList<>();

        actions = new ArrayList[4];

        actions[0] = new ArrayList<>();

        action = new Action("Nap", 1, 4, 2);
        actions[0].add(action);

        actions[1] = new ArrayList<>();

        action = new Action("Nap", 1, 4, 2);
        actions[1].add(action);
        action = new Action("Quick Study", -1, 4, 5);
        actions[1].add(action);
        action = new Action("Listen to Instructor", -2, 4, 6);
        actions[1].add(action);
        action = new Action("Talk with Friends", -1, 4, 3);
        actions[1].add(action);

        actions[2] = new ArrayList<>();

        action = new Action("Hibernate", 12, 48, 12);
        actions[2].add(action);
        action = new Action("Sleep", 5, 16, 6);
        actions[2].add(action);
        action = new Action("Nap", 1, 4, 2);
        actions[2].add(action);

        actions[3] = new ArrayList<>();

        action = new Action("Marathon Study", -6, 32, 24);
        actions[3].add(action);
        action = new Action("Study", -3, 16, 18);
        actions[3].add(action);
        action = new Action("Quick Study", -1, 4, 5);
        actions[3].add(action);
        action = new Action("Hibernate", 12, 48, 12);
        actions[3].add(action);
        action = new Action("Sleep", 5, 16, 6);
        actions[3].add(action);
        action = new Action("Nap", 1, 4, 2);
        actions[3].add(action);
    }
}
