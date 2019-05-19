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

public class HatchingGroundTests {

    @Test
    public void testReplaceCard() {
        ElementSpaceUtilities logic = EasyMock.mock(ElementSpaceUtilities.class);
        EasyMock.expect(logic.isComplete(EasyMock.anyObject(Card.class))).andReturn(false).anyTimes();

        Deck deck = EasyMock.mock(Deck.class);
        Card card1 = new Card();
        Card card2 = new Card();

        EasyMock.expect(deck.draw()).andReturn(card1);
        EasyMock.expect(deck.draw()).andReturn(card2);

        EasyMock.replay(deck, logic);
        HatchingGround hatchingGround = new HatchingGround(deck, logic);
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();
        hatchingGround.replaceCard(card1);
        assertEquals(card2, hatchingGround.cards[0][0]);
        EasyMock.verify(deck, logic);
    }

    @Test
    public void testReplaceCardBigger() {
        ElementSpaceUtilities logic = EasyMock.mock(ElementSpaceUtilities.class);
        EasyMock.expect(logic.isComplete(EasyMock.anyObject(Card.class))).andReturn(false).anyTimes();

        Deck deck = EasyMock.mock(Deck.class);
        Card card1 = new Card();
        Card card2 = new Card();

        EasyMock.expect(deck.draw()).andReturn(new Card()).times(3);
        EasyMock.expect(deck.draw()).andReturn(card1);
        EasyMock.expect(deck.draw()).andReturn(card2);

        EasyMock.replay(deck, logic);
        HatchingGround hatchingGround = new HatchingGround(deck, logic);
        hatchingGround.setDimensions(2, 2);
        hatchingGround.populate();
        hatchingGround.replaceCard(card1);
        assertEquals(card2, hatchingGround.cards[1][1]);
        EasyMock.verify(deck, logic);
    }

    public static class SetupTests {

        private Game game;
        private HatchingGround hatchingGround;
        private Stack<Card> cards;

        @Before
        public void init() {
            ElementSpaceUtilities logic = EasyMock.niceMock(ElementSpaceUtilities.class);
            EasyMock.replay(logic);

            this.cards = new Stack<Card>();
            for (int i = 0; i < 50; i++) {
                this.cards.push(new Card());
            }

            this.hatchingGround = new HatchingGround(new Deck(this.cards, null), logic);
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

    }
}
