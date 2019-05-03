package elements.giver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gui.DrawChoice;

public class DrawChoiceTests {

    @Test
    public void testToStringBlue() {
        DrawChoice drawChoice = DrawChoice.BLUE;
        assertEquals("BLUE Element", drawChoice.toString());
    }

    @Test
    public void testToStringRed() {
        DrawChoice drawChoice = DrawChoice.RED;
        assertEquals("RED Element", drawChoice.toString());
    }
}
