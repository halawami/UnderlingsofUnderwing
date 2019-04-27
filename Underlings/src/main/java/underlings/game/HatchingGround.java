package underlings.game;

import underlings.card.Card;
import underlings.gui.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
		List<Card> cardsToReturn = new LinkedList<>();
		cardsToReturn.add(this.cards[0][0]);
		cardsToReturn.add(this.cards[0][1]);
		cardsToReturn.add(this.cards[0][2]);
		cardsToReturn.add(this.cards[1][0]);
		cardsToReturn.add(this.cards[1][2]);
		cardsToReturn.add(this.cards[2][1]);
		cardsToReturn.add(this.cards[2][2]);
		cardsToReturn.add(this.cards[2][0]);
		return cardsToReturn;
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
		// call populate in the future when cards are done
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {
				Card currentCard = this.cards[row][col]; 
				if(currentCard.isComplete() && currentCard.handler != null){
					completeEggs.add(this.cards[row][col]);
					this.cards[row][col] = deck.draw();
				}
			}
		}
		return completeEggs;
	}
}
