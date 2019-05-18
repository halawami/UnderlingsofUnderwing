package underlings.field;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.gui.DrawChoice;

public class FieldSpaceTests {

    @Test
    public void testToString() {
        FieldSpace redSpace = new FieldSpace(DrawChoice.RED);
        assertEquals("Red Element Field Space", redSpace.toString());

        FieldSpace blueSpace = new FieldSpace(DrawChoice.BLUE);
        assertEquals("Blue Element Field Space", blueSpace.toString());
    }
}
