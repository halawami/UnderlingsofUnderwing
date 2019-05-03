package card.effect.domestic;

import card.effect.PlayerEffect;
import player.Player;

public class GainOneHandlerEffect extends PlayerEffect {

    @Override
    protected void apply(Player player) {
        player.gainHandler();
    }

    @Override
    public String toString() {
        return "Gain one handler";
    }

}
