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
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;

    @Before
    public void init() {

        this.scoreUtils = new ScoreUtils();

        Card p1c1 = new Card();
        Card p1c2 = new Card();
        Card p1c3 = new Card();
        Card p1c4 = new Card();
        Card p1c5 = new Card();

        p1c1.points = 12;
        p1c2.points = 3;
        p1c3.points = 6;
        p1c4.points = 19;
        p1c5.points = 3;

        p1c1.temperature = Temperature.WARM;
        p1c2.temperature = Temperature.WARM;
        p1c3.temperature = Temperature.NEUTRAL;
        p1c4.temperature = Temperature.WARM;
        p1c5.temperature = Temperature.COOL;

        List<Card> p1c = new ArrayList<>();
        p1c.add(p1c1);
        p1c.add(p1c2);
        p1c.add(p1c3);
        p1c.add(p1c4);
        p1c.add(p1c5);

        this.player1 = new Player(6, new HandlerFactory(), 1);
        this.player1.hatchedCards = p1c;

        Card p2c1 = new Card();
        Card p2c2 = new Card();
        Card p2c3 = new Card();

        p2c1.points = 16;
        p2c2.points = 19;
        p2c3.points = 10;

        p2c1.temperature = Temperature.WARM;
        p2c2.temperature = Temperature.COOL;
        p2c3.temperature = Temperature.NEUTRAL;

        List<Card> p2c = new ArrayList<>();
        p2c.add(p2c1);
        p2c.add(p2c2);
        p2c.add(p2c3);

        this.player2 = new Player(6, new HandlerFactory(), 2);
        this.player2.hatchedCards = p2c;

        List<Card> p3c = new ArrayList<>();

        this.player3 = new Player(6, new HandlerFactory(), 3);
        this.player3.hatchedCards = p3c;

        Card p4c1 = new Card();
        Card p4c2 = new Card();

        p4c1.points = 3;
        p4c2.points = 10;

        p4c1.temperature = Temperature.COOL;
        p4c2.temperature = Temperature.COOL;

        List<Card> p4c = new ArrayList<>();
        p4c.add(p4c1);
        p4c.add(p4c2);

        this.player4 = new Player(6, new HandlerFactory(), 4);
        this.player4.hatchedCards = p4c;

        Card p5c1 = new Card();
        Card p5c2 = new Card();

        p5c1.points = 3;
        p5c2.points = 3;

        p5c1.temperature = Temperature.WARM;
        p5c2.temperature = Temperature.WARM;

        List<Card> p5c = new ArrayList<>();
        p5c.add(p5c1);
        p5c.add(p5c2);

        this.player5 = new Player(6, new HandlerFactory(), 5);
        this.player5.hatchedCards = p5c;

    }

    @Test
    public void testTwoPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(this.player1);
        players.add(this.player2);

        Map<Player, Integer> scores = this.scoreUtils.calculateScores(players);

        assertEquals(43, (int) (scores.get(this.player1)));
        assertEquals(45, (int) (scores.get(this.player2)));
    }

    @Test
    public void testOneEmpty() {
        List<Player> players = new ArrayList<>();
        players.add(this.player1);
        players.add(this.player2);
        players.add(this.player3);

        Map<Player, Integer> scores = this.scoreUtils.calculateScores(players);

        assertEquals(58, (int) (scores.get(this.player1)));
        assertEquals(65, (int) (scores.get(this.player2)));
        assertEquals(20, (int) (scores.get(this.player3)));

    }

    @Test
    public void testOneNeutralWarmCool() {
        List<Player> players = new ArrayList<>();
        players.add(this.player1);
        players.add(this.player2);
        players.add(this.player3);
        players.add(this.player4);

        Map<Player, Integer> scores = this.scoreUtils.calculateScores(players);

        assertEquals(58, (int) (scores.get(this.player1)));
        assertEquals(65, (int) (scores.get(this.player2)));
        assertEquals(20, (int) (scores.get(this.player3)));
        assertEquals(28, (int) (scores.get(this.player4)));

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

        assertEquals(58, (int) (scores.get(this.player1)));
        assertEquals(65, (int) (scores.get(this.player2)));
        assertEquals(20, (int) (scores.get(this.player3)));
        assertEquals(28, (int) (scores.get(this.player4)));
        assertEquals(21, (int) (scores.get(this.player5)));
    }

}
