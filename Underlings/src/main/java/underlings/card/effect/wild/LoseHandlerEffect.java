package underlings.card.effect.wild;

import underlings.card.effect.PlayerEffect;
import underlings.player.Player;

public class LoseHandlerEffect extends PlayerEffect {

    @Override
    protected void apply(Player player) {
        player.loseHandler();
    }
}
