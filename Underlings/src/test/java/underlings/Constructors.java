package underlings;

import underlings.card.Card;
import underlings.handler.HandlerFactory;
import underlings.player.Player;

public class Constructors {

    public static Player Player() {
        return new Player(6, new HandlerFactory(), 0);
    }

    public static Card Card(int points) {
        Card card = new Card();
        card.points = points;
        return card;
    }
}
