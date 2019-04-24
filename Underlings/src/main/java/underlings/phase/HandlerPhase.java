package underlings.phase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.player.Player;

public class HandlerPhase extends RotationPhase {

	public HandlerPhase(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod) {
		super(players, gui, elementBag, hatchingGround, displayMethod);
	}

	private Map<Player, List<Handler>> unmovedHandlers;

	@Override
	public void setup() {
		this.unmovedHandlers = new HashMap<>();

		for (Player player : this.players) {
			this.unmovedHandlers.put(player, new ArrayList<>(player.getHandlers()));
		}
	}

	@Override
	public void turn(Player player) {

		List<Handler> playersHandlers = this.unmovedHandlers.get(player);

		if (!playersHandlers.isEmpty()) {
			this.phaseComplete = false;

			Handler chosenHandler = this.gui.promptHandler.promptChoice("Choose a Handler", playersHandlers);

			List<HandlerChoice> possibleChoices = chosenHandler.getPossibleChoices();

			HandlerChoice chosenChoice = this.gui.promptHandler.promptChoice("Choose a movement for " + chosenHandler,
					possibleChoices);

			if (chosenChoice != HandlerChoice.STAY) {
				if (chosenChoice == HandlerChoice.CARD) {
					Card chosenCard = this.gui.promptHandler.promptChoice("Choose a card",
							this.hatchingGround.getUnclaimedEggs());
					chosenCard.handler = chosenHandler;
					chosenHandler.setLocation(chosenCard.name);
				} else if (chosenChoice == HandlerChoice.FIELD) {

				}
				if (chosenChoice == HandlerChoice.FIELD_WHITESPACE) {

				}
				chosenHandler.moveToState(chosenChoice.getState());
			}

			playersHandlers.remove(chosenHandler);
			this.displayMethod.run();

		}

	}

}
