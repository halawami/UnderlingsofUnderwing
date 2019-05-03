package field;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gui.DrawChoice;
import handler.Handler;
import handler.HandlerState;

public class RotateTests {

    @Test
    public void testStart() {
        Field field = new Field(new FieldSpaceFactory());
        Handler handler = new Handler(HandlerState.READY_ROOM);

        field.addHandler(0, handler);
        field.rotate(handler);

        assertTrue(handler.elementGiver.drawChoices.contains(DrawChoice.BLUE));
    }

    @Test
    public void testEnd() {
        Field field = new Field(new FieldSpaceFactory());
        Handler handler = new Handler(HandlerState.READY_ROOM);

        field.addHandler(21, handler);
        field.rotate(handler);

        assertTrue(handler.elementGiver.drawChoices.contains(DrawChoice.RED));
    }

}
