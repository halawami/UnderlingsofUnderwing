package underlings.field;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import underlings.gui.DrawChoice;

public class AddTests extends FieldTests {

    @Test
    public void testStart() {
        this.field.addHandler(0, this.handler);
        assertTrue(this.handler.drawChoices.contains(DrawChoice.RED));
    }

    @Test
    public void testEnd() {
        this.field.addHandler(21, this.handler);
        assertTrue(this.handler.drawChoices.contains(DrawChoice.GREEN));
    }

    @Test
    public void testWhite() {
        this.field.addHandlerWhitespace(this.handler);
        assertTrue(this.handler.drawChoices.contains(DrawChoice.WHITE));
    }

}
