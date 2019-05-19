package underlings.card.effect.domestic;

import java.text.MessageFormat;
import underlings.card.effect.ElementEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

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
        return MessageFormat.format(LocaleWrap.get("collect_element_effect"), elements);
    }
}
