package comp3350.studentlifesimulator.persistence;

import java.util.ArrayList;

import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.Student;

public interface DatabaseAccessInterface {
    void openDB(String databasePath);

    void closeDB();

    Student getStudent();

    void updateStudent(Student newStudent);

    ArrayList<Course> getCourses();

    ArrayList<Course> getSelectedCourses();

    void addSelectedCourse(Course course);

    boolean removeSelectedCourse(Course course);
}
