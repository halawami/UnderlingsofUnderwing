package underlings.game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.handler.Handler;
import underlings.handler.WildHandler;

public class HatchingGround implements Iterable<Card> {

    private int height;
    private int width;
    public Card[][] cards;
    protected Deck deck;
    public ElementSpaceLogic logic;
    public boolean lateHatching;

    public HatchingGround(Deck deck, ElementSpaceLogic logic) {
        this.deck = deck;
        this.logic = logic;
        this.lateHatching = false;
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
                this.placeCard(row, col, this.deck.draw());
            }
        }
    }

    public List<Card> getUnclaimedEggs() {
        return this.getDragons(Integer.MAX_VALUE, true);
    }

    public List<Card> getClaimedEggs() {
        return this.getDragons(Integer.MAX_VALUE, false);
    }

    public List<Card> getAdjacentCards(Card centerCard) {
        Point cardCoordinates = this.getCardCoordinates(centerCard);
        List<Card> cardsToReturn = new LinkedList<>();

        int y = cardCoordinates.y;
        int x = cardCoordinates.x;
        if (y > 0) {
            cardsToReturn.add(this.cards[y - 1][x]);
        }
        if (x > 0) {
            cardsToReturn.add(this.cards[y][x - 1]);
        }
        if (y < this.height - 1) {
            cardsToReturn.add(this.cards[y + 1][x]);
        }
        if (x < this.width - 1) {
            cardsToReturn.add(this.cards[y][x + 1]);
        }

        return cardsToReturn;
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

            int row = 1;
            int col = 1;

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
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                Card currentCard = this.cards[row][col];
                if (this.logic.isComplete(currentCard) && currentCard.handler != WildHandler.getInstance()) {
                    completeEggs.add(this.cards[row][col]);
                    this.placeCard(row, col, this.deck.draw());
                }
            }
        }
        return completeEggs;
    }

    public void replaceCard(Card card) {
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if (this.cards[row][col] == card) {
                    this.placeCard(row, col, this.deck.draw());
                }
            }
        }
    }

    public void placeCard(int row, int col, Card card) {
        this.cards[row][col] = card;
        if (card.handler == WildHandler.getInstance()) {
            for (Effect effect : card.wildEffects) {
                effect.apply();
            }

        }
    }

    public Card findCard(Handler handler) {
        for (Card card : this) {
            if (card.handler == handler) {
                return card;
            }
        }
        return null;
    }

    public List<Card> getAllCards() {
        List<Card> allCards = new ArrayList<>();
        for (Card card : this) {
            allCards.add(card);
        }
        return allCards;
    }

    public List<Card> getDragons(int maxPoints, boolean unclaimed) {
        List<Card> validCards = new LinkedList<>();
        for (Card card : this) {
            if (card.points <= maxPoints && card.handler == null && unclaimed) {
                validCards.add(card);
            } else if (!unclaimed && card.handler != null) {
                validCards.add(card);
            }
        }
        return validCards;
    }

}
