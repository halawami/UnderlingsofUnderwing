package underlings.card.effect.domestic;

import underlings.card.effect.PlayerEffect;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class GainOneHandlerEffect extends PlayerEffect {

    @Override
    protected void apply(Player player) {
        player.gainHandler();
    }

    @Override
    public String toString() {
        return LocaleWrap.get("gain_one_handler_effect");
    }

}
