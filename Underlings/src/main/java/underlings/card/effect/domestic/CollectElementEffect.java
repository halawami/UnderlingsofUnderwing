package underlings.card.effect.domestic;

import underlings.card.effect.ElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.NullElement;
import underlings.player.Player;

public class CollectElementEffect extends ElementEffect {

	public ElementColor[] elementChoices;

	@Override
	protected void apply(Player player, ElementBag elementBag) {

		Element element = elementBag.drawElementFromList(this.elementChoices);

		if (element != NullElement.getInstance()) {
			player.addElement(element);
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder elements = new StringBuilder();
		for (ElementColor color : this.elementChoices) {
			elements.append(color);
			elements.append(" ");
		}
		return "Collect one of the following elements randomly: [ " + elements + "]";
	}
}
