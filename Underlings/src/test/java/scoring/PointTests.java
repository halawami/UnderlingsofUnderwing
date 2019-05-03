package scoring;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import card.Card;

public class PointTests {

    @Test
    public void testEmpty() {
        ScoreUtils scoreUtils = new ScoreUtils();
        int points = scoreUtils.calculatePoints(Collections.emptyList());

        assertEquals(0, points);
    }

    @Test
    public void testOneCard() {
        Card cardOne = new Card();
        cardOne.points = 10;

        List<Card> cards = new ArrayList<>();
        cards.add(cardOne);

        ScoreUtils scoreUtils = new ScoreUtils();
        int points = scoreUtils.calculatePoints(cards);

        assertEquals(10, points);

    }

    @Test
    public void testTwoCards() {
        Card cardOne = new Card();
        cardOne.points = 13;

        Card cardTwo = new Card();
        cardTwo.points = 16;

        List<Card> cards = new ArrayList<>();
        cards.add(cardOne);
        cards.add(cardTwo);

        ScoreUtils scoreUtils = new ScoreUtils();
        int points = scoreUtils.calculatePoints(cards);

        assertEquals(29, points);
    }

}
