package comp3350.studentlifesimulator.tests.objects;

import org.junit.Test;

import comp3350.studentlifesimulator.objects.Interaction;

import static org.junit.Assert.*;

public class TestInteraction {

    @Test
    public void testValidInteractions() {
        Interaction study = new Interaction("Study");
        assertEquals("Study", study.getInteractionName());
        assertEquals(1, study.getValue());

        Interaction exercise = new Interaction("Exercise", 2);
        assertEquals("Exercise", exercise.getInteractionName());
        assertEquals(2, exercise.getValue());
    }

    @Test
    public void testInvalidInteractionValues() {
        assertThrows(
                IllegalArgumentException.class,
                ()->new Interaction("InvalidAction", 0)
        );

        assertThrows(
                IllegalArgumentException.class,
                ()->new Interaction("InvalidAction", -1)
        );

        //TO-DO: determine energy cost value limits
        assertThrows(
                IllegalArgumentException.class,
                ()->new Interaction("InvalidAction", 10)
        );
    }
}