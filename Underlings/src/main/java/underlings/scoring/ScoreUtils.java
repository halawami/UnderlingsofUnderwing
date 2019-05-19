package underlings.scoring;

import java.util.ArrayList;
import java.util.List;
import underlings.card.Card;
import underlings.card.Temperature;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class ScoreUtils {

    private List<Player> players;
    private Gui gui;
    protected int winningScore;

    public ScoreUtils(List<Player> players, Gui gui) {
        this.players = players;
        this.gui = gui;
    }

    public void calculateScores() {
        int warmest = 0;
        int coolest = 0;

        for (Player player : this.players) {
            player.netTemperature = this.calculateTemperature(player.hatchedCards);

            warmest = Math.max(player.netTemperature, warmest);
            coolest = Math.min(player.netTemperature, coolest);
        }

        for (Player player : this.players) {
            player.score = 0;

            if (this.players.size() > 2) {
                player.score += (player.netTemperature == warmest && warmest != 0) ? 15 : 0;
                player.score += (player.netTemperature == coolest && coolest != 0) ? 15 : 0;
                player.score += (player.netTemperature == 0) ? 20 : 0;
            }

            player.score += this.calculatePoints(player.hatchedCards);

            this.winningScore = Math.max(player.score, this.winningScore);
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
        for (Player player : this.players) {
            this.gui.alert(LocaleWrap.format("player_score", player, player.score), player.id, PromptType.REGULAR);
        }
    }

    public void displayWinners() {
        List<Player> winners = new ArrayList<>();
        for (Player player : this.players) {
            if (player.score == this.winningScore) {
                winners.add(player);
            }
        }
        this.gui.alert(LocaleWrap.format("winners", winners), PromptType.REGULAR);
    }

}
