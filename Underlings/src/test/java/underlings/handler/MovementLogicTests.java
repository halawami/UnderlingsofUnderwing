package underlings.handler;

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
import underlings.gui.DrawChoice;
import underlings.gui.Gui;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;

public class MovementLogicTests {

    private HatchingGround hatchingGround;
    private Gui gui;
    private Field field;
    private HandlerMovementLogic logic;
    private Player player;

    @Before
    public void init() {
        this.hatchingGround = EasyMock.mock(HatchingGround.class);
        this.gui = EasyMock.mock(Gui.class);
        this.field = new Field(new FieldSpaceFactory());
        this.logic = new HandlerMovementLogic(this.hatchingGround, this.gui, this.field);
        this.player = new Player(2, new HandlerFactory(), 0);
    }

    @Test
    public void testCardBreakRoom() {
        Card card = new Card();
        Handler handler = new Handler(HandlerState.CARD);

        card.handler = handler;

        EasyMock.expect(this.hatchingGround.findCard(handler)).andReturn(card);
        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.BREAK_ROOM, this.player);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertNull(card.handler);

    }

    @Test
    public void testFieldStay() {
        Handler handler = new Handler(HandlerState.READY_ROOM);

        EasyMock.expect(this.gui.getFieldSpace(this.player, this.field)).andReturn(this.field.field.get(0));
        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.FIELD, this.player);
        this.logic.move(handler, HandlerChoice.STAY, this.player);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.FIELD, handler.getState());
        assertTrue(handler.drawChoices.contains(DrawChoice.BLUE));
    }

    @Test
    public void testField() {
        Handler handler = new Handler(HandlerState.READY_ROOM);

        EasyMock.expect(this.gui.getFieldSpace(this.player, this.field)).andReturn(this.field.field.get(0));
        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.FIELD, this.player);

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
        this.logic.move(handler, HandlerChoice.CARD, this.player);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.CARD, handler.getState());
        assertEquals(handler, card.handler);
        assertEquals(card.name, handler.location);

    }

    @Test
    public void testFieldWhitespace() {
        Handler handler = new Handler(HandlerState.READY_ROOM);

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.FIELD_WHITESPACE, this.player);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.FIELD_WHITESPACE, handler.getState());
        assertTrue(handler.drawChoices.contains(DrawChoice.WHITE));
    }

    @Test
    public void testBreakRoom() {

        Handler handler = new Handler(HandlerState.FIELD);
        this.field.addHandler(0, handler);

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.BREAK_ROOM, this.player);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.BREAK_ROOM, handler.getState());
    }

    @Test
    public void testStay() {
        Handler handler = new Handler(HandlerState.READY_ROOM);

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.STAY, this.player);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.READY_ROOM, handler.getState());
    }

    @Test
    public void testReadyRoom() {
        Handler handler = new Handler(HandlerState.BREAK_ROOM);

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.READY_ROOM, this.player);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.READY_ROOM, handler.getState());
    }

    @Test
    public void testFieldToBreakRoom() {
        Handler handler = new Handler(HandlerState.FIELD);
        this.field.addHandler(0, handler);

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.BREAK_ROOM, this.player);

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

        this.logic.move(handler, HandlerChoice.BREAK_ROOM, this.player);

        EasyMock.verify(this.hatchingGround, this.gui);

        assertEquals(1, handler.drawChoices.size());
        assertTrue(handler.drawChoices.contains(DrawChoice.RANDOM));
        assertEquals(HandlerState.BREAK_ROOM, handler.getState());

    }

    @Test
    public void testWildHandler() {
        Handler handler = WildHandler.getInstance();

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(handler, HandlerChoice.BREAK_ROOM, this.player);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertEquals(HandlerState.CARD, WildHandler.getInstance().getState());
    }

    @Test
    public void testNullHandler() {
        Card card = new Card();

        EasyMock.replay(this.hatchingGround, this.gui);

        this.logic.move(card.handler, HandlerChoice.BREAK_ROOM, this.player);

        EasyMock.verify(this.hatchingGround, this.gui);
        assertNull(card.handler);
    }



}
