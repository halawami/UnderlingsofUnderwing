package underlings.phase;

import javax.swing.JOptionPane;

import underlings.gui.Gui;
import underlings.player.Player;

public class WildFinalPhase implements FinalPhase {

	private Gui gui;

	public WildFinalPhase(Gui gui) {
		this.gui = gui;
	}

	@Override
	public void execute() {
		this.gui.promptHandler.displayMessage("All eggs hatched wild, the game wins", -1, JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void turn(Player player) {

	}

}
