package comp3350.studentlifesimulator.objects;

public class Student {
    private String studentName;
    private EnergyBar energyBar;
    private int score;

    public Student(String newStudentName, EnergyBar energy, int currScore) {
        studentName = newStudentName;
        energyBar = energy;
        score = currScore;
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

    public int getScore() {
        return score;
    }

    public void addToScore(int points) {
        score += points;
    }

    public boolean canDoAction(Action toDo) {
        return energyBar.canAdjustEnergy(toDo.getEnergyUnit());
    }

    public void doAction(Action toDo) {
        energyBar.adjustEnergy(toDo.getEnergyUnit());
        score += toDo.getPointsUnit();
    }
}
