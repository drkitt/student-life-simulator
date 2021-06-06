package comp3350.studentlifesimulator.objects;

public class EnergyBar {
    private int currentEnergy;
    private final int maxEnergy;

    public EnergyBar(int maximumEnergy, int initialEnergy) {
        if (maximumEnergy <= 0) {
            throw new IllegalArgumentException("Maximum energy must be positive");
        }
        maxEnergy = maximumEnergy;

        setCurrentEnergy(initialEnergy);
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int newEnergy) {
        if (newEnergy < 0) {
            throw new IllegalArgumentException("Current energy must be non-negative");
        }
        if (newEnergy > maxEnergy) {
            throw new IllegalArgumentException("Current energy can't exceed max energy");
        }

        currentEnergy = newEnergy;
    }

    public void adjustEnergy(int delta) {
        setCurrentEnergy(currentEnergy + delta);
    }
}
