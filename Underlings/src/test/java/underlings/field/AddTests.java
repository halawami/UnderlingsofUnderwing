package underlings.field;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import underlings.gui.DrawChoice;
import underlings.handler.Handler;
import underlings.handler.HandlerState;

public class AddTests {

    @Test
    public void testStart() {
        Field field = new Field(new FieldSpaceFactory());
        Handler handler = new Handler(HandlerState.READY_ROOM);

        field.addHandler(0, handler);
        assertTrue(handler.elementGiver.drawChoices.contains(DrawChoice.RED));
    }

    @Test
    public void testEnd() {
        Field field = new Field(new FieldSpaceFactory());
        Handler handler = new Handler(HandlerState.READY_ROOM);

        field.addHandler(21, handler);
        assertTrue(handler.elementGiver.drawChoices.contains(DrawChoice.GREEN));
    }

    @Test
    public void testWhite() {
        Field field = new Field(new FieldSpaceFactory());
        Handler handler = new Handler(HandlerState.READY_ROOM);

        field.addHandlerWhitespace(handler);
        assertTrue(handler.elementGiver.drawChoices.contains(DrawChoice.WHITE));
    }

}
