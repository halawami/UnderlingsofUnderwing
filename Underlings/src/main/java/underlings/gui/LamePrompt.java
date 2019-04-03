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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElementDrawChoice promptElementDrawChoice(List<ElementDrawChoice> elementDrawChoices) {
		// TODO Auto-generated method stub
		return null;
	}
}
