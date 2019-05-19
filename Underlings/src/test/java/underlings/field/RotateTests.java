package underlings.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import underlings.gui.DrawChoice;

public class RotateTests extends FieldTests {

    @Test
    public void testStart() {
        this.field.addHandler(0, this.handler);
        this.field.rotate(this.handler);

        this.testHandlerNotOnMultipleSpots();
        assertTrue(this.handler.drawChoices.contains(DrawChoice.BLUE));
    }

    @Test
    public void testEnd() {
        this.field.addHandler(21, this.handler);
        this.field.rotate(this.handler);

        this.testHandlerNotOnMultipleSpots();
        assertTrue(this.handler.drawChoices.contains(DrawChoice.RED));
    }

    private void testHandlerNotOnMultipleSpots() {
        int amt = 0;
        for (FieldSpace space : this.field.field) {
            if (space.contains(this.handler)) {
                amt++;
            }
        }

        assertEquals(1, amt);
    }

}
