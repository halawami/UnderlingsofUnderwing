package underlings.gui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.utilities.LocaleUtilities;

public class DrawChoiceTests {

    @Test
    public void testToString() {
        checkDrawChoiceString(DrawChoice.BLUE);
        checkDrawChoiceString(DrawChoice.RED);
        checkDrawChoiceString(DrawChoice.GREEN);
        checkDrawChoiceString(DrawChoice.PURPLE);
        checkDrawChoiceString(DrawChoice.ORANGE);
        checkDrawChoiceString(DrawChoice.YELLOW);
        checkDrawChoiceString(DrawChoice.BLACK);
        checkDrawChoiceString(DrawChoice.WHITE);
        checkDrawChoiceString(DrawChoice.RANDOM);
        checkDrawChoiceString(DrawChoice.COOL);
        checkDrawChoiceString(DrawChoice.WARM);
    }

    private void checkDrawChoiceString(DrawChoice drawChoice) {
        String expected = LocaleUtilities.format("draw_choice", LocaleUtilities.get(drawChoice.name()));
        assertEquals(expected, drawChoice.toString());
    }

}
