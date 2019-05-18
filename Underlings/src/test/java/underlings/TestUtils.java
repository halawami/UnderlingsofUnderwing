package underlings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public class TestUtils<T> {

    private Class<T> objectsClass;

    public static <T> TestUtils<T> mockListOf(Class<T> objectsClass) {
        TestUtils<T> utilsContainingType = new TestUtils<>();
        utilsContainingType.objectsClass = objectsClass;
        return utilsContainingType;
    }

    public List<T> withLength(int length) {
        List<T> mockObjects = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            mockObjects.add(EasyMock.mock(this.objectsClass));
        }
        return mockObjects;
    }

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

    public static Player Player() {
        return new Player(6, new HandlerFactory(), 0);
    }

}
