package underlings.scoring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.player.Player;

public class ScoreUtils {

	public Map<Player, Integer> calculateScores(List<Player> players) {
		Map<Player, Integer> scores = new HashMap<>();
		
		for (Player player : players) {
			scores.put(player, this.calculatePoints(player.hatchedCards));
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
			balance += (temp == Temperature.WARM) ? 1 : (temp == Temperature.COOL) ? - 1 : 0;
		}
		
		return balance;	
	}
	
}
