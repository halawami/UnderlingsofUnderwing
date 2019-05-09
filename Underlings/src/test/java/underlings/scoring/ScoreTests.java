package underlings.scoring;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.handler.HandlerFactory;
import underlings.player.Player;

public class ScoreTests {

	private ScoreUtils scoreUtils;
	private Player[] players;

	@SuppressWarnings("serial")
	@Before
	public void init() {
		this.scoreUtils = new ScoreUtils();
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
	}

	@Test
	public void testTwoPlayers() {
		Map<Player, Integer> scores = this.scoreUtils.calculateScores(Arrays.asList(this.players), false);

		assertEquals(43, (int) (scores.get(this.players[0])));
		assertEquals(45, (int) (scores.get(this.players[1])));
	}

	@Test
	public void testOneEmpty() {
		Map<Player, Integer> scores = this.scoreUtils.calculateScores(Arrays.asList(this.players), true);

		assertEquals(58, (int) (scores.get(this.players[0])));
		assertEquals(65, (int) (scores.get(this.players[1])));
		assertEquals(20, (int) (scores.get(this.players[2])));

	}

	@Test
	public void testOneNeutralWarmCool() {
		Map<Player, Integer> scores = this.scoreUtils.calculateScores(Arrays.asList(this.players), true);

		assertEquals(58, (int) (scores.get(this.players[0])));
		assertEquals(65, (int) (scores.get(this.players[1])));
		assertEquals(20, (int) (scores.get(this.players[2])));
		assertEquals(28, (int) (scores.get(this.players[3])));

	}

	@Test
	public void testTwoSameNetWarm() {
		Map<Player, Integer> scores = this.scoreUtils.calculateScores(Arrays.asList(this.players), true);

		assertEquals(58, (int) (scores.get(this.players[0])));
		assertEquals(65, (int) (scores.get(this.players[1])));
		assertEquals(20, (int) (scores.get(this.players[2])));
		assertEquals(28, (int) (scores.get(this.players[3])));
		assertEquals(21, (int) (scores.get(this.players[4])));
	}

	@Test
	public void testTwoSameNetCool() {
		Map<Player, Integer> scores = this.scoreUtils.calculateScores(Arrays.asList(this.players), true);

		assertEquals(58, (int) (scores.get(this.players[0])));
		assertEquals(28, (int) (scores.get(this.players[3])));
		assertEquals(28, (int) (scores.get(this.players[5])));
	}

	public Card createCard(int points, Temperature temperature) {
		Card toReturn = new Card();
		toReturn.points = points;
		toReturn.temperature = temperature;
		return toReturn;
	}

}
