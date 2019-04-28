package underlings.card.effect.domestic;

import underlings.card.effect.ElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.NullElement;
import underlings.player.Player;

public class CollectPrimaryElementEffect extends ElementEffect {

	@Override
	protected void apply(Player player, ElementBag elementBag) {
		Element element = elementBag.drawRandomPrimaryElement();

		if (element != NullElement.getInstance()) {
			player.addElement(element);
		}
	}
	
	@Override
	public String toString() {
		return "Collect a random primary element";
	}
}
