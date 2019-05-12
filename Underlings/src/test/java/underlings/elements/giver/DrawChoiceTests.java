package underlings.elements.giver;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import underlings.gui.DrawChoice;

public class DrawChoiceTests {

    @Test
    public void testToString() {
        assertEquals("Blue Element", this.getString(DrawChoice.BLUE));
        assertEquals("Red Element", this.getString(DrawChoice.RED));
        assertEquals("Green Element", this.getString(DrawChoice.GREEN));
        assertEquals("Purple Element", this.getString(DrawChoice.PURPLE));
        assertEquals("Orange Element", this.getString(DrawChoice.ORANGE));
        assertEquals("Yellow Element", this.getString(DrawChoice.YELLOW));
        assertEquals("Black Element", this.getString(DrawChoice.BLACK));
        assertEquals("White Element", this.getString(DrawChoice.WHITE));
        assertEquals("Random Element", this.getString(DrawChoice.RANDOM));
        assertEquals("Cool Element", this.getString(DrawChoice.COOL));
        assertEquals("Warm Element", this.getString(DrawChoice.WARM));
    }

    private String getString(DrawChoice drawChoice) {
        return drawChoice.toString();
    }

}
