package underlings.card.effect.domestic.players.player;

import underlings.card.effect.PlayerEffect;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class TwoHandlersOnFieldSpaceEffect extends PlayerEffect {

    @Override
    protected void apply(Player player) {
        player.maxHandlersOnSpace = 2;
    }

    @Override
    public String toString() {
        return LocaleWrap.get("two_handler_effect");
    }

}
