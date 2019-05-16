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
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class TakeHatchedDragonFromPlayer extends PlayersEffect {

    public int points;
    public Temperature[] temperatures;

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        List<Temperature> temperaturesList = Arrays.asList(this.temperatures);
        Map<Player, List<Card>> playerCards = new HashMap<>();
        for (Player player : players) {
            if (player != currentPlayer) {
                playerCards.put(player, new LinkedList<>());
                for (Card dragon : player.hatchedCards) {
                    if (temperaturesList.contains(dragon.temperature) && dragon.points <= this.points) {
                        playerCards.get(player).add(dragon);
                    }
                }
            }
        }

        Player playerToSteal = gui.promptChoice(LocaleWrap.get("prompt_player_to_steal"),
                new ArrayList<>(playerCards.keySet()), currentPlayer.id);
        Card toSteal = gui.promptChoice(LocaleWrap.get("prompt_card_to_steal"), playerCards.get(playerToSteal),
                currentPlayer.getId());

        // TODO: is this the right behavior
        // eggHatchingLogic.hatchEgg(toSteal, false, currentPlayer);
    }

}
