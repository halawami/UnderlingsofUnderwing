package underlings.card.effect.domestic;

import underlings.card.effect.PlayerEffect;
import underlings.player.Player;

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
