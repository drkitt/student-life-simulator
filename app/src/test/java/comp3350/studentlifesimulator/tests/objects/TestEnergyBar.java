package comp3350.studentlifesimulator.tests.objects;

import org.junit.Test;
import comp3350.studentlifesimulator.objects.EnergyBar;
import static org.junit.Assert.*;

public class TestEnergyBar {
    @Test
    public void testMaxEnergy() {
        EnergyBar maxedOut = new EnergyBar(4, 4);
        assertEquals(4, maxedOut.getCurrentEnergy());

        assertThrows(
                IllegalArgumentException.class,
                () -> new EnergyBar(0, 0)
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new EnergyBar(-1, -1)
        );
    }

    @Test
    public void testInitialEnergy() {
        EnergyBar maxedOut = new EnergyBar(4, 4);
        assertEquals(4, maxedOut.getCurrentEnergy());
        EnergyBar partiallyDepleted = new EnergyBar(4, 2);
        assertEquals(2, partiallyDepleted.getCurrentEnergy());
        EnergyBar completelyDepleted = new EnergyBar(4, 0);
        assertEquals(0, completelyDepleted.getCurrentEnergy());

        assertThrows(
                IllegalArgumentException.class,
                () -> new EnergyBar(4, -1)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new EnergyBar(4, 5)
        );
    }

    @Test
    public void testAdjustingEnergy() {
        EnergyBar energy = new EnergyBar(4, 4);

        assertEquals(4, energy.getCurrentEnergy());

        energy.setCurrentEnergy(3);
        assertEquals(3, energy.getCurrentEnergy());

        assertTrue(energy.canAdjustEnergy(1));
        energy.adjustEnergy(1);
        assertEquals(4, energy.getCurrentEnergy());

        assertFalse(energy.canAdjustEnergy(1));
        assertThrows(
                IllegalArgumentException.class,
                () -> energy.adjustEnergy(1)
        );
        assertEquals(4, energy.getCurrentEnergy());

        assertTrue(energy.canAdjustEnergy(-2));
        energy.adjustEnergy(-2);
        assertEquals(2, energy.getCurrentEnergy());

        assertTrue(energy.canAdjustEnergy(-2));
        energy.adjustEnergy(-2);
        assertEquals(0, energy.getCurrentEnergy());

        assertFalse(energy.canAdjustEnergy(-1));
        assertThrows(
                IllegalArgumentException.class,
                () -> energy.adjustEnergy(-1)
        );
        assertEquals(0, energy.getCurrentEnergy());
    }
}
