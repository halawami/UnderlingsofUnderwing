package underlings.hatchingground;

import static org.junit.Assert.assertEquals;

import java.awt.Dimension;

import org.junit.Test;

import underlings.game.HatchingGround;

public class DimensionTests {

    @Test
    public void testDimensionsEmpty() {
        HatchingGround hatchingGround = new HatchingGround(null);
        hatchingGround.setDimensions(0, 0);

        Dimension expected = new Dimension(0, 0);
        assertEquals(expected, hatchingGround.getDimensions());
    }

    @Test
    public void testDimensionsNonempty() {
        HatchingGround hatchingGround = new HatchingGround(null);
        hatchingGround.setDimensions(2, 2);

        Dimension expected = new Dimension(2, 2);
        assertEquals(expected, hatchingGround.getDimensions());
    }

}
