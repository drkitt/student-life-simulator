package comp3350.studentlifesimulator.objects;

public class Student
{
    private String studentID;
    private String studentName;

    public Student(String newStudentName)
    {
        studentID = null;
        studentName = newStudentName;
    }

    public Student(String newStudentID,String newStudentName)
    {
        studentID = newStudentID;
        studentName = newStudentName;
    }

    public String getStudentID()
    {
        return (studentID);
    }

    public String getStudentName()
    {
        return (studentName);
    }
}
