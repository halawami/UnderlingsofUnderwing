package underlings.card.effect.domestic;

import underlings.card.effect.ElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.player.Player;

public class GainPrimaryElementEffect extends ElementEffect {

	@Override
	public void apply(Player player, ElementBag elementBag) {
		Element element = elementBag.drawRandomPrimaryElement();
		player.addElement(element);
	}

}
