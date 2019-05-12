package underlings.elements.giver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import underlings.element.ElementGiver;
import underlings.field.FieldSpace;
import underlings.gui.DrawChoice;
import underlings.handler.Handler;
import underlings.handler.HandlerState;

public class HandlerTests {

    @Test
    public void testNotField() {
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        ElementGiver elementGiver = handler.elementGiver;
        List<DrawChoice> drawChoices = elementGiver.drawChoices;
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
        ElementGiver elementGiver = handler.elementGiver;
        List<DrawChoice> drawChoices = elementGiver.drawChoices;
        assertEquals(2, drawChoices.size());
        assertTrue(drawChoices.contains(DrawChoice.RANDOM));
        assertTrue(drawChoices.contains(color));
    }

    @Test
    public void testToStringReadyRoom() {
        Handler handler = new Handler(HandlerState.READY_ROOM);
        ElementGiver elementGiver = handler.elementGiver;
        assertEquals("Handler in Ready Room", elementGiver.toString());
    }

    @Test
    public void testToStringBreakRoom() {
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        ElementGiver elementGiver = handler.elementGiver;
        assertEquals("Handler in Break Room", elementGiver.toString());
    }

    @Test
    public void testToStringCard() {
        Handler handler = new Handler(HandlerState.CARD);
        handler.setLocation("testCard");
        assertEquals("Handler on testCard", handler.toString());
    }

    @Test
    public void testToStringIncubation() {
        Handler handler = new Handler(HandlerState.INCUBATION);
        ElementGiver elementGiver = handler.elementGiver;
        assertEquals("Handler in Incubation", elementGiver.toString());
    }

    @Test
    public void testToStringField() {
        assertEquals("Handler on Red Field Space", this.getElementGiverString(DrawChoice.RED));
        assertEquals("Handler on Blue Field Space", this.getElementGiverString(DrawChoice.BLUE));
        assertEquals("Handler on Yellow Field Space", this.getElementGiverString(DrawChoice.YELLOW));
        assertEquals("Handler on Green Field Space", this.getElementGiverString(DrawChoice.GREEN));
        assertEquals("Handler on Orange Field Space", this.getElementGiverString(DrawChoice.ORANGE));
        assertEquals("Handler on Purple Field Space", this.getElementGiverString(DrawChoice.PURPLE));
        assertEquals("Handler on Black Field Space", this.getElementGiverString(DrawChoice.BLACK));
        assertEquals("Handler on White Field Space", this.getElementGiverString(DrawChoice.WHITE));
    }

    private String getElementGiverString(DrawChoice drawChoice) {
        Handler handler = new Handler(HandlerState.FIELD);
        FieldSpace fieldSpace = new FieldSpace(drawChoice);
        fieldSpace.addHandler(handler);
        ElementGiver elementGiver = handler.elementGiver;
        return elementGiver.toString();
    }

}
