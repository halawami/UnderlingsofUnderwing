package underlings.scoring;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.Card;
import underlings.card.Temperature;

public class TemperatureTests {

    private Scoring scoreUtils;
    private Card[] cards;

    @Before
    public void init() {
        this.scoreUtils = new Scoring(null, null);
        this.cards = new Card[4];
        this.cards[0] = TestUtils.makeCard(Temperature.WARM);
        this.cards[1] = TestUtils.makeCard(Temperature.COOL);
        this.cards[2] = TestUtils.makeCard(Temperature.NEUTRAL);
        this.cards[3] = new Card();
    }

    @Test
    public void testNoCards() {
        int balance = this.scoreUtils.calculateTemperature(Collections.emptyList());

        assertEquals(0, balance);
    }

    @Test
    public void testPerfectBalance() {
        int balance = this.scoreUtils.calculateTemperature(Arrays.asList(this.cards));

        assertEquals(0, balance);
    }

    @Test
    public void testWarmBalance() {
        this.cards[3] = TestUtils.makeCard(Temperature.WARM);
        int balance = this.scoreUtils.calculateTemperature(Arrays.asList(this.cards));

        assertEquals(1, balance);
    }

    @Test
    public void testCoolBalance() {
        this.cards[3] = TestUtils.makeCard(Temperature.COOL);
        int balance = this.scoreUtils.calculateTemperature(Arrays.asList(this.cards));

        assertEquals(-1, balance);
    }

}
