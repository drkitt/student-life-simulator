package comp3350.studentlifesimulator.persistence;

import java.util.ArrayList;
import java.util.Dictionary;

import comp3350.studentlifesimulator.objects.Action;
import comp3350.studentlifesimulator.objects.Course;
import comp3350.studentlifesimulator.objects.Student;
import comp3350.studentlifesimulator.objects.Time;

public interface DatabaseAccessInterface {
    void openDB(String databasePath);

    void closeDB();

    Student getStudent();

    void updateStudent(Student newStudent);

    ArrayList<Course> getCourses();

    ArrayList<Course> getSelectedCourses();

    void addSelectedCourse(Course course);

    boolean removeSelectedCourse(Course course);

    Dictionary<String, Action> getActions(int key);

    Time getTime();

    void updateTime(Time time);

    String getCharacterAsset(int type);

    void updateCharacterAsset(int type, String newAsset);
}
