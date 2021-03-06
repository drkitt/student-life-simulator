package comp3350.studentlifesimulator.objects;

public class Action {
    private final String actionName;
    private final int energyUnit;
    private final int timeUnit;
    private final int pointsUnit;

    public Action(String newAction) {
        actionName = newAction;
        energyUnit = 1;
        timeUnit = 1;
        pointsUnit = 1;
    }

    public Action(String newAction, int newEnergy, int newTime, int newPoints) {
        if (newEnergy < -Student.getMaxEnergy() || newEnergy == 0 || newEnergy > Student.getMaxEnergy()) {
            throw new IllegalArgumentException("Actions must have an energy value within the energy bar limits.");
        }
        if (newTime < 1) {
            throw new IllegalArgumentException("Actions must have a time value within the global time limits.");
        }

        actionName = newAction;
        energyUnit = newEnergy;
        timeUnit = newTime;
        pointsUnit = newPoints;
    }

    public String getActionName() {
        return actionName;
    }

    public int getEnergyUnit() {
        return energyUnit;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public int getPointsUnit() {
        return pointsUnit;
    }
}
