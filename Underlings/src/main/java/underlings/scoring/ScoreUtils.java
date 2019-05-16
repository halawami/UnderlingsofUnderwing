package underlings.scoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class ScoreUtils {

    private List<Player> players;
    private Gui gui;
    protected List<Player> winners;
    protected Map<Player, Integer> scores;

    public ScoreUtils(List<Player> players, Gui gui) {
        this.players = players;
        this.gui = gui;
        this.winners = new LinkedList<>();
        this.scores = new HashMap<>();
    }

    public void calculateScores() {
        boolean bonus = this.players.size() > 2;

        int warmest = 0;
        int coolest = 0;

        List<Player> warmestPlayers = new ArrayList<>();
        List<Player> coolestPlayers = new ArrayList<>();

        for (Player player : this.players) {
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

        for (Player player : this.players) {
            int score = 0;

            if (bonus) {
                score += (warmestPlayers.contains(player)) ? 15 : 0;
                score += (coolestPlayers.contains(player)) ? 15 : 0;
                score += (this.calculateTemperature(player.hatchedCards)) == 0 ? 20 : 0;
            }

            score += this.calculatePoints(player.hatchedCards);

            this.scores.put(player, score);
        }
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
        int maxScore = 0;

        for (Player player : this.scores.keySet()) {
            maxScore = this.decideWinners(this.scores, player, maxScore);

            this.gui.alert(LocaleWrap.format("player_score", player, this.scores.get(player)), player.getId(),
                    PromptType.REGULAR);
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
        this.gui.alert(LocaleWrap.format("winners", this.winners), PromptType.REGULAR);
    }

}
