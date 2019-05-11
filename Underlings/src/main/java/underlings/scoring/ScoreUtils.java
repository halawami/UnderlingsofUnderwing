package underlings.scoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.gui.Gui;
import underlings.player.Player;

public class ScoreUtils {

	private List<Player> players;
	private Gui gui;
	public List<Player> winners;

	public ScoreUtils(List<Player> players, Gui gui) {
		this.players = players;
		this.gui = gui;
		this.winners = new LinkedList<>();
	}

	public Map<Player, Integer> calculateScores() {
		Map<Player, Integer> scores = new HashMap<>();
		boolean bonus = this.players.size() > 2;

		int warmest = 0;
		int coolest = 0;

		List<Player> warmestPlayers = new ArrayList<>();
		List<Player> coolestPlayers = new ArrayList<>();

		for (Player player : players) {
			int temp = this.calculateTemperature(player.hatchedCards);

			if (temp != 0) {
				if (temp > warmest) {
					warmest = temp;
					warmestPlayers = new ArrayList<>();
					warmestPlayers.add(player);
				} else if (temp == warmest) {
					warmestPlayers.add(player);
				}

				if (temp < coolest) {
					coolest = temp;
					coolestPlayers = new ArrayList<>();
					coolestPlayers.add(player);
				} else if (temp == coolest) {
					coolestPlayers.add(player);
				}
			}

		}

		for (Player player : players) {
			int score = 0;

			if (bonus) {
				score += (warmestPlayers.contains(player)) ? 15 : 0;
				score += (coolestPlayers.contains(player)) ? 15 : 0;
				score += (this.calculateTemperature(player.hatchedCards)) == 0 ? 20 : 0;
			}

			score += this.calculatePoints(player.hatchedCards);

			scores.put(player, score);
		}

		return scores;
	}

	public int calculatePoints(List<Card> cards) {
		int points = 0;

		for (Card card : cards) {
			points += card.points;
		}

		return points;
	}

	public int calculateTemperature(List<Card> cards) {
		int balance = 0;

		for (Card card : cards) {
			Temperature temp = card.temperature;
			balance += (temp == Temperature.WARM) ? 1 : (temp == Temperature.COOL) ? -1 : 0;
		}

		return balance;
	}

	public void displayScores() {
		Map<Player, Integer> scores = this.calculateScores();
		int maxScore = 0;

		for (Player player : scores.keySet()) {
			maxScore = this.decideWinners(scores, player, maxScore);

			gui.promptHandler.displayMessage(player + ": " + scores.get(player) + " points", player.getPlayerId(),
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	public int decideWinners(Map<Player, Integer> scores, Player player, int maxScore) {
		if (scores.get(player) == maxScore) {
			this.winners.add(player);
		} else if (scores.get(player) > maxScore) {
			this.winners.clear();
			this.winners.add(player);
			maxScore = scores.get(player);
		}
		return maxScore;
	}

	public void displayWinners() {
		this.gui.promptHandler.displayMessage("Winner(s): " + this.winners, 0, JOptionPane.PLAIN_MESSAGE);
	}

}
