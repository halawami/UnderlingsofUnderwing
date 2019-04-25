package tests.scoring;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.handler.HandlerFactory;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public class ScoreTests {
	
	private ScoreUtils scoreUtils;
	private Player player1, player2, player3, player4, player5, player6;
	
	@Before
	public void init() {
		
		this.scoreUtils = new ScoreUtils();
		
		Card p1_c1 = new Card();
		Card p1_c2 = new Card();
		Card p1_c3 = new Card();
		Card p1_c4 = new Card();
		Card p1_c5 = new Card();
		
		p1_c1.points = 12;
		p1_c2.points = 3;
		p1_c3.points = 6;
		p1_c4.points = 19;
		p1_c5.points = 3;
		
		p1_c1.temperature = Temperature.WARM;
		p1_c2.temperature = Temperature.WARM;
		p1_c3.temperature = Temperature.NEUTRAL;
		p1_c4.temperature = Temperature.WARM;
		p1_c5.temperature = Temperature.COOL;
		
		List<Card> p1_c = new ArrayList<>();
		p1_c.add(p1_c1);
		p1_c.add(p1_c2);
		p1_c.add(p1_c3);
		p1_c.add(p1_c4);
		p1_c.add(p1_c5);
		
		this.player1 = new Player(6, new HandlerFactory(), 1);
		this.player1.hatchedCards = p1_c;
		
		Card p2_c1 = new Card();
		Card p2_c2 = new Card();
		Card p2_c3 = new Card();
		
		p2_c1.points = 16;
		p2_c2.points = 19;
		p2_c3.points = 10;
		
		p2_c1.temperature = Temperature.WARM;
		p2_c2.temperature = Temperature.COOL;
		p2_c3.temperature = Temperature.NEUTRAL;
		
		List<Card> p2_c = new ArrayList<>();
		p2_c.add(p2_c1);
		p2_c.add(p2_c2);
		p2_c.add(p2_c3);
		
		this.player2 = new Player(6, new HandlerFactory(), 2);
		this.player2.hatchedCards = p2_c;
	
	}

	@Test
	public void testTwoPlayers() {
		List<Player> players = new ArrayList<>();
		players.add(this.player1);
		players.add(this.player2);
		
		Map<Player, Integer> scores = this.scoreUtils.calculateScores(players);

		assertTrue(43 == scores.get(this.player1));
		assertTrue(45 == scores.get(this.player2));
	}
	
}
