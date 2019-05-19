package underlings.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.field.FieldSpace;
import underlings.gui.DrawChoice;
import underlings.utilities.LocaleWrap;

public class HandlerStateStringTests {

    @Test
    public void testToStringReadyRoom() {
        Handler handler = new Handler(HandlerState.READY_ROOM);
        assertEquals(LocaleWrap.format("HANDLER_" + HandlerState.READY_ROOM), handler.toString());
    }

    @Test
    public void testToStringBreakRoom() {
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        assertEquals(LocaleWrap.format("HANDLER_" + HandlerState.BREAK_ROOM), handler.toString());
    }

    @Test
    public void testToStringCard() {
        Handler handler = new Handler(HandlerState.CARD);
        handler.setLocation("test");
        assertEquals(LocaleWrap.format("HANDLER_" + HandlerState.CARD, "test"), handler.toString());
    }

    @Test
    public void testToStringIncubation() {
        Handler handler = new Handler(HandlerState.INCUBATION);
        assertEquals(LocaleWrap.format("HANDLER_" + HandlerState.INCUBATION), handler.toString());
    }

    @Test
    public void testToStringField() {
        assertEquals(LocaleWrap.format("field_space_element_giver", LocaleWrap.get("BLUE")),
                this.getStringField(DrawChoice.BLUE));
        assertEquals(LocaleWrap.format("field_space_element_giver", LocaleWrap.get("RED")),
                this.getStringField(DrawChoice.RED));
        assertEquals(LocaleWrap.format("field_space_element_giver", LocaleWrap.get("YELLOW")),
                this.getStringField(DrawChoice.YELLOW));
        assertEquals(LocaleWrap.format("field_space_element_giver", LocaleWrap.get("GREEN")),
                this.getStringField(DrawChoice.GREEN));
        assertEquals(LocaleWrap.format("field_space_element_giver", LocaleWrap.get("ORANGE")),
                this.getStringField(DrawChoice.ORANGE));
        assertEquals(LocaleWrap.format("field_space_element_giver", LocaleWrap.get("PURPLE")),
                this.getStringField(DrawChoice.PURPLE));
        assertEquals(LocaleWrap.format("field_space_element_giver", LocaleWrap.get("BLACK")),
                this.getStringField(DrawChoice.BLACK));
        assertEquals(LocaleWrap.format("field_space_element_giver", LocaleWrap.get("WHITE")),
                this.getStringField(DrawChoice.WHITE));
    }

    private String getStringField(DrawChoice drawChoice) {
        Handler handler = new Handler(HandlerState.FIELD);
        FieldSpace fieldSpace = new FieldSpace(drawChoice);
        fieldSpace.addHandler(handler);
        return handler.toString();
    }

}
