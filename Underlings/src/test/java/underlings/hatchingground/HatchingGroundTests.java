package underlings.hatchingground;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.Card;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.hatchingground.HatchingGround;

public class HatchingGroundTests {

    @Test
    public void testReplaceCard() {
        ElementSpaceLogic logic = EasyMock.mock(ElementSpaceLogic.class);
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
        ElementSpaceLogic logic = EasyMock.mock(ElementSpaceLogic.class);
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
}
