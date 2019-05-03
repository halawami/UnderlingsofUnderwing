package underlings.scoring;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.scoring.ScoreUtils;

public class TemperatureTests {

    @Test
    public void testEmpty() {
        ScoreUtils scoreUtils = new ScoreUtils();
        int balance = scoreUtils.calculateTemperature(Collections.emptyList());

        assertEquals(0, balance);
    }

    @Test
    public void testPerfectBalance() {
        Card cardOne = new Card();
        Card cardTwo = new Card();
        Card cardThree = new Card();

        cardOne.temperature = Temperature.WARM;
        cardTwo.temperature = Temperature.COOL;
        cardThree.temperature = Temperature.NEUTRAL;

        List<Card> cards = new ArrayList<>();
        cards.add(cardOne);
        cards.add(cardTwo);
        cards.add(cardThree);

        ScoreUtils scoreUtils = new ScoreUtils();
        int balance = scoreUtils.calculateTemperature(cards);

        assertEquals(0, balance);
    }

    @Test
    public void testWarmBalance() {
        List<Card> cards = new ArrayList<>();

        Card cardOne = new Card();
        cardOne.temperature = Temperature.WARM;
        cards.add(cardOne);

        Card cardTwo = new Card();
        cardTwo.temperature = Temperature.COOL;
        cards.add(cardTwo);

        Card cardThree = new Card();
        cardThree.temperature = Temperature.NEUTRAL;
        cards.add(cardThree);

        Card cardFour = new Card();
        cardFour.temperature = Temperature.WARM;
        cards.add(cardFour);

        ScoreUtils scoreUtils = new ScoreUtils();
        int balance = scoreUtils.calculateTemperature(cards);

        assertEquals(1, balance);
    }

    @Test
    public void testCoolBalance() {
        List<Card> cards = new ArrayList<>();

        Card cardOne = new Card();
        cardOne.temperature = Temperature.WARM;
        cards.add(cardOne);

        Card cardTwo = new Card();
        cardTwo.temperature = Temperature.COOL;
        cards.add(cardTwo);

        Card cardThree = new Card();
        cardThree.temperature = Temperature.NEUTRAL;
        cards.add(cardThree);

        Card cardFour = new Card();
        cardFour.temperature = Temperature.COOL;
        cards.add(cardFour);

        ScoreUtils scoreUtils = new ScoreUtils();
        int balance = scoreUtils.calculateTemperature(cards);

        assertEquals(-1, balance);
    }

}
