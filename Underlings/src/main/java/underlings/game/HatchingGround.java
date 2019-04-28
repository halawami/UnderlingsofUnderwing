package underlings.game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import underlings.card.Card;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.gui.GUI;
import underlings.handler.Handler;
import underlings.handler.WildHandler;

public class HatchingGround implements Iterable<Card> {

  private int height, width;
  public Card[][] cards;
  private Deck deck;

  public HatchingGround(Deck deck) {
    this.deck = deck;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public void setDimensions(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void populate() {
    this.cards = new Card[this.height][this.width];
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        this.cards[row][col] = this.deck.draw();
      }
    }
  }

  public void display(GUI gui) {
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        gui.display.displayCard(row, col, this.cards[row][col]);
      }
    }
  }

  public List<Card> getUnclaimedEggs() {
    List<Card> unclaimedEggs = new ArrayList<>();
    for (Card card : this) {
      if (card.handler == null) {
        unclaimedEggs.add(card);
      }
    }

    return unclaimedEggs;
  }

  public List<Card> getAdjacentCards(Card centerCard) {
    Point cardCoordinates = this.getCardCoordinates(centerCard);
    List<Card> cardsToReturn = new LinkedList<>();

    for (int yDiff = -1; yDiff <= 1; yDiff++) {
      for (int xDiff = -1; xDiff <= 1; xDiff++) {
        int distanceFromCard = Math.abs(xDiff) + Math.abs(yDiff);
        int adjacentCardY = cardCoordinates.y + yDiff;
        int adjacentCardX = cardCoordinates.x + xDiff;
        if (distanceFromCard == 1 && coordinatesInBounds(adjacentCardY, adjacentCardX)) {
          cardsToReturn.add(this.cards[adjacentCardY][adjacentCardX]);
        }
      }
    }
    return cardsToReturn;
  }

  private boolean coordinatesInBounds(int y, int x) {
    return (y >= 0 && x >= 0 && y < this.height && x < this.width);
  }

  public Point getCardCoordinates(Card centerCard) {
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        if (centerCard == this.cards[row][col]) {
          return new Point(col, row);
        }
      }
    }
    return new Point(-2, -2);
  }


  @Override
  public Iterator<Card> iterator() {
    return new Iterator<Card>() {

      int row = 1, col = 1;

      @Override
      public boolean hasNext() {
        return this.row <= HatchingGround.this.height;
      }

      @Override
      public Card next() {
        Card card = HatchingGround.this.cards[this.row - 1][this.col - 1];
        if (this.col == HatchingGround.this.width) {
          this.col = 1;
          this.row++;
        } else {
          this.col++;
        }

        return card;
      }
    };
  }

  public List<Card> pullAndReplaceCompleteEggs() {
    List<Card> completeEggs = new ArrayList<>();
    ElementSpaceLogic elementSpaceLogic = new ElementSpaceLogic();
    // call populate in the future when cards are done
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        Card currentCard = this.cards[row][col];
        if (elementSpaceLogic.isComplete(currentCard) && currentCard.handler != WildHandler
            .getInstance()) {
          completeEggs.add(this.cards[row][col]);
          this.cards[row][col] = this.deck.draw();
        }
      }
    }
    return completeEggs;
  }

  public Card findCard(Handler handler) {
    for (Card card : this) {
      if (card.handler == handler) {
        return card;
      }
    }
    return null;
  }
}
