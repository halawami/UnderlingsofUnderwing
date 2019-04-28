package underlings.scoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import underlings.card.Card;
import underlings.card.Temperature;
import underlings.player.Player;

public class ScoreUtils {

    public Map<Player, Integer> calculateScores(List<Player> players) {
        Map<Player, Integer> scores = new HashMap<>();

        int warmest = 0, coolest = 0;

        List<Player> warmestPlayers = new ArrayList<>();
        List<Player> coolestPlayers = new ArrayList<>();

        for (Player player : players) {
            int temp = this.calculateTemperature(player.hatchedCards);

            if (temp != 0) {
                if (temp == warmest) {
                    warmestPlayers.add(player);
                } else if (temp > warmest) {
                    warmest = temp;
                    warmestPlayers = new ArrayList<>();
                    warmestPlayers.add(player);
                }

                if (temp == coolest) {
                    coolestPlayers.add(player);
                } else if (temp < coolest) {
                    coolest = temp;
                    coolestPlayers = new ArrayList<>();
                    coolestPlayers.add(player);
                }
            }

        }

        for (Player player : players) {
            int score = 0;

            if (players.size() > 2) {
                score += (warmestPlayers.contains(player)) ? 15 : 0;
                score += (coolestPlayers.contains(player)) ? 15 : 0;
                score += (this.calculateTemperature(player.hatchedCards)) == 0
                        ? 20
                        : 0;
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
            balance += (temp == Temperature.WARM) ? 1
                    : (temp == Temperature.COOL) ? -1 : 0;
        }

        return balance;
    }

}
