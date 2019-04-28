package tests.field;

import static org.junit.Assert.assertNull;
import org.junit.Test;
import underlings.field.Field;
import underlings.field.FieldSpaceFactory;
import underlings.handler.Handler;
import underlings.handler.HandlerState;

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
