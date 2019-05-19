package underlings;

import java.util.Arrays;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public class TestUtils {

    public static ScoreUtils makeScoreUtils(Gui gui, Player... players) {
        return new ScoreUtils(Arrays.asList(players), gui);
    }

    public static ScoreUtils makeScoreUtils(Player... players) {
        return new ScoreUtils(Arrays.asList(players), null);
    }

    public static ScoreUtils makeScoreUtils() {
        return new ScoreUtils(null, null);
    }

    public static Card makeCard(Temperature temperature) {
        Card card = new Card();
        card.temperature = temperature;
        return card;
    }

    public static Card makeCard(int points, Temperature temperature) {
        Card card = new Card();
        card.points = points;
        card.temperature = temperature;
        return card;
    }

    public static Card makeCard(int points) {
        Card card = new Card();
        card.points = points;
        return card;
    }

    public static Player makePlayer(int id) {
        return new Player(6, new HandlerFactory(), id);
    }

    public static Player makePlayer() {
        return makePlayer(0);
    }

}
