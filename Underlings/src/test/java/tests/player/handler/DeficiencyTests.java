package tests.player.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import underlings.game.HandlerFactory;
import underlings.player.Player;

public class DeficiencyTests {

	private HandlerFactory handlerFactory = new HandlerFactory();

	private Player player;

	@Before
	public void init() {
		this.player = new Player(6, new HandlerFactory());
	}

	@Test
	public void test12Points2Handlers() {
		this.player.addPoints(12);
		assertEquals(3, this.player.getHandlerCount());
	}

	@Test
	public void test12Points3Handlers() {
		this.addHandler(1);

		this.player.addPoints(12);
		assertEquals(3, this.player.getHandlerCount());
	}

	@Test
	public void test12PointsMaxHandlers() {
		this.addHandler(4);

		this.player.addPoints(12);
		assertEquals(6, this.player.getHandlerCount());
	}

	@Test
	public void test25PointsFrom12With2Handlers() {
		this.player.addPoints(12);
		this.player.loseHandler();

		this.player.addPoints(13);
		assertEquals(3, this.player.getHandlerCount());
	}

	@Test
	public void test25PointsFrom12With3Handlers() {
		this.player.addPoints(12);

		this.player.addPoints(13);
		assertEquals(4, this.player.getHandlerCount());
	}

	@Test
	public void test25PointsFrom12With4Handlers() {
		this.player.addPoints(12);
		this.addHandler(1);

		this.player.addPoints(13);
		assertEquals(4, this.player.getHandlerCount());
	}

	@Test
	public void test25PointsFrom12WithMaxHandlers() {
		this.player.addPoints(12);
		this.addHandler(3);

		this.player.addPoints(13);
		assertEquals(6, this.player.getHandlerCount());
	}

	@Test
	public void test25PointsFrom0With2Handlers() {
		this.player.addPoints(25);
		assertEquals(4, this.player.getHandlerCount());
	}

	@Test
	public void test25PointsFrom0With3Handlers() {
		this.addHandler(1);
		
		this.player.addPoints(25);
		assertEquals(4, this.player.getHandlerCount());
	}

	@Test
	public void test25PointsFrom0With4Handlers() {
		this.addHandler(2);

		this.player.addPoints(25);
		assertEquals(4, this.player.getHandlerCount());
	}

	@Test
	public void test25PointsFrom0WithMaxHandlers() {
		this.addHandler(4);

		this.player.addPoints(25);
		assertEquals(6, this.player.getHandlerCount());
	}
	
	private void addHandler(int amt) {
		for (int i = 0; i < amt; i++) {
			this.player.addHandler();
		}
	}

}
