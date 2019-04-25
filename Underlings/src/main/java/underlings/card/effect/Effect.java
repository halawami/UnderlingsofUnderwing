package underlings.card.effect;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.player.Player;

public abstract class Effect {

	private Player player;
	private HatchingGround hatchingGround;
	private ElementBag elementBag;

	public void apply() {

	}

	public void apply(Player player);

	public void apply(HatchingGround hatchingGround);

	public void apply(Player player, ElementBag elementBag);

	public abstract Effect on(HatchingGround mockHatchingGround);

	public abstract Effect on(Card card);

	public abstract void on(ElementSpaceLogic mockElementSpaceLogic);

}
