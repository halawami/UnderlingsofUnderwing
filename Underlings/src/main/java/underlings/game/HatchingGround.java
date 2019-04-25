package underlings.game;

import underlings.card.Card;
import underlings.gui.GUI;

import java.util.ArrayList;
import java.util.Iterator;
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

	// TODO: implement this
	public List<Card> pullAndReplaceCompleteEggs() {
		return new ArrayList<Card>();
	}
}
