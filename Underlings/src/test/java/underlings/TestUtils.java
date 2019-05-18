package underlings;

import java.util.Arrays;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public class TestUtils {

    public static ScoreUtils ScoreUtils(Gui gui, Player... players) {
        return new ScoreUtils(Arrays.asList(players), gui);
    }

    public static ScoreUtils ScoreUtils(Player... players) {
        return new ScoreUtils(Arrays.asList(players), null);
    }

    public static ScoreUtils ScoreUtils() {
        return new ScoreUtils(null, null);
    }

    public static Card Card(Temperature temperature) {
        Card card = new Card();
        card.temperature = temperature;
        return card;
    }

    public static Card Card(int points, Temperature temperature) {
        Card card = new Card();
        card.points = points;
        card.temperature = temperature;
        return card;
    }

    public static Card Card(int points) {
        Card card = new Card();
        card.points = points;
        return card;
    }

    public static Player Player(int id) {
        return new Player(6, new HandlerFactory(), id);
    }

    public static Player Player() {
        return Player(0);
    }

}
