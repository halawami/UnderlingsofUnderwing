package underlings.gui;

import javax.swing.JOptionPane;

public class LamePrompt implements PromptHandler {

	@Override
	public int promptPlayerCount() {
		return Integer.parseInt(JOptionPane.showInputDialog("How many players? [2,6]"));
	}
}
