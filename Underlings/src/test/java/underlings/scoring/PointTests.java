package underlings.scoring;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.Card;

public class PointTests {

    private ScoreUtils scoreUtils;

    @Before
    public void init() {
        this.scoreUtils = TestUtils.ScoreUtils();
    }

    @Test
    public void testNoCards() {
        int points = this.scoreUtils.calculatePoints(Collections.emptyList());
        assertEquals(0, points);
    }

    @Test
    public void testOneCard() {
        Card[] cards = {TestUtils.Card(10)};
        int points = this.scoreUtils.calculatePoints(Arrays.asList(cards));
        assertEquals(10, points);
    }

    @Test
    public void testTwoCards() {
        Card[] cards = {TestUtils.Card(13), TestUtils.Card(16)};
        int points = this.scoreUtils.calculatePoints(Arrays.asList(cards));
        assertEquals(29, points);
    }

}
