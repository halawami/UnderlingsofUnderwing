package underlings.hatchingground;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Stack;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.ElementSpaceUtilities;
import underlings.game.Game;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.player.PlayerFactory;

public class SetupTests {

    private Game game;
    private Stack<Card> cards;
    private HatchingGround hatchingGround;
    private ElementSpaceUtilities logic;

    @Before
    public void init() {
        this.cards = new Stack<Card>();
        for (int i = 0; i < 50; i++) {
            this.cards.push(new Card());
        }

        this.logic = EasyMock.niceMock(ElementSpaceUtilities.class);
        EasyMock.replay(this.logic);

        this.hatchingGround = new HatchingGround(new Deck(this.cards, null), this.logic);
        this.game = new Game(EasyMock.mock(Gui.class), this.hatchingGround,
                new PlayerFactory(new HandlerFactory(), Arrays.asList()), EasyMock.mock(ElementBag.class));
    }

    @Test
    public void test2PlayerSize() {
        this.game.setUp(2);
        assertEquals(3, this.game.hatchingGround.getWidth());
        assertEquals(2, this.game.hatchingGround.getHeight());
    }

    @Test
    public void test3PlayerSize() {
        this.game.setUp(3);
        assertEquals(4, this.game.hatchingGround.getWidth());
        assertEquals(3, this.game.hatchingGround.getHeight());
    }

    @Test
    public void test4PlayerSize() {
        this.game.setUp(4);
        assertEquals(4, this.game.hatchingGround.getWidth());
        assertEquals(4, this.game.hatchingGround.getHeight());
    }

    @Test
    public void test6PlayerSize() {
        this.game.setUp(6);
        assertEquals(4, this.game.hatchingGround.getWidth());
        assertEquals(4, this.game.hatchingGround.getHeight());
    }

    @Test
    public void testDeal3By2() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(6);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, this.logic);
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testDeal4By3() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(12);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, this.logic);
        hatchingGround.setDimensions(4, 3);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testDeal4By4() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(16);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, this.logic);
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }


}
