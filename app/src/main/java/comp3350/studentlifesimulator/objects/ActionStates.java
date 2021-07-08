package comp3350.studentlifesimulator.objects;

public enum ActionStates {
    HAS_CLASS,
    IN_CLASS_HIGH, // Energy is above 5%
    IN_CLASS_LOW,  // Energy is below 5%
    FREE_TIME,
    LOW_ENERGY
}
