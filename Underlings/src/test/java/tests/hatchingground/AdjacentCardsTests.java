package tests.hatchingground;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
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


  @Test
  public void testGetCardCoordinatesFirstCard() {
    Deck stubDeck = EasyMock.niceMock(Deck.class);
    HatchingGround hatchingGround = new HatchingGround(stubDeck);
    hatchingGround.setDimensions(4, 4);
    Card[][] mockedCards = createMockedCards();
    hatchingGround.cards = mockedCards;

    Point cardCoordinates = hatchingGround.getCardCoordinates(mockedCards[0][0]);
    assertEquals(0, cardCoordinates.x);
    assertEquals(0, cardCoordinates.y);
  }

  @Test
  public void testGetCardCoordinatesLeftEdge() {
    Deck stubDeck = EasyMock.niceMock(Deck.class);
    HatchingGround hatchingGround = new HatchingGround(stubDeck);
    hatchingGround.setDimensions(4, 4);
    Card[][] mockedCards = createMockedCards();
    hatchingGround.cards = mockedCards;

    Point cardCoordinates = hatchingGround.getCardCoordinates(mockedCards[1][0]);
    assertEquals(1, cardCoordinates.y);
    assertEquals(0, cardCoordinates.x);
  }

  @Test
  public void testGetCardCoordinatesTopEdge() {
    Deck stubDeck = EasyMock.niceMock(Deck.class);
    HatchingGround hatchingGround = new HatchingGround(stubDeck);
    hatchingGround.setDimensions(4, 4);
    Card[][] mockedCards = createMockedCards();
    hatchingGround.cards = mockedCards;

    Point cardCoordinates = hatchingGround.getCardCoordinates(mockedCards[0][1]);
    assertEquals(0, cardCoordinates.y);
    assertEquals(1, cardCoordinates.x);
  }

  @Test
  public void testGetCardCoordinatesRightEdge() {
    Deck stubDeck = EasyMock.niceMock(Deck.class);
    HatchingGround hatchingGround = new HatchingGround(stubDeck);
    hatchingGround.setDimensions(4, 4);
    Card[][] mockedCards = createMockedCards();
    hatchingGround.cards = mockedCards;

    Point cardCoordinates = hatchingGround.getCardCoordinates(mockedCards[1][3]);
    assertEquals(1, cardCoordinates.y);
    assertEquals(3, cardCoordinates.x);
  }

  @Test
  public void testGetCardCoordinatesBottomEdge() {
    Deck stubDeck = EasyMock.niceMock(Deck.class);
    HatchingGround hatchingGround = new HatchingGround(stubDeck);
    hatchingGround.setDimensions(4, 4);
    Card[][] mockedCards = createMockedCards();
    hatchingGround.cards = mockedCards;

    Point cardCoordinates = hatchingGround.getCardCoordinates(mockedCards[3][1]);
    assertEquals(3, cardCoordinates.y);
    assertEquals(1, cardCoordinates.x);
  }

  @Test
  public void testGetCardCoordinatesMiddle() {
    Deck stubDeck = EasyMock.niceMock(Deck.class);
    HatchingGround hatchingGround = new HatchingGround(stubDeck);
    hatchingGround.setDimensions(4, 4);
    Card[][] mockedCards = createMockedCards();
    hatchingGround.cards = mockedCards;

    Point cardCoordinates = hatchingGround.getCardCoordinates(mockedCards[1][1]);
    assertEquals(1, cardCoordinates.y);
    assertEquals(1, cardCoordinates.x);
  }

  @Test
  public void testGetCardCoordinatesInvalidCard() {
    Deck stubDeck = EasyMock.niceMock(Deck.class);
    HatchingGround hatchingGround = new HatchingGround(stubDeck);
    hatchingGround.setDimensions(4, 4);
    Card[][] mockedCards = createMockedCards();
    hatchingGround.cards = mockedCards;

    Point cardCoordinates = hatchingGround.getCardCoordinates(new Card());
    assertEquals(-2, cardCoordinates.y);
    assertEquals(-2, cardCoordinates.x);
  }


  private Card[][] createMockedCards() {
    Card[][] mockedCards = new Card[4][4];
    for (int i = 0; i < mockedCards.length; i++) {
      for (int j = 0; j < mockedCards[i].length; j++) {
        mockedCards[i][j] = EasyMock.mock(Card.class);
      }
    }
    return mockedCards;
  }
}
