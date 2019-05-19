package underlings.field;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.gui.DrawChoice;
import underlings.utilities.LocaleWrap;

public class FieldSpaceTests {

    @Test
    public void testToString() {
        FieldSpace redSpace = new FieldSpace(DrawChoice.RED);
        assertEquals(LocaleWrap.format("field_space_string", DrawChoice.RED), redSpace.toString());

        FieldSpace blueSpace = new FieldSpace(DrawChoice.BLUE);
        assertEquals(LocaleWrap.format("field_space_string", DrawChoice.BLUE), blueSpace.toString());
    }
}
