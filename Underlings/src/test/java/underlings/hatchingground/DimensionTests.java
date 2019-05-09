package underlings.hatchingground;

import java.awt.Dimension;

import org.junit.Test;

import underlings.game.HatchingGround;

public class DimensionTests {

    @Test
    public void testDimensionsEmpty() {

        Dimension expected = new Dimension(0, 0);

        HatchingGround hatchingGround = new HatchingGround(null);
        hatchingGround.setDimensions(0, 0);

        assertEquals(expected, hatchingGround.getDimensions());

    }

}
