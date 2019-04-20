package underlings.card.effect.domestic;

import underlings.card.effect.PlayerEffect;
import underlings.player.Player;

public class GainOneHandlerEffect extends PlayerEffect {

	@Override
	public void apply(Player player) {
		player.gainHandler();
	}

}
