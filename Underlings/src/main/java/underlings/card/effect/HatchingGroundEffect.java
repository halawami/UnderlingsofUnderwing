package underlings.card.effect;

import underlings.element.ElementBag;
import underlings.player.Player;

public abstract class HatchingGroundEffect implements Effect {

	@Override
	public void apply(Player player) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void apply(Player player, ElementBag elementBag) {
		throw new UnsupportedOperationException();
	}
	
}
