package underlings.card.effect;

import underlings.element.ElementBag;
import underlings.player.Player;

public abstract class ElementEffect extends Effect {

    @Override
    protected abstract void apply(Player player, ElementBag elementBag);

}
