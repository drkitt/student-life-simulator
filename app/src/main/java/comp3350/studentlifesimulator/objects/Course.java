package comp3350.studentlifesimulator.objects;

import java.util.ArrayList;

import comp3350.studentlifesimulator.business.TimeFormatter;

public class Course {
    private final String courseID;
    private final String courseName;
    private final ArrayList<Weekday> classDays;
    private final int classTime;

    public Course(String ID, String name , ArrayList<Weekday> courseDays , int courseTime) {
        if ((ID == null || name == null) || (courseDays == null || courseDays.size() <= 0)) {
            throw new NullPointerException("Invalid course argument passed in");
        }

        if ((courseTime < 32 || courseTime > 72) ||
                (courseDays.contains(Weekday.Saturday) || courseDays.contains(Weekday.Sunday))) {
            throw new NullPointerException("Course time must be between 8am to 6pm and Course days cannot be Saturday or Sunday");
        }

        courseID = ID.trim();
        courseName = name.trim();
        classDays = courseDays;
        classTime = courseTime;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getClassTime() {
        return classTime;
    }

    public ArrayList<Weekday> getClassDays() {
        return classDays;
    }

    public String toString() {
        StringBuilder courseTime = new StringBuilder();
        TimeFormatter timeForClass = new TimeFormatter(classTime);
        String minute;

        if(timeForClass.getMinute() <= 9) {
            minute = "0" + timeForClass.getMinute();
        }
        else {
            minute = timeForClass.getMinute() + "";
        }

        for(int index = 0; index < classDays.size(); index++) {

            if(classDays.get(index) == Weekday.Thursday) {
                courseTime.append("TR");
            }
            else {
                courseTime.append(classDays.get(index).name().charAt(0));
            }

            if(index != classDays.size() - 1) {
                courseTime.append(", ");
            }
        }
        return courseID + " - "+ courseName + "\n"
                + courseTime + " - "+ timeForClass.getHour12() + ":"+ minute + " "
                + timeForClass.getSuffix() ;
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
