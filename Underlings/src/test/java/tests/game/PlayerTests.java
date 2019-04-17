package tests.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementGiver;
import underlings.game.HandlerFactory;
import underlings.player.Player;

public class PlayerTests {
	private HandlerFactory handlerFactory = new HandlerFactory();

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
		
}
