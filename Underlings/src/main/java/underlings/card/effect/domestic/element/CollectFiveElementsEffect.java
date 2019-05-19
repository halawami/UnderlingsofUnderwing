package underlings.card.effect.domestic.element;

import underlings.card.effect.ElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class CollectFiveElementsEffect extends ElementEffect {

    public ElementColor[] elementColors;

    @Override
    protected void apply(Player player, ElementBag elementBag) {
        for (ElementColor elementColor : this.elementColors) {
            Element element = elementBag.drawElementFromList(elementColor);
            player.addElement(element);
        }
    }

    @Override
    public String toString() {
        StringBuilder elements = new StringBuilder();
        for (ElementColor color : this.elementColors) {
            elements.append(color);
            elements.append(" ");
        }
        return LocaleWrap.format("collect_five_element_effect", elements);
    }
}
