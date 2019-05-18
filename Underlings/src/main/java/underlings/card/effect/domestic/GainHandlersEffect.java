package underlings.card.effect.domestic;

import underlings.card.effect.PlayerEffect;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class GainHandlersEffect extends PlayerEffect {

    public int numberOfHandlers;

    @Override
    protected void apply(Player player) {
        for (int i = 0; i < this.numberOfHandlers; i++) {
            player.gainHandler();
        }
    }

    @Override
    public String toString() {
        return LocaleWrap.format("gain_handler_effect", numberOfHandlers);
    }

}
