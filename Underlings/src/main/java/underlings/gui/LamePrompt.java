package underlings.gui;

import java.util.List;

import javax.swing.JOptionPane;

public class LamePrompt implements PromptHandler {

	@Override
	public <T extends Choice> T promptChoice(String prompt, List<T> choices, int playerId) {
		int index = this.displayOptions(choices.toArray(), "Player "+playerId, prompt);
		return choices.get(index);
	}
	
	private int displayOptions(Object[] options, String title, String message) {
		return JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
	}

	@Override
	public boolean promptDecision(String question, int playerId) {
		int option = JOptionPane.showConfirmDialog(null, question, "Player "+playerId, JOptionPane.YES_NO_OPTION);
		return option == JOptionPane.YES_OPTION;
	}

	@Override
	public void displayMessage(String message, int playerId) {
		JOptionPane.showMessageDialog(null, message, "Player "+playerId, JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public int promptInt(String prompt, int min, int max) {
		int result = 0;
		do {
			try {
				result = Integer.parseInt(JOptionPane.showInputDialog(prompt + " [" + min + ", " + max + "]"));
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Please enter a number in [" + min + ", " + max + "].");
			}
		} while(result > max || result < min);
		
		return result;
	}

}
