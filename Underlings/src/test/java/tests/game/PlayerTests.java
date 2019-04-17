package tests.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.game.Handler;
import underlings.game.HandlerFactory;
import underlings.gui.ElementGiver;
import underlings.player.Player;

public class PlayerTests {
	private HandlerFactory handlerFactory = new HandlerFactory();

	@Test
	public void testInitialHandlerCount() {
		Player player = new Player(4, this.handlerFactory);
		assertEquals(2, player.getHandlerCount());
	}

	@Test
	public void testAddHandlerAfterInit() {
		Player player = new Player(4, this.handlerFactory);
		player.gainHandler();
		assertEquals(3, player.getHandlerCount());
	}

	@Test
	public void testGetHandlerListSize() {
		Player player = new Player(4, this.handlerFactory);
		player.gainHandler();
		List<Handler> handlers = player.getHandlers();
		assertEquals(handlers.size(), player.getHandlerCount());
	}

	@Test
	public void testAddHandlerMaxSize6() {
		Player player = new Player(6, this.handlerFactory);
		player.gainHandler();
		player.gainHandler();
		player.gainHandler();
		player.gainHandler();
		assertEquals(6, player.getHandlerCount());
		player.gainHandler();
		assertEquals(6, player.getHandlerCount());
	}
	
	@Test
	public void testAddHandlerMaxSize4(){
		Player player = new Player(4, this.handlerFactory);
		player.gainHandler();
		player.gainHandler();
		assertEquals(4, player.getHandlerCount());
		player.gainHandler();
		assertEquals(4, player.getHandlerCount());
	}
	

	
	@Test
	public void testReach25PointsWith3Handlers(){
		Player player = new Player(4, this.handlerFactory);
		player.addPoints(13);
		player.gainHandler();
		player.addPoints(13);
		assertEquals(4, player.getHandlerCount());
	}
	
	@Test
	public void testReach25PointsWith4Handlers(){
		Player player = new Player(4, this.handlerFactory);
		player.gainHandler();
		player.gainHandler();
		player.addPoints(25);
		assertEquals(4, player.getHandlerCount());
	}
	
	@Test 
	public void testReach25PointsFromBelow12Points(){
		Player player = new Player(4, this.handlerFactory);
		player.addPoints(25);
		assertEquals(4, player.getHandlerCount());
	}
	
	@Test 
	public void testLoseHandlerAbove12Points(){
		Player player = new Player(4, this.handlerFactory);
		player.addPoints(13);
		player.loseHandler();
		assertEquals(2, player.getHandlerCount());
	}
	
	@Test 
	public void testLoseHandlerAtMinHandlers(){
		Player player = new Player(4, this.handlerFactory);
		player.loseHandler();
		assertEquals(2, player.getHandlerCount());
	}

	// is this correct behavior?
	@Test 
	public void testReach12PointsAfterLosingAHandler(){
		Player player = new Player(4, this.handlerFactory);
		player.addPoints(13);
		player.loseHandler();
		player.addPoints(1);
		assertEquals(2, player.getHandlerCount());
	}
	
	@Test
	public void testAddElement() {
		Player player = new Player(4, this.handlerFactory);
		Element elementToAdd = new Element(ElementColor.BLUE);
		player.addElement(elementToAdd);
		assertTrue(player.getElements().contains(elementToAdd));
	}
	
	@Test
	public void testNewPlayerElementGivers() {
		Player player = new Player(4, this.handlerFactory);
		List<ElementGiver> elementGivers = player.getElementGivers();
		assertEquals(2, elementGivers.size());
		for (ElementGiver elementGiver : elementGivers) {
			assertEquals(1, elementGiver.getElementDrawChoices().size());
			assertTrue(elementGiver.getElementDrawChoices().get(0).isRandom());
		}
	}
	
	@Test
	public void testPlayerElementGiversAddHandler() {
		Player player = new Player(4, this.handlerFactory);
		player.gainHandler();
		List<ElementGiver> elementGivers = player.getElementGivers();
		assertEquals(3, elementGivers.size());
		for (ElementGiver elementGiver : elementGivers) {
			assertEquals(1, elementGiver.getElementDrawChoices().size());
			assertTrue(elementGiver.getElementDrawChoices().get(0).isRandom());
		}
	}
	
	@Test
	public void testAddPointsAllFalse(){
		Player player = new Player(2, this.handlerFactory);
		player.addPoints(5);
		assertEquals(2, player.getHandlerCount());
	}
	
	@Test
	public void testAddPointsOtherTest(){
		Player player = new Player(2, this.handlerFactory);
		player.addPoints(25);
		player.addPoints(1);
		assertEquals(4, player.getHandlerCount());
	}
	
	@Test
	public void testAddPointsTFFOtherTest(){
		Player player = new Player(2, this.handlerFactory);
		player.addPoints(25);
		player.loseHandler();
		player.addPoints(1);
		assertEquals(3, player.getHandlerCount());
	}
	
}
