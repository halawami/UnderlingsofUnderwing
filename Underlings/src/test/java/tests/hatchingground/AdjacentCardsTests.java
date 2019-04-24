package tests.hatchingground;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.Card;
import underlings.game.Deck;
import underlings.game.HatchingGround;

public class AdjacentCardsTests {

    @Test
    public void testMiddleCard() {
        Deck stubDeck = EasyMock.niceMock(Deck.class);
        HatchingGround hatchingGround = new HatchingGround(stubDeck);
        Card[][] mockedCards = createMockedCards();
        hatchingGround.cards = mockedCards;
        hatchingGround.getAdjacentCards(mockedCards[1][1]);



    }

    private Card[][] createMockedCards() {
        Card[][] mockedCards = new Card[4][4];
        for (int i = 0; i < mockedCards.length; i++) {
            for (int j = 0; j < mockedCards[i].length; j++) {
                EasyMock.mock(Card.class);
            }
        }
        return mockedCards;
    }
}
