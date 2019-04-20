package underlings.card.effect;

import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.player.Player;

public abstract class PlayerEffect implements Effect {

	@Override
	public void apply(HatchingGround hatchingGround) {
		throw new UnsupportedOperationException();
	}
	
	@Override 
	public void apply(Player player, ElementBag elementBag) {
		throw new UnsupportedOperationException();
	}
	
}
