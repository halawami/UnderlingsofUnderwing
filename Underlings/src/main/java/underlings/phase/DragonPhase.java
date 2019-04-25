package underlings.phase;

import java.util.List;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.player.Player;

public class DragonPhase extends SequentialPhase {

	public DragonPhase(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod, Field field) {
		super(players, gui, elementBag, hatchingGround, displayMethod, field);
	}

	// pull complete eggs from hatching ground
	// return elements
	// re-populate hatching ground
	@Override
	public void setup() {
		List<Card> completeEggs = hatchingGround.pullAndReplaceCompleteEggs();
		for (Card completeEgg : completeEggs) {
			for (ElementSpace space : completeEgg.elementSpaces) {
				for (ElementColor color : space.elements) {
					elementBag.putElement(color);
				}
			}
		}
	}

	// hatch incubated eggs
	// send the handler to the ready room
	// run the positive effects
	@Override
	public void turn(Player player) {

	}

}
