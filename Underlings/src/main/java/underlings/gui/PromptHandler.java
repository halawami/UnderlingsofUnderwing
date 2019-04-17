package underlings.gui;

import java.util.List;

import underlings.element.ElementGiver;
import underlings.game.Card;
import underlings.game.Handler;
import underlings.game.HandlerState;

public interface PromptHandler {
	
	public int promptPlayerCount(int minPlayers, int maxPlayers);
	
	public ElementGiver promptElementGiver(List<ElementGiver> elementGivers);
	
	public ElementDrawChoice promptElementDrawChoice(List<ElementDrawChoice> elementDrawChoices);

	public HandlerState promptHandlerState(List<HandlerState> possibleStates);

	public Card promptCardSelection(List<Card> unclaimedEggs);

	public Handler promptHandler(List<Handler> playersHandlers);
		
}