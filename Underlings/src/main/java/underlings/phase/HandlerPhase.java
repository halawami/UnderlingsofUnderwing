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

public class HandlerPhase implements Phase {

	@Override
	public void execute(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod) {

		Map<Player, List<Handler>> unmovedHandlers = new HashMap<>();

		for (Player player : players) {
			unmovedHandlers.put(player, new ArrayList<>(player.getHandlers()));
		}

		boolean allHandlersPlayed = false;

		while (!allHandlersPlayed) {
			allHandlersPlayed = true;

			for (Player player : players) {

				List<Handler> playersHandlers = unmovedHandlers.get(player);

				if (!playersHandlers.isEmpty()) {
					allHandlersPlayed = false;

					Handler chosenHandler = gui.promptHandler.promptChoice("Choose a Handler", playersHandlers);

					List<HandlerChoice> possibleChoices = chosenHandler.getPossibleChoices();

					HandlerChoice chosenChoice = gui.promptHandler
							.promptChoice("Choose a movement for " + chosenHandler, possibleChoices);

					if (chosenChoice != HandlerChoice.STAY) {
						if (chosenChoice == HandlerChoice.CARD) {
							Card chosenCard = gui.promptHandler.promptChoice("Choose a card", hatchingGround.getUnclaimedEggs());
							chosenCard.handler = chosenHandler;
							chosenHandler.setLocation(chosenCard.name);
						} else if (chosenChoice == HandlerChoice.FIELD) {

						} if (chosenChoice == HandlerChoice.FIELD_WHITESPACE) {
							
						}
						chosenHandler.moveToState(chosenChoice.getState());
					}

					playersHandlers.remove(chosenHandler);
					displayMethod.run();

				}

			}
		}

	}

}
