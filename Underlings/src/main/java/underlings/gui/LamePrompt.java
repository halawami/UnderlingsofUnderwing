package underlings.gui;

import java.util.List;

import javax.swing.JOptionPane;

public class LamePrompt implements PromptHandler {

	@Override
	public int promptPlayerCount(int minPlayers, int maxPlayers) {
		// TODO: Input Validation
		return Integer.parseInt(JOptionPane.showInputDialog("How many players? [" + minPlayers + ", " + maxPlayers + "]"));
	}

	@Override
	public <T extends Choice> T promptChoice(String prompt, List<T> choices) {
		int index = this.displayOptions(choices.toArray(), "", prompt);
		return choices.get(index);
	}
	
	private int displayOptions(Object[] options, String title, String message) {
		return JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
	}

}
