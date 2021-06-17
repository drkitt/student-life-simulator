package comp3350.studentlifesimulator.objects;

public class Student {
    private static final int MAX_ENERGY = 10;
    private String studentID;
    private String studentName;
    private EnergyBar energyBar;

    public Student(String newStudentName) {
        studentID = null;
        studentName = newStudentName;
        energyBar = new EnergyBar(MAX_ENERGY, MAX_ENERGY);
    }

    public Student(String newStudentID,String newStudentName) {
        studentID = newStudentID;
        studentName = newStudentName;
        energyBar = new EnergyBar(MAX_ENERGY, MAX_ENERGY);
    }

    public static int getMaxEnergy() {
        return (MAX_ENERGY);
    }

    public String getStudentID()
    {
        return (studentID);
    }

    public String getStudentName()
    {
        return (studentName);
    }

    public int getCurrentEnergy() {
        return energyBar.getCurrentEnergy();
    }

    public boolean canDoAction(Action toDo) {
        return energyBar.canAdjustEnergy(toDo.getEnergyUnit());
    }

    public void doAction(Action toDo) {
        energyBar.adjustEnergy(toDo.getEnergyUnit());
    }
}
