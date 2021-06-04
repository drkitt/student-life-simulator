package comp3350.studentlifesimulator.objects;

public class Course {

    private final String courseID;
    private final String courseName;

    public Course(String ID, String name){
        courseID = ID;
        courseName = name;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String toString() {
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }

    public boolean equals(Course other) {
        boolean result = false;
        if(other != null) {
            result = other.courseID.equals(courseID) && other.courseName.equals(courseName);
        }
        return result;
    }

}
