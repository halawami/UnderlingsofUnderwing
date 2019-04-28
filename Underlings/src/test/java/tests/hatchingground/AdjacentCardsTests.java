package tests.hatchingground;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    assertEquals(4, returnedCards.size());
    assertTrue(returnedCards.contains(mockedCards[0][1]));
    assertTrue(returnedCards.contains(mockedCards[1][0]));
    assertTrue(returnedCards.contains(mockedCards[1][2]));
    assertTrue(returnedCards.contains(mockedCards[2][1]));
  }

  @Test
  public void testLeftEdgeCard() {
    Deck stubDeck = EasyMock.niceMock(Deck.class);
    HatchingGround hatchingGround = new HatchingGround(stubDeck);
    Card[][] mockedCards = createMockedCards();
    hatchingGround.cards = mockedCards;
    List<Card> returnedCards = hatchingGround.getAdjacentCards(mockedCards[1][0]);
    assertEquals(3, returnedCards.size());
    assertTrue(returnedCards.contains(mockedCards[0][0]));
    assertTrue(returnedCards.contains(mockedCards[1][1]));
    assertTrue(returnedCards.contains(mockedCards[2][0]));
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
