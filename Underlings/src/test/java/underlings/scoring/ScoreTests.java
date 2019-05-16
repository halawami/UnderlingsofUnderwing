package underlings.scoring;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
        this.players = new Player[6];

        this.players[0] = new Player(6, new HandlerFactory(), 1);
        this.players[0].hatchedCards = new ArrayList<Card>() {
            {
                this.add(ScoreTests.this.createCard(12, Temperature.WARM));
                this.add(ScoreTests.this.createCard(3, Temperature.WARM));
                this.add(ScoreTests.this.createCard(6, Temperature.NEUTRAL));
                this.add(ScoreTests.this.createCard(19, Temperature.WARM));
                this.add(ScoreTests.this.createCard(3, Temperature.COOL));
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

        this.players[5] = new Player(6, new HandlerFactory(), 6);
        this.players[5].hatchedCards = new ArrayList<Card>() {
            {
                this.add(ScoreTests.this.createCard(3, Temperature.COOL));
                this.add(ScoreTests.this.createCard(10, Temperature.COOL));
            }
        };
        // this.scoreUtils = new ScoreUtils(Arrays.asList(players), gui);
    }

    @Test
    public void testTwoPlayers() {
        this.scoreUtils = new ScoreUtils(Arrays.asList(this.players[0], this.players[1]), this.gui);
        this.scoreUtils.calculateScores();

        assertEquals(43, (int) (this.scoreUtils.scores.get(this.players[0])));
        assertEquals(45, (int) (this.scoreUtils.scores.get(this.players[1])));
    }

    @Test
    public void testOneEmpty() {
        this.scoreUtils = new ScoreUtils(Arrays.asList(this.players[0], this.players[1], this.players[2]), this.gui);
        this.scoreUtils.calculateScores();

        assertEquals(58, (int) (this.scoreUtils.scores.get(this.players[0])));
        assertEquals(65, (int) (this.scoreUtils.scores.get(this.players[1])));
        assertEquals(20, (int) (this.scoreUtils.scores.get(this.players[2])));

    }

    @Test
    public void testOneNeutralWarmCool() {
        this.scoreUtils = new ScoreUtils(
                Arrays.asList(this.players[0], this.players[1], this.players[2], this.players[3]), this.gui);
        this.scoreUtils.calculateScores();

        assertEquals(58, (int) (this.scoreUtils.scores.get(this.players[0])));
        assertEquals(65, (int) (this.scoreUtils.scores.get(this.players[1])));
        assertEquals(20, (int) (this.scoreUtils.scores.get(this.players[2])));
        assertEquals(28, (int) (this.scoreUtils.scores.get(this.players[3])));

    }

    @Test
    public void testTwoSameNetWarm() {
        this.scoreUtils = new ScoreUtils(
                Arrays.asList(this.players[0], this.players[1], this.players[2], this.players[3], this.players[4]),
                this.gui);
        this.scoreUtils.calculateScores();

        assertEquals(58, (int) (this.scoreUtils.scores.get(this.players[0])));
        assertEquals(65, (int) (this.scoreUtils.scores.get(this.players[1])));
        assertEquals(20, (int) (this.scoreUtils.scores.get(this.players[2])));
        assertEquals(28, (int) (this.scoreUtils.scores.get(this.players[3])));
        assertEquals(21, (int) (this.scoreUtils.scores.get(this.players[4])));
    }

    @Test
    public void testTwoSameNetCool() {
        this.scoreUtils = new ScoreUtils(Arrays.asList(this.players[0], this.players[1], this.players[2],
                this.players[3], this.players[4], this.players[5]), this.gui);
        this.scoreUtils.calculateScores();

        assertEquals(58, (int) (this.scoreUtils.scores.get(this.players[0])));
        assertEquals(28, (int) (this.scoreUtils.scores.get(this.players[3])));
        assertEquals(28, (int) (this.scoreUtils.scores.get(this.players[5])));
    }

    public Card createCard(int points, Temperature temperature) {
        Card toReturn = new Card();
        toReturn.points = points;
        toReturn.temperature = temperature;
        return toReturn;
    }

    @Test
    public void testDisplayScoresTwoPlayers() {
        List<Player> fakePlayers = new LinkedList<>();
        fakePlayers = Arrays.asList(this.players[0], this.players[1]);
        this.scoreUtils = new ScoreUtils(fakePlayers, this.gui);
        this.scoreUtils.scores.put(this.players[1], 5);
        this.scoreUtils.scores.put(this.players[0], 1);
        for (Player player : this.scoreUtils.scores.keySet()) {
            this.gui.alert(player + ": " + this.scoreUtils.scores.get(player) + " points", player.getId(),
                    PromptType.REGULAR);
        }
        EasyMock.replay(this.gui);
        this.scoreUtils.displayScores();
        EasyMock.verify(this.gui);
    }

    @Test
    public void testDisplayScoresNoPlayers() {
        List<Player> fakePlayers = new LinkedList<>();
        this.scoreUtils = new ScoreUtils(fakePlayers, this.gui);
        EasyMock.replay(this.gui);
        this.scoreUtils.displayScores();
        EasyMock.verify(this.gui);
        assertEquals(0, this.scoreUtils.winners.size());
    }

    @Test
    public void testDecideWinnersDifferentScores() {
        List<Player> fakePlayers = new LinkedList<>();
        fakePlayers = Arrays.asList(this.players[0], this.players[1]);
        this.scoreUtils = new ScoreUtils(fakePlayers, this.gui);
        this.scoreUtils.scores.put(this.players[1], 1);
        this.scoreUtils.scores.put(this.players[0], 5);
        EasyMock.replay(this.gui);
        int max = this.scoreUtils.decideWinners(this.scoreUtils.scores, fakePlayers.get(0), 0);
        int finalMax = this.scoreUtils.decideWinners(this.scoreUtils.scores, fakePlayers.get(1), max);
        EasyMock.verify(this.gui);
        assertEquals(1, this.scoreUtils.winners.size());
        assertEquals(5, finalMax);
    }

    @Test
    public void testDecideWinnersMultipleDifferentScores() {
        List<Player> fakePlayers = new LinkedList<>();
        fakePlayers = Arrays.asList(this.players[0], this.players[1], this.players[2]);
        this.scoreUtils = new ScoreUtils(fakePlayers, this.gui);
        this.scoreUtils.scores.put(this.players[0], 5);
        this.scoreUtils.scores.put(this.players[1], 1);
        this.scoreUtils.scores.put(this.players[2], 6);
        EasyMock.replay(this.gui);
        int max = this.scoreUtils.decideWinners(this.scoreUtils.scores, fakePlayers.get(0), 0);
        int secondMax = this.scoreUtils.decideWinners(this.scoreUtils.scores, fakePlayers.get(1), max);
        int finalMax = this.scoreUtils.decideWinners(this.scoreUtils.scores, fakePlayers.get(2), secondMax);
        EasyMock.verify(this.gui);
        assertEquals(1, this.scoreUtils.winners.size());
        assertEquals(6, finalMax);
    }

    @Test
    public void testDecideWinnersSameScores() {
        List<Player> fakePlayers = new LinkedList<>();
        fakePlayers = Arrays.asList(this.players[0], this.players[1]);
        this.scoreUtils = new ScoreUtils(fakePlayers, this.gui);
        this.scoreUtils.scores.put(this.players[1], 1);
        this.scoreUtils.scores.put(this.players[0], 1);
        EasyMock.replay(this.gui);
        int max = this.scoreUtils.decideWinners(this.scoreUtils.scores, fakePlayers.get(0), 0);
        int finalMax = this.scoreUtils.decideWinners(this.scoreUtils.scores, fakePlayers.get(1), max);
        EasyMock.verify(this.gui);
        assertEquals(1, finalMax);
        assertEquals(2, this.scoreUtils.winners.size());
    }



    @Test
    public void testDisplayOneWinner() {
        List<Player> fakePlayers = new LinkedList<>();
        fakePlayers = Arrays.asList(this.players[0], this.players[1]);
        this.scoreUtils = new ScoreUtils(fakePlayers, this.gui);
        this.scoreUtils.winners.add(this.players[0]);
        this.gui.alert(LocaleWrap.format("winners", this.scoreUtils.winners), PromptType.REGULAR);

        EasyMock.replay(this.gui);

        this.scoreUtils.displayWinners();

        EasyMock.verify(this.gui);
    }

    @Test
    public void testDisplayTwoWinner() {
        List<Player> fakePlayers = new LinkedList<>();
        fakePlayers = Arrays.asList(this.players[0], this.players[1]);
        this.scoreUtils = new ScoreUtils(fakePlayers, this.gui);
        this.scoreUtils.winners.add(this.players[0]);
        this.scoreUtils.winners.add(this.players[1]);
        this.gui.alert(LocaleWrap.format("winners", this.scoreUtils.winners), PromptType.REGULAR);

        EasyMock.replay(this.gui);

        this.scoreUtils.displayWinners();

        EasyMock.verify(this.gui);
    }
}
