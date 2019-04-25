package underlings.card.effect.domestic;

import underlings.card.effect.ElementEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.player.Player;

public class CollectElementEffect extends ElementEffect {

    public ElementColor[] elementChoices;


    @Override
    protected void apply(Player player, ElementBag elementBag) {
        player.addElement(elementBag.drawElementFromList(elementChoices[0]));
    }
}
