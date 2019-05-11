package underlings.phase;

import java.util.List;

import javax.swing.JOptionPane;

import underlings.gui.Gui;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public class RegularFinalPhase implements FinalPhase {

	private Gui gui;
	private List<Player> players;
	private Phase dragonPhase;
	private ScoreUtils scoreUtils;

	public RegularFinalPhase(List<Player> players, Gui gui, Phase dragonPhase, ScoreUtils scoreUtils) {
		this.players = players;
		this.gui = gui;
		this.dragonPhase = dragonPhase;
		this.scoreUtils = scoreUtils;
	}

	@Override
	public void execute() {
		this.dragonPhase.setup();

		for (Player player : this.players) {
			this.turn(player);
		}

		this.gui.promptHandler.displayMessage("Game Over!", 0, JOptionPane.PLAIN_MESSAGE);
		this.scoreUtils.displayScores();
		this.scoreUtils.displayWinners();
	}

	@Override
	public void turn(Player player) {
		this.dragonPhase.turn(player);
		this.dragonPhase.turn(player);
	}
}
