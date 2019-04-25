package underlings.card.effect.domestic;

import underlings.card.effect.ElementEffect;
import underlings.element.ElementBag;
import underlings.player.Player;

public class CollectPrimaryElementEffect extends ElementEffect {

    @Override
    protected void apply(Player player, ElementBag elementBag) {
        player.addElement(elementBag.drawRandomPrimaryElement());
    }
}
