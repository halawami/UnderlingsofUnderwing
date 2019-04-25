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
	private Card centerCard;
	private ElementSpaceLogic elementSpaceLogic;

	public Effect on(HatchingGround hatchingGround){
		this.hatchingGround = hatchingGround;
		return this;
	}

	public Effect on(Card card){
		this.centerCard = card;
		return this;
	}

	public Effect on(ElementSpaceLogic elementSpaceLogic){
		this.elementSpaceLogic = elementSpaceLogic;
		return this;
	}

	public Effect on(ElementBag elementBag) {
		this.elementBag = elementBag;
		return this;
	}

	public void apply() {
		this.apply(this.centerCard, this.hatchingGround, this.elementBag, this.elementSpaceLogic);
	}

	protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic){
		throw new UnsupportedOperationException();
	}


}
