package underlings.card.effect.domestic;

import underlings.card.effect.ElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;

public class CollectFiveElementsEffect extends ElementEffect {

    public ElementColor[] elementColors;

    @Override
    protected void apply(Player player, ElementBag elementBag) {
        for (ElementColor elementColor : this.elementColors) {
            Element element = elementBag.drawElementFromList(elementColor);
            player.addElement(element);
        }
    }
}
