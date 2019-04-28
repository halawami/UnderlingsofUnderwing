package tests.scoring;

import static org.junit.Assert.assertEquals;

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
	private Player player1, player2, player3, player4, player5;
	
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
		
		List<Card> p3_c = new ArrayList<>();
		
		this.player3 = new Player(6, new HandlerFactory(), 3);
		this.player3.hatchedCards = p3_c;
		
		Card p4_c1 = new Card();
		Card p4_c2 = new Card();
		
		p4_c1.points = 3;
		p4_c2.points = 10;
		
		p4_c1.temperature = Temperature.COOL;
		p4_c2.temperature = Temperature.COOL;
		
		List<Card> p4_c = new ArrayList<>();
		p4_c.add(p4_c1);
		p4_c.add(p4_c2);
		
		this.player4 = new Player(6, new HandlerFactory(), 4);
		this.player4.hatchedCards = p4_c;
	
		Card p5_c1 = new Card();
		Card p5_c2 = new Card();
		
		p5_c1.points = 3;
		p5_c2.points = 3;
		
		p5_c1.temperature = Temperature.WARM;
		p5_c2.temperature = Temperature.WARM;
		
		List<Card> p5_c = new ArrayList<>();
		p5_c.add(p5_c1);
		p5_c.add(p5_c2);
		
		this.player5 = new Player(6, new HandlerFactory(), 5);
		this.player5.hatchedCards = p5_c;
		
	}

	@Test
	public void testTwoPlayers() {
		List<Player> players = new ArrayList<>();
		players.add(this.player1);
		players.add(this.player2);
		
		Map<Player, Integer> scores = this.scoreUtils.calculateScores(players);

		assertEquals(43, (int)(scores.get(this.player1)));
		assertEquals(45, (int)(scores.get(this.player2)));
	}
	
	@Test
	public void testOneEmpty() {
		List<Player> players = new ArrayList<>();
		players.add(this.player1);
		players.add(this.player2);
		players.add(this.player3);
		
		Map<Player, Integer> scores = this.scoreUtils.calculateScores(players);

		assertEquals(58, (int)(scores.get(this.player1)));
		assertEquals(65, (int)(scores.get(this.player2)));
		assertEquals(20, (int)(scores.get(this.player3)));
		
	}
	
	@Test
	public void testOneNeutralWarmCool() {
		List<Player> players = new ArrayList<>();
		players.add(this.player1);
		players.add(this.player2);
		players.add(this.player3);
		players.add(this.player4);
		
		Map<Player, Integer> scores = this.scoreUtils.calculateScores(players);

		assertEquals(58, (int)(scores.get(this.player1)));
		assertEquals(65, (int)(scores.get(this.player2)));
		assertEquals(20, (int)(scores.get(this.player3)));
		assertEquals(28, (int)(scores.get(this.player4)));

	}
	
	@Test
	public void testTwoSameNetWarm() {
		List<Player> players = new ArrayList<>();
		players.add(this.player1);
		players.add(this.player2);
		players.add(this.player3);
		players.add(this.player4);
		players.add(this.player5);
		
		Map<Player, Integer> scores = this.scoreUtils.calculateScores(players);

		assertEquals(58, (int)(scores.get(this.player1)));
		assertEquals(65, (int)(scores.get(this.player2)));
		assertEquals(20, (int)(scores.get(this.player3)));
		assertEquals(28, (int)(scores.get(this.player4)));
		assertEquals(21, (int)(scores.get(this.player5)));
	}
	
}
