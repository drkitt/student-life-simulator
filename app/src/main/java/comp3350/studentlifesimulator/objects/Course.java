package comp3350.studentlifesimulator.objects;

public class Course {

    private final String courseID;
    private final String courseName;

    public Course(String ID, String name) {

        if(ID == null || name == null) {
            throw new NullPointerException("Invalid course argument passed in");
        }
        courseID = ID.trim();
        courseName = name.trim();
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String toString() {
        return courseID + " - "+ courseName;
    }

    public boolean equals(Course other) {
        boolean result;
        if(other != null) {
            result = other.courseID.equals(courseID) && other.courseName.equals(courseName);
        }
        else{
            throw new NullPointerException("Invalid course argument passed in");
        }
        return result;
    }

}
