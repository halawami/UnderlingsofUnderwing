package underlings.gui;

import java.util.List;

import javax.swing.JOptionPane;

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
}
