package underlings.field;

import org.junit.Before;

import underlings.handler.Handler;
import underlings.handler.HandlerState;

public class FieldTests {

    protected Field field;
    protected Handler handler;

    @Before
    public void init() {
        this.field = new Field(new FieldSpaceFactory());
        this.handler = new Handler(HandlerState.READY_ROOM);
    }

}
