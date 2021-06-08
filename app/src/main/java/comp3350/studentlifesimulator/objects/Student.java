package comp3350.studentlifesimulator.objects;

public class Student
{
    private final int STARTING_ENERGY = 10;
    private String studentID;
    private String studentName;
    private EnergyBar energyBar;

    public Student(String newStudentName)
    {
        studentID = null;
        studentName = newStudentName;
        energyBar = new EnergyBar(STARTING_ENERGY, STARTING_ENERGY);
    }

    public Student(String newStudentID,String newStudentName)
    {
        studentID = newStudentID;
        studentName = newStudentName;
        energyBar = new EnergyBar(STARTING_ENERGY, STARTING_ENERGY);
    }

    public String getStudentID()
    {
        return (studentID);
    }

    public String getStudentName()
    {
        return (studentName);
    }

    public boolean doAction(Action toDo) {
        return false;
    }
}
