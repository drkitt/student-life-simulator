package comp3350.studentlifesimulator.objects;

public class EnergyBar {
    private int currentEnergy;
    private static final int MAX_ENERGY = 12;

    public EnergyBar(int initialEnergy) {
        if (initialEnergy < 0) {
            throw new IllegalArgumentException("Initial energy must be positive");
        }
        if (initialEnergy > MAX_ENERGY) {
            throw new IllegalArgumentException("Initial energy must be less than MAX_ENERGY");
        }

        setCurrentEnergy(initialEnergy);
    }

    public static int getMaxEnergy() {
        return MAX_ENERGY;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int newEnergy) {
        if (newEnergy < 0) {
            throw new IllegalArgumentException("Current energy must be non-negative");
        }
        if (newEnergy > MAX_ENERGY) {
            throw new IllegalArgumentException("Current energy can't exceed max energy");
        }

        currentEnergy = newEnergy;
    }

    public void adjustEnergy(int delta) {
        setCurrentEnergy(currentEnergy + delta);
    }

    public boolean canAdjustEnergy(int delta) {
        return 0 <= currentEnergy + delta && currentEnergy + delta <= MAX_ENERGY;
    }
}
