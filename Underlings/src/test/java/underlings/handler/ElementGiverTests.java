package underlings.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import underlings.field.FieldSpace;
import underlings.gui.DrawChoice;

public class ElementGiverTests {

    @Test
    public void testNotField() {
        Handler handler = new Handler(HandlerState.BREAK_ROOM);

        List<DrawChoice> drawChoices = handler.drawChoices;
        assertEquals(1, drawChoices.size());
        assertTrue(drawChoices.contains(DrawChoice.RANDOM));
    }

    @Test
    public void testFieldAll() {
        this.testFieldColor(DrawChoice.BLUE);
        this.testFieldColor(DrawChoice.RED);
        this.testFieldColor(DrawChoice.GREEN);
        this.testFieldColor(DrawChoice.PURPLE);
        this.testFieldColor(DrawChoice.ORANGE);
        this.testFieldColor(DrawChoice.YELLOW);
        this.testFieldColor(DrawChoice.BLACK);
        this.testFieldColor(DrawChoice.WHITE);
    }

    private void testFieldColor(DrawChoice color) {
        FieldSpace fieldSpace = new FieldSpace(color);
        Handler handler = new Handler(HandlerState.FIELD);
        fieldSpace.addHandler(handler);

        List<DrawChoice> drawChoices = handler.drawChoices;
        assertEquals(2, drawChoices.size());
        assertTrue(drawChoices.contains(DrawChoice.RANDOM));
        assertTrue(drawChoices.contains(color));
    }


}
