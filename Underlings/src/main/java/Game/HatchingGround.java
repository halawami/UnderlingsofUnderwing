package Game;

public class HatchingGround {

    private int height, width;
    private Card[][] cards;

    public HatchingGround(int width, int height, Deck deck) {
        this.width = width;
        this.height = height;
        this.cards = new Card[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cards[i][j] = deck.draw();
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }


}
