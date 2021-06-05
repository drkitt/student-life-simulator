package comp3350.studentlifesimulator.objects;

public class Interaction {
    private String interactionName;
    private int value;

    // Interaction type represents the energy consumed by the interaction
    // Defaults to 1; Max value of 4, ADJUST BASED ON ENERGY-BAR LIMITS
    public Interaction(String newInteraction) {
        interactionName = newInteraction;
        value = 1;
    }

    public Interaction(String newInteraction, int newValue) {
        if (newValue < 1 || newValue > 4) { // TO-DO: determine energy costs of each interaction
            throw new IllegalArgumentException("Interactions must have an energy value between 1-4");
        }
        interactionName = newInteraction;
        value = newValue;
    }

    public String getInteractionName(){
        return interactionName;
    }

    public int getValue(){
        return value;
    }
}
