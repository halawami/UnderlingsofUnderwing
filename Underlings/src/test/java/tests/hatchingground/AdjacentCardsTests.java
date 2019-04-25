package tests.hatchingground;

import static org.junit.Assert.*;

import java.util.List;

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
        List<Card> returnedCards = hatchingGround.getAdjacentCards(mockedCards[1][1]);
        int ctr = 0;
        for(int i = 0; i < 3; i++){
        	for(int j = 0; j < 3; j++){
        		if(i != 1 || j != 1){
        			assertTrue(returnedCards.contains(mockedCards[i][j]));
        			ctr++;
        			System.out.println(i + " " + j);
        		}
        	}
        }
        System.out.println(ctr);
        assertEquals(8, returnedCards.size());
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
