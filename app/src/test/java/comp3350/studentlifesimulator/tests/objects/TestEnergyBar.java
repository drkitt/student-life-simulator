package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;

import static org.junit.Assert.*;

public class TestEnergyBar extends TestCase {
    public TestEnergyBar(String arg0) { super(arg0); }

    public void testMaxEnergy() {
        EnergyBar maxedOut = new EnergyBar(Student.getMaxEnergy());
        assertEquals(Student.getMaxEnergy(), maxedOut.getCurrentEnergy());
    }

    public void testInitialEnergy() {
        EnergyBar maxedOut = new EnergyBar(EnergyBar.getMaxEnergy());
        assertEquals(EnergyBar.getMaxEnergy(), maxedOut.getCurrentEnergy());
        EnergyBar partiallyDepleted = new EnergyBar(2);
        assertEquals(2, partiallyDepleted.getCurrentEnergy());
        EnergyBar completelyDepleted = new EnergyBar(0);
        assertEquals(0, completelyDepleted.getCurrentEnergy());


        assertThrows(
                IllegalArgumentException.class,
                () -> new EnergyBar(-1)
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new EnergyBar(EnergyBar.getMaxEnergy() + 1)
        );
    }

    public void testAdjustingEnergy() {
        EnergyBar energy = new EnergyBar(EnergyBar.getMaxEnergy());

        assertEquals(EnergyBar.getMaxEnergy(), energy.getCurrentEnergy());

        energy.setCurrentEnergy(EnergyBar.getMaxEnergy() - 1);
        assertEquals(EnergyBar.getMaxEnergy() - 1, energy.getCurrentEnergy());

        assertTrue(energy.canAdjustEnergy(1));
        energy.adjustEnergy(1);
        assertEquals(EnergyBar.getMaxEnergy(), energy.getCurrentEnergy());

        assertFalse(energy.canAdjustEnergy(1));
        assertThrows(
                IllegalArgumentException.class,
                () -> energy.adjustEnergy(1)
        );
        assertEquals(EnergyBar.getMaxEnergy(), energy.getCurrentEnergy());

        assertTrue(energy.canAdjustEnergy(-(EnergyBar.getMaxEnergy() - 2)));
        energy.adjustEnergy(-(EnergyBar.getMaxEnergy() - 2));
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
