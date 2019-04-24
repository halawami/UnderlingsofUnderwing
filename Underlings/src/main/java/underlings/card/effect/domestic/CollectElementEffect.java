package underlings.card.effect.domestic;

import underlings.card.effect.ElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;

public class CollectElementEffect extends ElementEffect {

    public ElementColor[] elementChoices;

    @Override
    public void apply(Player player, ElementBag elementBag) {
        Element element = elementBag.drawElementFromList(elementChoices);
        player.addElement(element);
    }
}
