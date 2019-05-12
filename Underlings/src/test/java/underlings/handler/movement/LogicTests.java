package underlings.handler.movement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.google.common.base.Function;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.card.Card;
import underlings.field.Field;
import underlings.field.FieldSpaceFactory;
import underlings.game.HatchingGround;
import underlings.gui.DrawChoice;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;

public class LogicTests {

    private HatchingGround hatchingGround;
    private Gui gui;
    private Field field;
    private HandlerMovementLogic logic;

    @Before
    public void init() {
        this.hatchingGround = EasyMock.mock(HatchingGround.class);
        this.gui = EasyMock.mock(Gui.class);
        this.field = new Field(new FieldSpaceFactory());
        this.logic = new HandlerMovementLogic(this.hatchingGround, this.gui, this.field);
    }

    @Test
    public void testCardBreakRoom() {

        Card card = new Card();
        Handler handler = new Handler(HandlerState.CARD);

        card.handler = handler;

        EasyMock.expect(this.hatchingGround.findCard(handler)).andReturn(card);
        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.BREAK_ROOM, 0);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertNull(card.handler);

    }

    @Test
    public void testFieldStay() {
        Handler handler = new Handler(HandlerState.READY_ROOM);

        EasyMock.expect(this.gui.getFieldSpace()).andReturn(0);
        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.FIELD, 0);
        this.logic.move(handler, HandlerChoice.STAY, 0);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.FIELD, handler.getState());
        assertTrue(handler.drawChoices.contains(DrawChoice.BLUE));
    }

    @Test
    public void testField() {
        Handler handler = new Handler(HandlerState.READY_ROOM);

        EasyMock.expect(this.gui.getFieldSpace()).andReturn(0);
        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.FIELD, 0);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.FIELD, handler.getState());
        assertTrue(handler.drawChoices.contains(DrawChoice.RED));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCard() {
        Card card = new Card();

        EasyMock.expect(this.gui.getCard(EasyMock.anyInt(), EasyMock.anyString(),
                EasyMock.anyObject(HatchingGround.class), EasyMock.anyObject(Function.class))).andReturn(card);
        EasyMock.replay(this.hatchingGround, this.gui);

        Handler handler = new Handler(HandlerState.READY_ROOM);
        this.logic.move(handler, HandlerChoice.CARD, 0);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.CARD, handler.getState());
        assertEquals(handler, card.handler);

    }

    @Test
    public void testFieldWhitespace() {
        Handler handler = new Handler(HandlerState.READY_ROOM);

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.FIELD_WHITESPACE, 0);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.FIELD_WHITESPACE, handler.getState());
        assertTrue(handler.drawChoices.contains(DrawChoice.WHITE));
    }

    @Test
    public void testBreakRoom() {

        Handler handler = new Handler(HandlerState.FIELD);

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.BREAK_ROOM, 0);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.BREAK_ROOM, handler.getState());
    }

    @Test
    public void testStay() {
        Handler handler = new Handler(HandlerState.READY_ROOM);

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.STAY, 0);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.READY_ROOM, handler.getState());
    }

    @Test
    public void testReadyRoom() {
        Handler handler = new Handler(HandlerState.BREAK_ROOM);

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.READY_ROOM, 0);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.READY_ROOM, handler.getState());
    }

}
