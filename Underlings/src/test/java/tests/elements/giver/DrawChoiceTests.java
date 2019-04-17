package tests.elements.giver;

import org.junit.Test;
import underlings.gui.DrawChoice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DrawChoiceTests {

    @Test
    public void testToStringBlue(){
        DrawChoice drawChoice = DrawChoice.BLUE;
        assertEquals("BLUE Element", drawChoice.toString());
    }
}
