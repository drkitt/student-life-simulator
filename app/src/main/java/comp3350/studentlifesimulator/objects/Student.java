package comp3350.studentlifesimulator.objects;

public class Student {
    private String studentName;
    private EnergyBar energyBar;

    public Student(String newStudentName, EnergyBar energy) {
        studentName = newStudentName;
        energyBar = energy;
    }

    public static int getMaxEnergy() {
        return (EnergyBar.getMaxEnergy());
    }

    public String getStudentName() {
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
