package underlings.scoring;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.gui.Gui;
import underlings.player.Player;

public class TemperatureTests {

    private ScoreUtils scoreUtils;
    private Card[] cards;

    @Before
    public void init() {
        Player player = EasyMock.mock(Player.class);
        List<Player> players = Arrays.asList(player);
        Gui gui = EasyMock.mock(Gui.class);
        this.scoreUtils = new ScoreUtils(players, gui);
        this.cards = new Card[] {new Card(), new Card(), new Card(), new Card()};
    }

    @Test
    public void testEmpty() {
        int balance = this.scoreUtils.calculateTemperature(Collections.emptyList());

        assertEquals(0, balance);
    }

    @Test
    public void testPerfectBalance() {
        this.cards[0].temperature = Temperature.WARM;
        this.cards[1].temperature = Temperature.COOL;
        this.cards[2].temperature = Temperature.NEUTRAL;

        int balance = this.scoreUtils.calculateTemperature(Arrays.asList(this.cards));

        assertEquals(0, balance);
    }

    @Test
    public void testWarmBalance() {
        this.cards[0].temperature = Temperature.WARM;
        this.cards[1].temperature = Temperature.COOL;
        this.cards[2].temperature = Temperature.NEUTRAL;
        this.cards[3].temperature = Temperature.WARM;

        int balance = this.scoreUtils.calculateTemperature(Arrays.asList(this.cards));

        assertEquals(1, balance);
    }

    @Test
    public void testCoolBalance() {
        this.cards[0].temperature = Temperature.WARM;
        this.cards[1].temperature = Temperature.COOL;
        this.cards[2].temperature = Temperature.NEUTRAL;
        this.cards[3].temperature = Temperature.COOL;

        int balance = this.scoreUtils.calculateTemperature(Arrays.asList(this.cards));

        assertEquals(-1, balance);
    }

}
