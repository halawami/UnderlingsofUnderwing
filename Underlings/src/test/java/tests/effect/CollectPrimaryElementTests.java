package tests.effect;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.card.effect.domestic.CollectPrimaryElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;

public class CollectPrimaryElementTests {

	@Test
	public void testApply() {
		Player player = EasyMock.mock(Player.class);
		ElementBag elementBag = EasyMock.mock(ElementBag.class);
		Element element = new Element(ElementColor.BLUE);

		Effect gainPrimaryElement = new CollectPrimaryElementEffect();
		gainPrimaryElement.on(player).on(elementBag);

		EasyMock.expect(elementBag.drawRandomPrimaryElement()).andReturn(element);
		player.addElement(element);

		EasyMock.replay(player, elementBag);

		gainPrimaryElement.apply();
		EasyMock.verify(player, elementBag);
	}
	
	@Test
	public void testToString() {
		Effect gainPrimaryElement = new CollectPrimaryElementEffect();
		
		assertEquals("Collect a random primary element", gainPrimaryElement.toString());
	}

}
