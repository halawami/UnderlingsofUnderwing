package underlings.gui;

import java.util.List;

import underlings.element.ElementGiver;
import underlings.card.Card;
import underlings.game.Handler;
import underlings.game.HandlerState;

public interface PromptHandler {
	
	int promptPlayerCount(int minPlayers, int maxPlayers);
	
	ElementGiver promptElementGiver(List<ElementGiver> elementGivers);
	
	DrawChoice promptElementDrawChoice(List<DrawChoice> possibleElementDrawChoices);

	HandlerState promptHandlerState(List<HandlerState> possibleStates);

	Card promptCardSelection(List<Card> unclaimedEggs);

	Handler promptHandler(List<Handler> playersHandlers);
		
}