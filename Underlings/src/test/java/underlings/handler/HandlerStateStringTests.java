package underlings.handler;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import underlings.field.FieldSpace;
import underlings.gui.DrawChoice;

public class HandlerStateStringTests {

    @Test
    public void testToStringReadyRoom() {
        Handler handler = new Handler(HandlerState.READY_ROOM);
        assertEquals("Handler in Ready Room", handler.toString());
    }

    @Test
    public void testToStringBreakRoom() {
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        assertEquals("Handler in Break Room", handler.toString());
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
        assertEquals("Handler in Incubation", handler.toString());
    }

    @Test
    public void testToStringField() {
        assertEquals("Handler on Blue Field Space", this.getStringField(DrawChoice.BLUE));
        assertEquals("Handler on Red Field Space", this.getStringField(DrawChoice.RED));
        assertEquals("Handler on Yellow Field Space", this.getStringField(DrawChoice.YELLOW));
        assertEquals("Handler on Green Field Space", this.getStringField(DrawChoice.GREEN));
        assertEquals("Handler on Orange Field Space", this.getStringField(DrawChoice.ORANGE));
        assertEquals("Handler on Purple Field Space", this.getStringField(DrawChoice.PURPLE));
        assertEquals("Handler on Black Field Space", this.getStringField(DrawChoice.BLACK));
        assertEquals("Handler on White Field Space", this.getStringField(DrawChoice.WHITE));
    }

    private String getStringField(DrawChoice drawChoice) {
        Handler handler = new Handler(HandlerState.FIELD);
        FieldSpace fieldSpace = new FieldSpace(drawChoice);
        fieldSpace.addHandler(handler);
        return handler.toString();
    }

}
