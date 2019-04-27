package underlings.phase;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.handler.HandlerState;
import underlings.player.Player;

import java.util.List;

public class DragonPhase extends SequentialPhase {
	
	private List<Card> completeEggs;

	public DragonPhase(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod, Field field) {
		super(players, gui, elementBag, hatchingGround, displayMethod, field);
	}

	@Override
	public void setup() {
		this.completeEggs = this.hatchingGround.pullAndReplaceCompleteEggs();
		for (Card completeEgg : this.completeEggs) {
			for (ElementSpace space : completeEgg.elementSpaces) {
				for (ElementColor color : space.elements) {
					this.elementBag.putElement(color);
				}
			}
		}
	}

	// hatch incubated eggs
	// send the handler to the ready room
	// run the positive effects
	@Override
	public void turn(Player player) {
		for(Card unhatchedEgg : player.unhatchedCards) {
			unhatchedEgg.handler.moveToState(HandlerState.READY_ROOM);
			for(int i = 0; i < unhatchedEgg.domesticEffects.length; i++) {
				unhatchedEgg.domesticEffects[i].on(player).apply();
			}
			player.hatchedCards.add(unhatchedEgg);
		}
		player.unhatchedCards.clear();
		for(Card completeCard : this.completeEggs){
			if (player.getHandlers().contains(completeCard.handler)){
				player.unhatchedCards.add(completeCard);
				completeCard.handler.moveToState(HandlerState.INCUBATION);
			}
		}
		this.phaseComplete = true;
	}

}
