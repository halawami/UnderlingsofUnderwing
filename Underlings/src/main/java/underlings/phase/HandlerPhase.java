package underlings.phase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import underlings.element.ElementBag;
import underlings.game.Card;
import underlings.game.Handler;
import underlings.game.HandlerState;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
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
					
					Handler chosenHandler = gui.promptHandler.promptHandler(playersHandlers);
					
					List<HandlerState> possibleStates = chosenHandler.getPossibleStates();
					
					HandlerState chosenState = gui.promptHandler.promptHandlerState(possibleStates);
					
					List<Card> unclaimedEggs = hatchingGround.getUnclaimedEggs();
					
					Card chosenCard = gui.promptHandler.promptCardSelection(unclaimedEggs);
					
					chosenHandler.moveToState(chosenState); // TODO: Refactor into setHandler
					chosenCard.setHandler(chosenHandler);
					
					playersHandlers.remove(chosenHandler);
					displayMethod.run();
					
				}
			
			}
		}

	}

}
