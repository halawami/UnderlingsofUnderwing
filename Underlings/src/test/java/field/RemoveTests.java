package field;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import handler.Handler;
import handler.HandlerState;

public class RemoveTests {

    @Test
    public void testStart() {

        Field field = new Field(new FieldSpaceFactory());
        Handler handler = new Handler(HandlerState.READY_ROOM);
        field.addHandler(0, handler);

        field.removeHandler(handler);
        assertNull(field.findHandler(handler));
    }

}
