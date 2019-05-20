package underlings.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.field.FieldSpace;
import underlings.gui.DrawChoice;
import underlings.utilities.LocaleUtilities;

public class StateStringTests {

    @Test
    public void testReadyRoom() {
        Handler handler = new Handler(HandlerState.READY_ROOM);
        assertEquals(LocaleUtilities.format("HANDLER_" + HandlerState.READY_ROOM), handler.toString());
    }

    @Test
    public void testBreakRoom() {
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        assertEquals(LocaleUtilities.format("HANDLER_" + HandlerState.BREAK_ROOM), handler.toString());
    }

    @Test
    public void testCard() {
        Handler handler = new Handler(HandlerState.CARD);
        handler.setLocation("test");
        assertEquals(LocaleUtilities.format("HANDLER_" + HandlerState.CARD, "test"), handler.toString());
    }

    @Test
    public void testIncubation() {
        Handler handler = new Handler(HandlerState.INCUBATION);
        assertEquals(LocaleUtilities.format("HANDLER_" + HandlerState.INCUBATION), handler.toString());
    }

    @Test
    public void testField() {
        assertEquals(LocaleUtilities.format("field_space_element_giver", LocaleUtilities.get("BLUE")),
                this.getStringField(DrawChoice.BLUE));
        assertEquals(LocaleUtilities.format("field_space_element_giver", LocaleUtilities.get("RED")),
                this.getStringField(DrawChoice.RED));
        assertEquals(LocaleUtilities.format("field_space_element_giver", LocaleUtilities.get("YELLOW")),
                this.getStringField(DrawChoice.YELLOW));
        assertEquals(LocaleUtilities.format("field_space_element_giver", LocaleUtilities.get("GREEN")),
                this.getStringField(DrawChoice.GREEN));
        assertEquals(LocaleUtilities.format("field_space_element_giver", LocaleUtilities.get("ORANGE")),
                this.getStringField(DrawChoice.ORANGE));
        assertEquals(LocaleUtilities.format("field_space_element_giver", LocaleUtilities.get("PURPLE")),
                this.getStringField(DrawChoice.PURPLE));
        assertEquals(LocaleUtilities.format("field_space_element_giver", LocaleUtilities.get("BLACK")),
                this.getStringField(DrawChoice.BLACK));
        assertEquals(LocaleUtilities.format("field_space_element_giver", LocaleUtilities.get("WHITE")),
                this.getStringField(DrawChoice.WHITE));
    }

    private String getStringField(DrawChoice drawChoice) {
        Handler handler = new Handler(HandlerState.FIELD);
        FieldSpace fieldSpace = new FieldSpace(drawChoice);
        fieldSpace.addHandler(handler);
        return handler.toString();
    }

}
