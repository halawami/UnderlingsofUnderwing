package tests.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.game.HandlerFactory;
import underlings.player.Player;

public class AddHandlerTests {

	private HandlerFactory handlerFactory = new HandlerFactory();
	
	@Test
	public void test12Points2Handlers() {
		Player player = new Player(6, this.handlerFactory);
		
		player.addPoints(12);
		assertEquals(3, player.getHandlerCount());
	}
	
	@Test
	public void test12Points3Handlers() {
		Player player = new Player(6, this.handlerFactory);
		player.addHandler();
		
		player.addPoints(12);
		assertEquals(3, player.getHandlerCount());
	}
	
	@Test
	public void test12PointsMaxHandlers() {
		Player player = new Player(6, this.handlerFactory);
		player.addHandler();
		player.addHandler();
		player.addHandler();
		player.addHandler();
		
		player.addPoints(12);
		assertEquals(6, player.getHandlerCount());
	}
	
	@Test
	public void test25PointsFrom12With2Handlers() {
		Player player = new Player(6, this.handlerFactory);
		player.addPoints(12);
		player.loseHandler();
		
		player.addPoints(13);
		assertEquals(3, player.getHandlerCount());
	}
	
	@Test
	public void test25PointsFrom12With3Handlers() {
		Player player = new Player(6, this.handlerFactory);
		player.addPoints(12);
		
		player.addPoints(13);
		assertEquals(4, player.getHandlerCount());
	}
	
	@Test
	public void test25PointsFrom12With4Handlers() {
		Player player = new Player(6, this.handlerFactory);
		player.addPoints(12);
		player.addHandler();
		
		player.addPoints(13);
		assertEquals(4, player.getHandlerCount());
	}
	
	@Test
	public void test25PointsFrom12WithMaxHandlers() {
		Player player = new Player(6, this.handlerFactory);
		player.addPoints(12);
		player.addHandler();
		player.addHandler();
		player.addHandler();
		
		player.addPoints(13);
		assertEquals(6, player.getHandlerCount());
	}
	
	@Test
	public void test25PointsFrom0With2Handlers() {
		Player player = new Player(6, this.handlerFactory);
		
		player.addPoints(25);
		assertEquals(4, player.getHandlerCount());
	}
	
	@Test
	public void test25PointsFrom0With3Handlers() {
		Player player = new Player(6, this.handlerFactory);
		player.addHandler();
		
		player.addPoints(25);
		assertEquals(4, player.getHandlerCount());
	}
	
	@Test
	public void test25PointsFrom0With4Handlers() {
		Player player = new Player(6, this.handlerFactory);
		player.addHandler();
		player.addHandler();
		
		player.addPoints(25);
		assertEquals(4, player.getHandlerCount());
	}
	
	@Test
	public void test25PointsFrom0WithMaxHandlers() {
		Player player = new Player(6, this.handlerFactory);
		player.addHandler();
		player.addHandler();
		player.addHandler();
		player.addHandler();
		
		player.addPoints(25);
		assertEquals(6, player.getHandlerCount());
	}
	
}
