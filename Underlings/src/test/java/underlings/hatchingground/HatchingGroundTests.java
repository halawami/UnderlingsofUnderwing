package underlings.hatchingground;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.game.Deck;
import underlings.game.HatchingGround;

public class HatchingGroundTests {

    @Test
    public void testReplaceCard() {
        Deck deck = EasyMock.mock(Deck.class);
        Card card1 = new Card();
        Card card2 = new Card();

        EasyMock.expect(deck.draw()).andReturn(card1);
        EasyMock.expect(deck.draw()).andReturn(card2);

        EasyMock.replay(deck);
        HatchingGround hatchingGround = new HatchingGround(deck, null);
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();
        hatchingGround.replaceCard(card1);
        assertEquals(card2, hatchingGround.cards[0][0]);
        EasyMock.verify(deck);
    }

    @Test
    public void testReplaceCardBigger() {
        Deck deck = EasyMock.mock(Deck.class);
        Card card1 = new Card();
        Card card2 = new Card();

        EasyMock.expect(deck.draw()).andReturn(new Card()).times(3);
        EasyMock.expect(deck.draw()).andReturn(card1);
        EasyMock.expect(deck.draw()).andReturn(card2);

        EasyMock.replay(deck);
        HatchingGround hatchingGround = new HatchingGround(deck, null);
        hatchingGround.setDimensions(2, 2);
        hatchingGround.populate();
        hatchingGround.replaceCard(card1);
        assertEquals(card2, hatchingGround.cards[1][1]);
        EasyMock.verify(deck);
    }
}
