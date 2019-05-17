package underlings.card.effect.domestic;

import underlings.card.effect.PlayerEffect;
import underlings.player.Player;

public class TwoHandlersOnFieldSpaceEffect extends PlayerEffect {

    @Override
    protected void apply(Player player) {
        player.maxHandlersOnSpace = 2;
    }

}
