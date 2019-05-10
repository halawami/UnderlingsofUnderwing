package underlings.scoring;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;

public class PointTests {

    private ScoreUtils scoreUtils;

    @Before
    public void init() {
        this.scoreUtils = new ScoreUtils();
    }

    @Test
    public void testEmpty() {
        int points = this.scoreUtils.calculatePoints(Collections.emptyList());

        assertEquals(0, points);
    }

    @Test
    public void testOneCard() {
        Card cardOne = new Card();
        cardOne.points = 10;

        List<Card> cards = new ArrayList<>();
        cards.add(cardOne);

        int points = this.scoreUtils.calculatePoints(cards);

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

        int points = this.scoreUtils.calculatePoints(cards);

        assertEquals(29, points);
    }

}
