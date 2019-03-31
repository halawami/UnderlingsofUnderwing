package tests.game;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import underlings.game.Handler;
import underlings.player.Player;

public class PlayerTests {

	@Test
	public void testInitialHandlerCount() {
		Player player = new Player(4);
		assertEquals(2, player.getHandlerCount());
	}

	@Test
	public void testAddHandlerAfterInit() {
		Player player = new Player(4);
		player.addHandler();
		assertEquals(3, player.getHandlerCount());
	}

	@Test
	public void testGetHandlerListSize() {
		Player player = new Player(4);
		player.addHandler();
		List<Handler> handlers = player.getHandlers();
		assertEquals(handlers.size(), player.getHandlerCount());
	}

	@Test
	public void testAddHandlerMaxSize6() {
		Player player = new Player(6);
		player.addHandler();
		player.addHandler();
		player.addHandler();
		player.addHandler();
		assertEquals(6, player.getHandlerCount());
		player.addHandler();
		assertEquals(6, player.getHandlerCount());
	}
	
	@Test
	public void testAddHandlerMaxSize4(){
		Player player = new Player(4);
		player.addHandler();
		player.addHandler();
		assertEquals(4, player.getHandlerCount());
		player.addHandler();
		assertEquals(4, player.getHandlerCount());
	}
	
	@Test
	public void testReach12PointsWith2Handlers(){
		Player player = new Player(4);
		player.addPoints(12);
		assertEquals(3, player.getHandlerCount());
	}
	
	@Test
	public void testReach12PointsWith3Handlers(){
		Player player = new Player(4);
		player.addHandler();
		player.addPoints(12);
		assertEquals(3, player.getHandlerCount());
	}
	
	@Test
	public void testReach25PointsWith3Handlers(){
		Player player = new Player(4);
		player.addHandler();
		player.addPoints(25);
		assertEquals(4, player.getHandlerCount());
	}
	
	@Test
	public void testReach25PointsWith4Handlers(){
		Player player = new Player(4);
		player.addHandler();
		player.addHandler();
		player.addPoints(25);
		assertEquals(4, player.getHandlerCount());
	}
	
	// is this correct behavior? or do they get 2 handlers: 
	// yes they should
	@Test 
	public void testReach25PointsFromBelow12Points(){
		Player player = new Player(4);
		player.addPoints(25);
		assertEquals(4, player.getHandlerCount());
	}
	
	@Test 
	public void testLoseHandlerAbove12Points(){
		Player player = new Player(4);
		player.addPoints(13);
		player.loseHandler();
		assertEquals(2, player.getHandlerCount());
	}
	
	@Test 
	public void testLoseHandlerAtMinHandlers(){
		Player player = new Player(4);
		player.loseHandler();
		assertEquals(2, player.getHandlerCount());
	}

	// is this correct behavior?
	@Test 
	public void testReach12PointsAfterLosingAHandler(){
		Player player = new Player(4);
		player.addPoints(13);
		player.loseHandler();
		player.addPoints(1);
		assertEquals(2, player.getHandlerCount());
	}

}
