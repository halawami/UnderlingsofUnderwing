package tests.effect;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.CollectPrimaryElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;

public class ElementTests {

	@Test
	public void testGainPrimaryElement() {
		Player player = EasyMock.mock(Player.class);
		ElementBag elementBag = EasyMock.mock(ElementBag.class);
		Element element = new Element(ElementColor.BLUE);
		
		Effect gainPrimaryElement = new CollectPrimaryElementEffect();
		
		EasyMock.expect(elementBag.drawRandomPrimaryElement()).andReturn(element);
		player.addElement(element);
		
		EasyMock.replay(player, elementBag);
		
		gainPrimaryElement.apply(player, elementBag);
		EasyMock.verify(player, elementBag);
	}
	
}
