package underlings.scoring;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.Card;
import underlings.card.Temperature;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class ScoreTests {

    private ScoreUtils scoreUtils;
    private Player[] players;
    private Gui gui;

    @SuppressWarnings("serial")
    @Before
    public void init() {
        this.gui = EasyMock.mock(Gui.class);
        this.players = new Player[5];

        this.players[0] = TestUtils.Player(0);
        this.players[0].hatchedCards = new ArrayList<Card>() {
            {
                this.add(TestUtils.Card(12, Temperature.WARM));
                this.add(TestUtils.Card(3, Temperature.COOL));
                this.add(TestUtils.Card(6, Temperature.NEUTRAL));
                this.add(TestUtils.Card(19, Temperature.COOL));
                this.add(TestUtils.Card(3, Temperature.WARM));
            }
        };

        this.players[1] = TestUtils.Player(1);
        this.players[1].hatchedCards = new ArrayList<Card>() {
            {
                this.add(TestUtils.Card(16, Temperature.WARM));
                this.add(TestUtils.Card(19, Temperature.COOL));
                this.add(TestUtils.Card(10, Temperature.NEUTRAL));
            }
        };

        this.players[2] = TestUtils.Player(2);
        this.players[2].hatchedCards = new ArrayList<Card>();

        this.players[3] = TestUtils.Player(3);
        this.players[3].hatchedCards = new ArrayList<Card>() {
            {
                this.add(TestUtils.Card(3, Temperature.COOL));
                this.add(TestUtils.Card(10, Temperature.COOL));
            }
        };

        this.players[4] = TestUtils.Player(4);
        this.players[4].hatchedCards = new ArrayList<Card>() {
            {
                this.add(TestUtils.Card(3, Temperature.WARM));
                this.add(TestUtils.Card(3, Temperature.WARM));
            }
        };


    }

    @Test
    public void testTwoPlayers() {
        this.scoreUtils = TestUtils.ScoreUtils(this.players[0], this.players[1]);
        this.scoreUtils.calculateScores();

        assertEquals(43, this.players[0].score);
        assertEquals(45, this.players[1].score);
        assertEquals(45, this.scoreUtils.winningScore);
    }

    @Test
    public void testTwoNeutralOneEmpty() {
        this.scoreUtils = TestUtils.ScoreUtils(this.players[0], this.players[1], this.players[2]);
        this.scoreUtils.calculateScores();

        assertEquals(63, this.players[0].score);
        assertEquals(65, this.players[1].score);
        assertEquals(20, this.players[2].score);
        assertEquals(65, this.scoreUtils.winningScore);

    }

    @Test
    public void testTwoNeutralOneEmptyOneCool() {
        this.scoreUtils = TestUtils.ScoreUtils(this.players[0], this.players[1], this.players[2], this.players[3]);
        this.scoreUtils.calculateScores();

        assertEquals(63, this.players[0].score);
        assertEquals(65, this.players[1].score);
        assertEquals(20, this.players[2].score);
        assertEquals(28, this.players[3].score);
        assertEquals(65, this.scoreUtils.winningScore);

    }

    @Test
    public void testAll() {
        this.scoreUtils = TestUtils.ScoreUtils(this.players[0], this.players[1], this.players[2], this.players[3],
                this.players[4]);
        this.scoreUtils.calculateScores();

        assertEquals(63, this.players[0].score);
        assertEquals(65, this.players[1].score);
        assertEquals(20, this.players[2].score);
        assertEquals(28, this.players[3].score);
        assertEquals(21, this.players[4].score);
        assertEquals(65, this.scoreUtils.winningScore);
    }


    @Test
    public void testDisplayScores() {
        this.scoreUtils = TestUtils.ScoreUtils(this.gui, this.players[0], this.players[1]);
        this.players[0].score = 1;
        this.players[1].score = 5;

        this.gui.alert(LocaleWrap.format("player_score", this.players[0], this.players[0].score), this.players[0].id,
                PromptType.REGULAR);
        this.gui.alert(LocaleWrap.format("player_score", this.players[1], this.players[1].score), this.players[1].id,
                PromptType.REGULAR);

        EasyMock.replay(this.gui);

        this.scoreUtils.displayScores();

        EasyMock.verify(this.gui);
    }

    @Test
    public void testDisplayWinners() {
        this.scoreUtils = TestUtils.ScoreUtils(this.gui, this.players[0], this.players[1]);
        this.players[0].score = 5;
        this.players[1].score = 10;
        List<Player> winners = new ArrayList<>();
        winners.add(this.players[1]);
        this.scoreUtils.winningScore = 10;
        this.gui.alert(LocaleWrap.format("winners", winners), PromptType.REGULAR);

        EasyMock.replay(this.gui);

        this.scoreUtils.displayWinners();

        EasyMock.verify(this.gui);
    }

}
