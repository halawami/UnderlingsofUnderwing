package card.effect.domestic;

import card.effect.ElementEffect;
import element.Element;
import element.ElementBag;
import element.ElementColor;
import player.Player;

public class CollectElementEffect extends ElementEffect {

    public ElementColor[] elementChoices;

    @Override
    protected void apply(Player player, ElementBag elementBag) {
        Element element = elementBag.drawElementFromList(this.elementChoices);
        player.addElement(element);

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
