package underlings.scoring;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.gui.ConcretePrompt;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.player.Player;

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
		this.scoreUtils = new ScoreUtils(Arrays.asList(players[0], players[1]), gui);
		this.scoreUtils.calculateScores();

		assertEquals(43, (int) (this.scoreUtils.scores.get(this.players[0])));
		assertEquals(45, (int) (this.scoreUtils.scores.get(this.players[1])));
	}

	@Test
	public void testOneEmpty() {
		this.scoreUtils = new ScoreUtils(Arrays.asList(players[0], players[1], players[2]), gui);
		this.scoreUtils.calculateScores();

		assertEquals(58, (int) (this.scoreUtils.scores.get(this.players[0])));
		assertEquals(65, (int) (this.scoreUtils.scores.get(this.players[1])));
		assertEquals(20, (int) (this.scoreUtils.scores.get(this.players[2])));

	}

	@Test
	public void testOneNeutralWarmCool() {
		this.scoreUtils = new ScoreUtils(Arrays.asList(players[0], players[1], players[2], players[3]), gui);
		this.scoreUtils.calculateScores();

		assertEquals(58, (int) (this.scoreUtils.scores.get(this.players[0])));
		assertEquals(65, (int) (this.scoreUtils.scores.get(this.players[1])));
		assertEquals(20, (int) (this.scoreUtils.scores.get(this.players[2])));
		assertEquals(28, (int) (this.scoreUtils.scores.get(this.players[3])));

	}

	@Test
	public void testTwoSameNetWarm() {
		this.scoreUtils = new ScoreUtils(Arrays.asList(players[0], players[1], players[2], players[3], players[4]),
				gui);
		this.scoreUtils.calculateScores();

		assertEquals(58, (int) (this.scoreUtils.scores.get(this.players[0])));
		assertEquals(65, (int) (this.scoreUtils.scores.get(this.players[1])));
		assertEquals(20, (int) (this.scoreUtils.scores.get(this.players[2])));
		assertEquals(28, (int) (this.scoreUtils.scores.get(this.players[3])));
		assertEquals(21, (int) (this.scoreUtils.scores.get(this.players[4])));
	}

	@Test
	public void testTwoSameNetCool() {
		this.scoreUtils = new ScoreUtils(
				Arrays.asList(players[0], players[1], players[2], players[3], players[4], players[5]), gui);
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
		fakePlayers = Arrays.asList(players[0], players[1]);
		this.scoreUtils = new ScoreUtils(fakePlayers, gui);
		gui.promptHandler = EasyMock.mock(ConcretePrompt.class);
		this.scoreUtils.scores.put(players[1], 5);
		this.scoreUtils.scores.put(players[0], 1);
		for (Player player : this.scoreUtils.scores.keySet()) {
			gui.promptHandler.displayMessage(player + ": " + this.scoreUtils.scores.get(player) + " points",
					player.getPlayerId(), JOptionPane.PLAIN_MESSAGE);
		}
		EasyMock.replay(gui, gui.promptHandler);
		this.scoreUtils.displayScores();
		EasyMock.verify(gui, gui.promptHandler);
	}

	@Test
	public void testDisplayScoresNoPlayers() {
		List<Player> fakePlayers = new LinkedList<>();
		this.scoreUtils = new ScoreUtils(fakePlayers, gui);
		gui.promptHandler = EasyMock.mock(ConcretePrompt.class);
		EasyMock.replay(gui, gui.promptHandler);
		this.scoreUtils.displayScores();
		EasyMock.verify(gui, gui.promptHandler);
	}

	@Test
	public void testDecideWinnersDifferentScores() {
		List<Player> fakePlayers = new LinkedList<>();
		fakePlayers = Arrays.asList(players[0], players[1]);
		this.scoreUtils = new ScoreUtils(fakePlayers, gui);
		gui.promptHandler = EasyMock.mock(ConcretePrompt.class);
		this.scoreUtils.scores.put(players[1], 5);
		this.scoreUtils.scores.put(players[0], 1);
		EasyMock.replay(gui, gui.promptHandler);
		int max = this.scoreUtils.decideWinners(this.scoreUtils.scores, fakePlayers.get(0), 0);
		int finalMax = this.scoreUtils.decideWinners(this.scoreUtils.scores, fakePlayers.get(1), max);
		EasyMock.verify(gui, gui.promptHandler);
		assertEquals(5, finalMax);
	}

	@Test
	public void testDecideWinnersSameScores() {
		List<Player> fakePlayers = new LinkedList<>();
		fakePlayers = Arrays.asList(players[0], players[1]);
		this.scoreUtils = new ScoreUtils(fakePlayers, gui);
		gui.promptHandler = EasyMock.mock(ConcretePrompt.class);
		this.scoreUtils.scores.put(players[1], 1);
		this.scoreUtils.scores.put(players[0], 1);
		EasyMock.replay(gui, gui.promptHandler);
		int max = this.scoreUtils.decideWinners(this.scoreUtils.scores, fakePlayers.get(0), 0);
		int finalMax = this.scoreUtils.decideWinners(this.scoreUtils.scores, fakePlayers.get(1), max);
		EasyMock.verify(gui, gui.promptHandler);
		assertEquals(1, finalMax);
	}
}
