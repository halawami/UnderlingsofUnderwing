package underlings.gui;

import java.util.List;

import javax.swing.JOptionPane;

import underlings.game.Card;
import underlings.game.Handler;
import underlings.game.HandlerState;

public class LamePrompt implements PromptHandler {

	@Override
	public int promptPlayerCount() {
		return Integer.parseInt(JOptionPane.showInputDialog("How many players? [2,6]"));
	}

	@Override
	public ElementGiver promptElementGiver(List<ElementGiver> elementGivers) {
		int index = displayOptions(elementGivers.toArray(), "Drawing Elements", "Select a drawing option");
		return elementGivers.get(index);
	}

	@Override
	public ElementDrawChoice promptElementDrawChoice(List<ElementDrawChoice> elementDrawChoices) {
		int index = displayOptions(elementDrawChoices.toArray(), "Drawing Elements", "Select a drawing option");
		return elementDrawChoices.get(index);
	}

	@Override
	public HandlerState promptHandlerState(List<HandlerState> possibleStates) {
		int index = displayOptions(possibleStates.toArray(), "Moving Handlers", "Select a state");
		return possibleStates.get(index);
	}

	@Override
	public Card promptCardSelection(List<Card> unclaimedEggs) {
		int index = displayOptions(unclaimedEggs.toArray(), "Moving Handlers", "Select a card");
		return unclaimedEggs.get(index);
	}

	@Override
	public Handler promptHandler(List<Handler> playersHandlers) {
		int index = displayOptions(playersHandlers.toArray(), "Moving Handlers", "Select a handler");
		return playersHandlers.get(index);
	}

	private int displayOptions(Object[] options, String title, String message) {
		return JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
	}
}
