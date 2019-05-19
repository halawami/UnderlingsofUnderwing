package underlings;

import java.util.Arrays;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.player.Player;
import underlings.scoring.Scoring;

public class TestUtils {

    public static Scoring makeScoreUtils(Gui gui, Player... players) {
        return new Scoring(Arrays.asList(players), gui);
    }

    public static Scoring makeScoreUtils(Player... players) {
        return new Scoring(Arrays.asList(players), null);
    }

    public static Scoring makeScoreUtils() {
        return new Scoring(null, null);
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
