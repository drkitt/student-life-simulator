package comp3350.studentlifesimulator.objects;

import java.util.ArrayList;

public class Course {
    private final String courseID;
    private final String courseName;
    private final ArrayList<Weekday> classDays;
    private final ArrayList<Time> classTime;

    public Course(String ID, String name , ArrayList<Weekday> courseDays , ArrayList<Time> courseTime ) {

        if (ID == null || name == null || (courseDays != null && courseDays.size() <= 0) ||
            (courseTime != null && courseTime.size() <= 0)) {
            throw new NullPointerException("Invalid course argument passed in");
        }

        if(courseDays.size() != courseTime.size()) {
            throw new NullPointerException("Course days and course time must have the same size");
        }

        if(!isWithinTimeFrame(courseDays , courseTime)) {
            throw new NullPointerException("Course time must be between 8am to 6pm and Course days cannot be Saturday or Sunday");
        }

        courseID = ID.trim();
        courseName = name.trim();
        classDays = courseDays;
        classTime = courseTime;
    }

    private boolean isWithinTimeFrame(ArrayList<Weekday> courseDays , ArrayList<Time> courseTime) {
        boolean value = true;
        Time currTime;
        Weekday currDay;

        for (int index = 0; index < courseTime.size() && value; index++) {
            currTime = courseTime.get(index);
            currDay = courseDays.get(index);

            value = (currTime.getCurrentTime() >= 32 && currTime.getCurrentTime() <= 72) &&
                    (currDay != Weekday.Saturday && currDay != Weekday.Sunday);
        }

        return value;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public ArrayList<Time> getClassTime() {
        return classTime;
    }

    public ArrayList<Weekday> getClassDays() {
        return classDays;
    }

    public String toString() {
        return courseID + " - "+ courseName;
    }

    public boolean equals(Course other) {
        boolean result;

        if (other != null) {
            result = other.courseID.equals(courseID) && other.courseName.equals(courseName);
        }
        else {
            throw new NullPointerException("Invalid course argument passed in");
        }

        return result;
    }
}
