package underlings.handler.movement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

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
import underlings.handler.WildHandler;

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

        EasyMock.expect(this.gui.getFieldSpace(0, this.field)).andReturn(this.field.field.get(0));
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

        EasyMock.expect(this.gui.getFieldSpace(0, this.field)).andReturn(this.field.field.get(0));
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
        card.name = "testCard";

        EasyMock.expect(this.gui.getCard(EasyMock.anyInt(), EasyMock.anyString(),
                EasyMock.anyObject(HatchingGround.class), EasyMock.anyObject(List.class))).andReturn(card);
        List<Card> cardList = Arrays.asList(card);
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(cardList);
        EasyMock.replay(this.hatchingGround, this.gui);

        Handler handler = new Handler(HandlerState.READY_ROOM);
        this.logic.move(handler, HandlerChoice.CARD, 0);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.CARD, handler.getState());
        assertEquals(handler, card.handler);
        assertEquals(card.name, handler.location);

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
        this.field.addHandler(0, handler);

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

    @Test
    public void testFieldToBreakRoom() {
        Handler handler = new Handler(HandlerState.FIELD);
        this.field.addHandler(0, handler);

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.BREAK_ROOM, 0);

        EasyMock.verify(this.hatchingGround, this.gui);

        assertEquals(1, handler.drawChoices.size());
        assertTrue(handler.drawChoices.contains(DrawChoice.RANDOM));
        assertEquals(HandlerState.BREAK_ROOM, handler.getState());

    }

    @Test
    public void testFieldWhiteToBreakRoom() {
        Handler handler = new Handler(HandlerState.FIELD_WHITESPACE);
        this.field.addHandlerWhitespace(handler);

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.BREAK_ROOM, 0);

        EasyMock.verify(this.hatchingGround, this.gui);

        assertEquals(1, handler.drawChoices.size());
        assertTrue(handler.drawChoices.contains(DrawChoice.RANDOM));
        assertEquals(HandlerState.BREAK_ROOM, handler.getState());

    }

    @Test
    public void testWildHandler() {
        Handler handler = WildHandler.getInstance();

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.BREAK_ROOM, 0);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.CARD, WildHandler.getInstance().getState());
    }

    @Test
    public void testNoHandler() {
        Card card = new Card();

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(card.handler, HandlerChoice.BREAK_ROOM, 0);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertNull(card.handler);
    }

}
