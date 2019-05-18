package underlings.scoring;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.Temperature;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.handler.HandlerFactory;
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

        this.players[0] = new Player(6, new HandlerFactory(), 1);
        this.players[0].hatchedCards = new ArrayList<Card>() {
            {
                this.add(ScoreTests.this.createCard(12, Temperature.WARM));
                this.add(ScoreTests.this.createCard(3, Temperature.COOL));
                this.add(ScoreTests.this.createCard(6, Temperature.NEUTRAL));
                this.add(ScoreTests.this.createCard(19, Temperature.COOL));
                this.add(ScoreTests.this.createCard(3, Temperature.WARM));
            }
        };

        this.players[1] = new Player(6, new HandlerFactory(), 2);
        this.players[1].hatchedCards = new ArrayList<Card>() {
            {
                this.add(ScoreTests.this.createCard(16, Temperature.WARM));
                this.add(ScoreTests.this.createCard(19, Temperature.COOL));
                this.add(ScoreTests.this.createCard(10, Temperature.NEUTRAL));
            }
        };

        this.players[2] = new Player(6, new HandlerFactory(), 3);
        this.players[2].hatchedCards = new ArrayList<Card>();

        this.players[3] = new Player(6, new HandlerFactory(), 4);
        this.players[3].hatchedCards = new ArrayList<Card>() {
            {
                this.add(ScoreTests.this.createCard(3, Temperature.COOL));
                this.add(ScoreTests.this.createCard(10, Temperature.COOL));
            }
        };

        this.players[4] = new Player(6, new HandlerFactory(), 5);
        this.players[4].hatchedCards = new ArrayList<Card>() {
            {
                this.add(ScoreTests.this.createCard(3, Temperature.WARM));
                this.add(ScoreTests.this.createCard(3, Temperature.WARM));
            }
        };


    }

    @Test
    public void testTwoPlayers() {
        this.scoreUtils = new ScoreUtils(Arrays.asList(this.players[0], this.players[1]), this.gui);
        this.scoreUtils.calculateScores();

        assertEquals(43, this.players[0].score);
        assertEquals(45, this.players[1].score);

        assertEquals(45, this.scoreUtils.winningScore);
    }

    @Test
    public void testTwoNeutralOneEmpty() {
        this.scoreUtils = new ScoreUtils(Arrays.asList(this.players[0], this.players[1], this.players[2]), this.gui);
        this.scoreUtils.calculateScores();

        assertEquals(63, this.players[0].score);
        assertEquals(65, this.players[1].score);
        assertEquals(20, this.players[2].score);

        assertEquals(65, this.scoreUtils.winningScore);

    }

    @Test
    public void testTwoNeutralOneEmptyOneCool() {
        this.scoreUtils = new ScoreUtils(
                Arrays.asList(this.players[0], this.players[1], this.players[2], this.players[3]), this.gui);
        this.scoreUtils.calculateScores();

        assertEquals(63, this.players[0].score);
        assertEquals(65, this.players[1].score);
        assertEquals(20, this.players[2].score);
        assertEquals(28, this.players[3].score);

        assertEquals(65, this.scoreUtils.winningScore);

    }

    @Test
    public void testAll() {
        this.scoreUtils = new ScoreUtils(
                Arrays.asList(this.players[0], this.players[1], this.players[2], this.players[3], this.players[4]),
                this.gui);
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
        List<Player> players = Arrays.asList(this.players[0], this.players[1]);
        this.scoreUtils = new ScoreUtils(players, this.gui);
        this.players[0].score = 1;
        this.players[1].score = 5;

        for (Player player : players) {
            this.gui.alert(LocaleWrap.format("player_score", player, player.score), player.id, PromptType.REGULAR);
        }

        EasyMock.replay(this.gui);

        this.scoreUtils.displayScores();

        EasyMock.verify(this.gui);
    }

    @Test
    public void testDisplayWinners() {
        List<Player> players = Arrays.asList(this.players[0], this.players[1]);
        this.scoreUtils = new ScoreUtils(players, this.gui);
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

    private Card createCard(int points, Temperature temperature) {
        Card toReturn = new Card();
        toReturn.points = points;
        toReturn.temperature = temperature;
        return toReturn;
    }

}
