package underlings.card.effect.wild.adjacenteggs;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public abstract class AdjacentEggsEffect extends HatchingGroundEffect {

	@Override
	protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
			ElementSpaceLogic elementSpaceLogic, Gui gui, Player currentPlayer, EggHatchingLogic eggHatchingLogic) {
		List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
		for (Card adjacentCard : adjacentCards) {
			System.out.println("adjacent card " + adjacentCard);
			System.out.println("hatching gruond " + hatchingGround);
			System.out.println("element bag" + elementBag);
			System.out.println("space logic" + elementSpaceLogic);
			System.out.println(" gui  " + gui);
			System.out.println(" player " + currentPlayer);
			System.out.println("hatching logic " + eggHatchingLogic);
			applyOnAdjacentEgg(adjacentCard, elementBag, currentPlayer.elementSpaceLogic, hatchingGround, gui,
					eggHatchingLogic);
		}
	}

	public abstract void applyOnAdjacentEgg(Card adjacentEgg, ElementBag elementBag,
			ElementSpaceLogic elementSpaceLogic, HatchingGround hatchingGround, Gui gui,
			EggHatchingLogic eggHatchingLogic);
}
