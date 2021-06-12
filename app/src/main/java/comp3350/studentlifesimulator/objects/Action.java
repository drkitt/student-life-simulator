package comp3350.studentlifesimulator.objects;

public class Action {
    private String actionName;
    private int energyUnit;
    private int timeUnit;

    // Units default to 1
    public Action(String newAction) {
        actionName = newAction;
        energyUnit = 1;
        timeUnit = 1;
    }

    // Values must stay within ENERGY-BAR and TIME limits
    public Action(String newAction, int newEnergy, int newTime) {
        if (newEnergy < -Student.getMaxEnergy() || newEnergy == 0 || newEnergy > Student.getMaxEnergy()) {
            throw new IllegalArgumentException("Actions must have an energy value within the energy bar limits.");
        }

        if (newTime < 1) { // Every action must have a positive non-zero time value
            throw new IllegalArgumentException("Actions must have a time value within the global time limits.");
        }

        actionName = newAction;
        energyUnit = newEnergy;
        timeUnit = newTime;
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
}
