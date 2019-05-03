package card.effect;

import player.Player;

public abstract class PlayerEffect extends Effect {

    @Override
    protected abstract void apply(Player player);

}
