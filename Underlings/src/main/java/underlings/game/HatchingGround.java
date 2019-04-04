package underlings.game;
import java.util.List;

import underlings.gui.GUI;

public class HatchingGround {

	private int height, width;
	private Card[][] cards;
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
		return null;
	}

}
