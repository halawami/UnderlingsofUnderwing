package Game;

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
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				this.cards[i][j] = this.deck.draw();
			}
		}
	}

}
