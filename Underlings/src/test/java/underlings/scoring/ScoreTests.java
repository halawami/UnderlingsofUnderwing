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
import underlings.utilities.LocaleUtilities;

public class ScoreTests {

    private Scoring scoreUtils;
    private Player[] players;
    private Gui gui;

    @SuppressWarnings("serial")
    @Before
    public void init() {
        this.gui = EasyMock.mock(Gui.class);
        this.players = new Player[5];

        this.players[0] = TestUtils.makePlayer(0);
        this.players[0].hatchedCards = new ArrayList<Card>() {
            {
                this.add(TestUtils.makeCard(12, Temperature.WARM));
                this.add(TestUtils.makeCard(3, Temperature.COOL));
                this.add(TestUtils.makeCard(6, Temperature.NEUTRAL));
                this.add(TestUtils.makeCard(19, Temperature.COOL));
                this.add(TestUtils.makeCard(3, Temperature.WARM));
            }
        };

        this.players[1] = TestUtils.makePlayer(1);
        this.players[1].hatchedCards = new ArrayList<Card>() {
            {
                this.add(TestUtils.makeCard(16, Temperature.WARM));
                this.add(TestUtils.makeCard(19, Temperature.COOL));
                this.add(TestUtils.makeCard(10, Temperature.NEUTRAL));
            }
        };

        this.players[2] = TestUtils.makePlayer(2);
        this.players[2].hatchedCards = new ArrayList<Card>();

        this.players[3] = TestUtils.makePlayer(3);
        this.players[3].hatchedCards = new ArrayList<Card>() {
            {
                this.add(TestUtils.makeCard(3, Temperature.COOL));
                this.add(TestUtils.makeCard(10, Temperature.COOL));
            }
        };

        this.players[4] = TestUtils.makePlayer(4);
        this.players[4].hatchedCards = new ArrayList<Card>() {
            {
                this.add(TestUtils.makeCard(3, Temperature.WARM));
                this.add(TestUtils.makeCard(3, Temperature.WARM));
            }
        };


    }

    @Test
    public void testTwoPlayers() {
        this.scoreUtils = TestUtils.makeScoreUtils(this.players[0], this.players[1]);
        this.scoreUtils.calculateScores();

        assertEquals(43, this.players[0].score);
        assertEquals(45, this.players[1].score);
        assertEquals(45, this.scoreUtils.winningScore);
    }

    @Test
    public void testTwoNeutralOneEmpty() {
        this.scoreUtils = TestUtils.makeScoreUtils(this.players[0], this.players[1], this.players[2]);
        this.scoreUtils.calculateScores();

        assertEquals(63, this.players[0].score);
        assertEquals(65, this.players[1].score);
        assertEquals(20, this.players[2].score);
        assertEquals(65, this.scoreUtils.winningScore);

    }

    @Test
    public void testTwoNeutralOneEmptyOneCool() {
        this.scoreUtils = TestUtils.makeScoreUtils(this.players[0], this.players[1], this.players[2], this.players[3]);
        this.scoreUtils.calculateScores();

        assertEquals(63, this.players[0].score);
        assertEquals(65, this.players[1].score);
        assertEquals(20, this.players[2].score);
        assertEquals(28, this.players[3].score);
        assertEquals(65, this.scoreUtils.winningScore);

    }

    @Test
    public void testAll() {
        this.scoreUtils = TestUtils.makeScoreUtils(this.players[0], this.players[1], this.players[2], this.players[3],
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
        this.scoreUtils = TestUtils.makeScoreUtils(this.gui, this.players[0], this.players[1]);
        this.players[0].score = 1;
        this.players[1].score = 5;

        this.gui.alert(LocaleUtilities.format("player_score", this.players[0], this.players[0].score),
                this.players[0].id, PromptType.REGULAR);
        this.gui.alert(LocaleUtilities.format("player_score", this.players[1], this.players[1].score),
                this.players[1].id, PromptType.REGULAR);

        EasyMock.replay(this.gui);

        this.scoreUtils.displayScores();

        EasyMock.verify(this.gui);
    }

    @Test
    public void testDisplayWinners() {
        this.scoreUtils = TestUtils.makeScoreUtils(this.gui, this.players[0], this.players[1]);
        this.players[0].score = 5;
        this.players[1].score = 10;
        List<Player> winners = new ArrayList<>();
        winners.add(this.players[1]);
        this.scoreUtils.winningScore = 10;
        this.gui.alert(LocaleUtilities.format("winners", winners), PromptType.REGULAR);

        EasyMock.replay(this.gui);

        this.scoreUtils.displayWinners();

        EasyMock.verify(this.gui);
    }

}
