package underlings.phase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import underlings.gui.Gui;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public class RegularFinalPhase implements FinalPhase {

	private Gui gui;
	private List<Player> players;
	private Phase dragonPhase;

	public RegularFinalPhase(List<Player> players, Gui gui, Phase dragonPhase) {
		this.players = players;
		this.gui = gui;
		this.dragonPhase = dragonPhase;
	}

	@Override
	public void execute() {
		this.dragonPhase.setup();

		for (Player player : this.players) {
			this.turn(player);
		}

		this.gui.promptHandler.displayMessage("Game Over!", 0, JOptionPane.PLAIN_MESSAGE);

		Map<Player, Integer> scores = new ScoreUtils().calculateScores(this.players, this.players.size() > 2);

		List<Player> maxPlayers = new ArrayList<>();
		int maxScore = 0;

		for (Player player : scores.keySet()) {
			if (scores.get(player) == maxScore) {
				maxPlayers.add(player);
			} else if (scores.get(player) > maxScore) {
				maxPlayers = new ArrayList<>();
				maxPlayers.add(player);
				maxScore = scores.get(player);
			}
			this.gui.promptHandler.displayMessage(player + ": " + scores.get(player) + " points", player.getPlayerId(),
					JOptionPane.PLAIN_MESSAGE);
		}

		this.gui.promptHandler.displayMessage("Winner(s): " + maxPlayers, 0, JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void turn(Player player) {
		this.dragonPhase.turn(player);
		this.dragonPhase.turn(player);
	}
}
