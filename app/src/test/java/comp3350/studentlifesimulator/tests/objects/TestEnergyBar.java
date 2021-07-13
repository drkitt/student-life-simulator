package comp3350.studentlifesimulator.tests.objects;

import junit.framework.TestCase;

import comp3350.studentlifesimulator.objects.EnergyBar;
import comp3350.studentlifesimulator.objects.Student;

import static org.junit.Assert.*;

public class TestEnergyBar extends TestCase {
    public TestEnergyBar(String arg0) { super(arg0); }

    public void testInitialEnergy() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EnergyBar(EnergyBar.getMaxEnergy() + 1)
        );

        assertEquals(12, new EnergyBar(EnergyBar.getMaxEnergy()).getCurrentEnergy());
        assertEquals(2, new EnergyBar(2).getCurrentEnergy());
        assertEquals(0, new EnergyBar(0).getCurrentEnergy());

        assertThrows(
                IllegalArgumentException.class,
                () -> new EnergyBar(-1)
        );
    }

    public void testIncreaseEnergy() {
        EnergyBar energy = new EnergyBar(6);
        assertEquals(6, energy.getCurrentEnergy());

        assertTrue(energy.canAdjustEnergy(4));
        energy.adjustEnergy(4);
        assertEquals(10, energy.getCurrentEnergy());
    }

    public void testDecreaseEnergy() {
        EnergyBar energy = new EnergyBar(EnergyBar.getMaxEnergy());
        assertEquals(12, energy.getCurrentEnergy());

        assertTrue(energy.canAdjustEnergy(-10));
        energy.adjustEnergy(-10);
        assertEquals(2, energy.getCurrentEnergy());
    }

    public void testAdjustToMaxEnergy() {
        EnergyBar energy = new EnergyBar(2);

        assertTrue(energy.canAdjustEnergy(10));
        energy.adjustEnergy(10);
        assertEquals(12, energy.getCurrentEnergy());
    }

    public void testAdjustAboveMaxEnergy() {
        EnergyBar energy = new EnergyBar(EnergyBar.getMaxEnergy());

        assertTrue(energy.canAdjustEnergy(1));
        energy.adjustEnergy(1);
        assertEquals(12, energy.getCurrentEnergy());
    }

    public void testAdjustToZeroEnergy() {
        EnergyBar energy = new EnergyBar(2);

        assertTrue(energy.canAdjustEnergy(-2));
        energy.adjustEnergy(-2);
        assertEquals(0, energy.getCurrentEnergy());
    }

    public void testAdjustToNegativeEnergy() {
        EnergyBar energy = new EnergyBar(0);

        assertFalse(energy.canAdjustEnergy(-1));
        assertThrows(
                IllegalArgumentException.class,
                () -> energy.adjustEnergy(-1)
        );
        assertEquals(0, energy.getCurrentEnergy());
    }

    public void testConsecutiveAdjustments() {
        EnergyBar energy = new EnergyBar(6);

        assertTrue(energy.canAdjustEnergy(-2));
        energy.adjustEnergy(-2);
        assertEquals(4, energy.getCurrentEnergy());

        assertTrue(energy.canAdjustEnergy(-2));
        energy.adjustEnergy(-2);
        assertEquals(2, energy.getCurrentEnergy());

        assertTrue(energy.canAdjustEnergy(3));
        energy.adjustEnergy(3);
        assertEquals(5, energy.getCurrentEnergy());
    }

    public void testNormalSetEnergy() {
        EnergyBar energy = new EnergyBar(6);

        energy.setCurrentEnergy(2);
        assertEquals(2, energy.getCurrentEnergy());

        energy.setCurrentEnergy(6);
        assertEquals(6, energy.getCurrentEnergy());
    }

    public void testSetToMaxEnergy() {
        EnergyBar energy = new EnergyBar(2);

        energy.setCurrentEnergy(EnergyBar.getMaxEnergy());
        assertEquals(12, energy.getCurrentEnergy());
    }

    public void testSetAboveMaxEnergy() {
        EnergyBar energy = new EnergyBar(EnergyBar.getMaxEnergy());

        assertThrows(
                IllegalArgumentException.class,
                () -> energy.setCurrentEnergy(13)
        );
        assertEquals(12, energy.getCurrentEnergy());
    }

    public void testSetToZeroEnergy() {
        EnergyBar energy = new EnergyBar(6);

        energy.setCurrentEnergy(0);
        assertEquals(0, energy.getCurrentEnergy());
    }

    public void testSetToNegativeEnergy() {
        EnergyBar energy = new EnergyBar(2);

        assertThrows(
                IllegalArgumentException.class,
                () -> energy.setCurrentEnergy(-1)
        );
        assertEquals(2, energy.getCurrentEnergy());
    }
}
