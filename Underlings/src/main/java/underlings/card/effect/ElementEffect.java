package underlings.card.effect;

import underlings.game.HatchingGround;
import underlings.player.Player;

public abstract class ElementEffect implements Effect {

	@Override
	public void apply(Player player) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void apply(HatchingGround hatchingGround) {
		throw new UnsupportedOperationException();
	}

}
