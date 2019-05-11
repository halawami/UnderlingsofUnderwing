package underlings.utilities;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.player.Player;

public class EggHatchingLogic {

	private Gui gui;
	private ElementBag elementBag;
	private HatchingGround hatchingGround;

	public EggHatchingLogic(Gui gui, ElementBag elementBag, HatchingGround hatchingGround) {
		this.gui = gui;
		this.elementBag = elementBag;
		this.hatchingGround = hatchingGround;
	}

	public void hatchEgg(Card card, boolean domestic, Player player) {
		Effect[] effects;
		if (domestic) {
			card.handler = WildHandler.getInstance();
			effects = card.wildEffects;
			this.returnElementsToBag(card);
		} else {
			effects = card.domesticEffects;
			card.handler.moveToState(HandlerState.READY_ROOM);
			player.hatchedCards.add(card);
		}
		for (int i = 0; i < effects.length; i++) {
			effects[i].on(card).on(this.elementBag).on(this.hatchingGround).on(player).on(this.gui).on(this).apply();
			this.gui.notifyAction(player.getPlayerId(), effects[i].toString() + " has been applied");
		}
	}

	public void returnElementsToBag(Card dragon) {
		for (ElementSpace space : dragon.elementSpaces) {
			for (ElementColor color : space.elements) {
				this.elementBag.putElement(color);
			}
		}
	}
}
