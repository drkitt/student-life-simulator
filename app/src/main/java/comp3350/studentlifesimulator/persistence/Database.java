package comp3350.studentlifesimulator.persistence;

import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.EnergyBar;

import java.util.ArrayList;

public class Database {
    private Student student;
    private EnergyBar energyBar;
    private ArrayList<Course> courses;
    private ArrayList<Course> selected;

    private final int MAX_ENERGY = 20;

    public Database() {
        initializeData();
    }

    public void updateStudentState(Student newStudent) {
        student = new Student(newStudent.getStudentID(), newStudent.getStudentName());
    }

    public void addSelectedCourse(Course course) {
        selected.add(new Course(course.getCourseID(), course.getCourseName()));
    }

    public boolean removeSelectedCourse(Course course) {
        int index = 0;
        int count = 0;
        boolean removed = false;

        while (count < courses.size()) {
            if(courses.get(count).equals(course)) {
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
        return new Student(student.getStudentID(), student.getStudentName());
    }

    public ArrayList<Course> getCourses() {
        return copyCourseList(courses);
    }

    public ArrayList<Course> getSelectedCourses() {
        return copyCourseList(selected);
    }

    private ArrayList<Course> copyCourseList(ArrayList<Course> courseList) {
        ArrayList<Course> tempCourses = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++) {
            tempCourses.add(new Course(courseList.get(i).getCourseID(), courseList.get(i).getCourseName()));
        }

        return tempCourses;
    }

    private void initializeData() {
        Course course;

        student = new Student("1234567", "Tired Student");

        energyBar = new EnergyBar(MAX_ENERGY, MAX_ENERGY);

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
    }
}
