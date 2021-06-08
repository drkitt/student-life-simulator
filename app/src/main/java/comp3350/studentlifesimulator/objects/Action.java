package comp3350.studentlifesimulator.objects;

public class Action {
    private String actionName;
    private int energyUnit;
    private int timeUnit;

    // Defaults to 1; Max value of 4, ADJUST BASED ON ENERGY-BAR LIMITS
    public Action(String newAction) {
        actionName = newAction;
        energyUnit = 1;
        timeUnit = 1;
    }

    public Action(String newAction, int newEnergy, int newTime) {
        if (newEnergy < 1 || newEnergy > 4 ) { // TO-DO: Use the limits created by the Energy bar!!
            throw new IllegalArgumentException("Actions must have an energy value within the energy bar limits.");
        }

        if (newTime < 1 || newTime > 4 ) { // TO-DO: Use the limits created by the global time!!
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
