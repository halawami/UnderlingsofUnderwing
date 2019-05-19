package underlings.card.effect.domestic.players.player;

import underlings.card.effect.PlayerEffect;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class TwoHandlersOnFieldSpaceEffect extends PlayerEffect {

    @Override
    protected void apply(Player player) {
        player.maxHandlersOnSpace = 2;
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("two_handler_effect");
    }

}
