package underlings.game;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.Card;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.handler.Handler;
import underlings.handler.HandlerState;
import underlings.hatchingground.Deck;
import underlings.hatchingground.HatchingGround;

public class FindCardTests {

    @Test
    public void testFindCardEmpty() {
        HatchingGround ground = new HatchingGround(null, null);
        ground.setDimensions(0, 0);
        ground.populate();
        Card foundCard = ground.findCard(new Handler(HandlerState.CARD));

        assertEquals(null, foundCard);
    }

    @Test
    public void testFindCardSuccessful() {
        Card card1 = new Card();
        card1.name = "card1";
        Card card2 = new Card();
        card2.handler = new Handler(HandlerState.CARD);
        card2.name = "card2";
        Deck mockedDeck = EasyMock.strictMock(Deck.class);

        EasyMock.expect(mockedDeck.draw()).andReturn(card2).times(2);
        EasyMock.expect(mockedDeck.draw()).andReturn(card1);
        EasyMock.expect(mockedDeck.draw()).andReturn(card2);

        ElementSpaceLogic logic = EasyMock.niceMock(ElementSpaceLogic.class);

        EasyMock.replay(mockedDeck, logic);

        HatchingGround ground = new HatchingGround(mockedDeck, logic);
        ground.setDimensions(2, 2);
        ground.populate();
        Card foundCard = ground.findCard(card1.handler);

        EasyMock.verify(mockedDeck, logic);

        assertEquals(card1, foundCard);
    }

    @Test
    public void testFindCardUnsuccessful() {
        Card card1 = new Card();
        card1.name = "card1";
        Deck mockedDeck = EasyMock.strictMock(Deck.class);

        EasyMock.expect(mockedDeck.draw()).andReturn(card1).times(4);

        ElementSpaceLogic logic = EasyMock.niceMock(ElementSpaceLogic.class);

        EasyMock.replay(mockedDeck, logic);

        HatchingGround ground = new HatchingGround(mockedDeck, logic);
        ground.setDimensions(2, 2);
        ground.populate();
        Card foundCard = ground.findCard(new Handler(HandlerState.CARD));

        EasyMock.verify(mockedDeck, logic);

        assertEquals(null, foundCard);
    }
}
