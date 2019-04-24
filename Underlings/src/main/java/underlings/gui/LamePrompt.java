package underlings.gui;

import java.util.List;

import javax.swing.JOptionPane;

import underlings.element.ElementGiver;
import underlings.card.Card;
import underlings.handler.Handler;
import underlings.handler.HandlerState;

public class LamePrompt implements PromptHandler {

	@Override
	public int promptPlayerCount(int minPlayers, int maxPlayers) {
		// TODO: Input Validation
		return Integer.parseInt(JOptionPane.showInputDialog("How many players? [" + minPlayers + ", " + maxPlayers + "]"));
	}

	@Override
	public ElementGiver promptElementGiver(List<ElementGiver> elementGivers) {
		int index = this.displayOptions(elementGivers.toArray(), "Drawing Elements", "Select a drawing option");
		return elementGivers.get(index);
	}

	@Override
	public DrawChoice promptElementDrawChoice(List<DrawChoice> elementDrawChoices) {
		int index = this.displayOptions(elementDrawChoices.toArray(), "Drawing Elements", "Select a drawing option");
		return elementDrawChoices.get(index);
	}

	@Override
	public HandlerState promptHandlerState(List<HandlerState> possibleStates) {
		int index = this.displayOptions(possibleStates.toArray(), "Moving Handlers", "Select a state");
		return possibleStates.get(index);
	}

	@Override
	public Card promptCardSelection(List<Card> unclaimedEggs) {
		int index = this.displayOptions(unclaimedEggs.toArray(), "Moving Handlers", "Select a card");
		return unclaimedEggs.get(index);
	}

	@Override
	public Handler promptHandler(List<Handler> playersHandlers) {
		int index = this.displayOptions(playersHandlers.toArray(), "Moving Handlers", "Select a handler");
		return playersHandlers.get(index);
	}

	private int displayOptions(Object[] options, String title, String message) {
		return JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
	}
}
