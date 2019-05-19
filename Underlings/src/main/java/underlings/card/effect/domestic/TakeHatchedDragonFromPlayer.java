package underlings.card.effect.domestic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class TakeHatchedDragonFromPlayer extends PlayersEffect {

    public int points;
    public Temperature[] temperatures;

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        List<Temperature> temperaturesList = Arrays.asList(this.temperatures);
        Map<Player, List<Card>> playerCards = new HashMap<>();
        System.out.println("here");
        for (Player player : players) {
            System.out.println("here2");
            if (player != currentPlayer && !player.hatchedCards.isEmpty()) {
                System.out.println("here3");
                playerCards.put(player, new LinkedList<>());
                for (Card dragon : player.hatchedCards) {
                    if (temperaturesList.contains(dragon.temperature) && dragon.points <= this.points) {
                        playerCards.get(player).add(dragon);
                    }
                }
                if (playerCards.get(player).isEmpty()) {
                    playerCards.remove(player);
                }
            }
        }
        if (playerCards.isEmpty()) {
            gui.alert(LocaleWrap.get("no_player_has_hatched_cards"), PromptType.REGULAR);
            return;
        }

        Player playerToSteal = gui.promptChoice(LocaleWrap.get("prompt_player_to_steal"),
                new ArrayList<>(playerCards.keySet()), currentPlayer.id);
        Card toSteal = gui.promptChoice(LocaleWrap.get("prompt_card_to_steal"), playerCards.get(playerToSteal),
                currentPlayer.getId());

        currentPlayer.hatchedCards.add(toSteal);
        playerToSteal.hatchedCards.remove(toSteal);
    }

    @Override
    public String toString() {
        StringBuilder temperature = new StringBuilder();
        for (Temperature temp : temperatures) {
            temperature.append(temp);
            temperature.append(" ");
        }
        return LocaleWrap.format("take_hatched_dragon", temperature, points);
    }
}
