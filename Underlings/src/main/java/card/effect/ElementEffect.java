package card.effect;

import element.ElementBag;
import player.Player;

public abstract class ElementEffect extends Effect {

    @Override
    protected abstract void apply(Player player, ElementBag elementBag);

}
