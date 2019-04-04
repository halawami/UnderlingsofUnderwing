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
		Object[] options = elementGivers.toArray();
		int index = JOptionPane.showOptionDialog(null, "Select a drawing option", "Drawing Elements",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		return elementGivers.get(index);
	}

	@Override
	public ElementDrawChoice promptElementDrawChoice(List<ElementDrawChoice> elementDrawChoices) {
		Object[] options = elementDrawChoices.toArray();
		int index = JOptionPane.showOptionDialog(null, "Select a drawing option", "Drawing Elements",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		return elementDrawChoices.get(index);
	}

	@Override
	public HandlerState promptHandlerState(List<HandlerState> possibleStates) {
		Object[] options = possibleStates.toArray();
		int index = JOptionPane.showOptionDialog(null, "Select a phase", "Moving Handlers",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		return possibleStates.get(index);
	}

	@Override
	public Card promptCardSelection(List<Card> unclaimedEggs) {
		Object[] options = unclaimedEggs.toArray();
		int index = JOptionPane.showOptionDialog(null, "Select a card", "Moving Handlers",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		return unclaimedEggs.get(index);
	}

	@Override
	public Handler promptHandler(List<Handler> playersHandlers) {
		Object[] options = playersHandlers.toArray();
		int index = JOptionPane.showOptionDialog(null, "Select a handler", "Moving Handlers",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		return playersHandlers.get(index);
	}
}
