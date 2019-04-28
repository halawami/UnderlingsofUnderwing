package underlings.card.effect;

import underlings.player.Player;

public abstract class PlayerEffect extends Effect {

    @Override
    protected abstract void apply(Player player);

}
