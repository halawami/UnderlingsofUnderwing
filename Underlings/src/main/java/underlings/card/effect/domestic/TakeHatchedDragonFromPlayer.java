package underlings.card.effect.domestic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.card.effect.PlayersHatchingEffect;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class TakeHatchedDragonFromPlayer extends PlayersHatchingEffect {

    public int points;
    public Temperature[] temperatures;

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui, EggHatchingLogic eggHatchingLogic) {
        List<Temperature> temperaturesList = Arrays.asList(temperatures);
        Map<Player, List<Card>> playerCards = new HashMap<>();
        for (Player player : players) {
            if (player != currentPlayer) {
                playerCards.put(player, new LinkedList<>());
                for (Card dragon : player.hatchedCards) {
                    if (temperaturesList.contains(dragon.temperature) && dragon.points <= points) {
                        playerCards.get(player).add(dragon);
                    }
                }
            }
        }
        Card toSteal =
                gui.promptCardToSteal(LocaleWrap.get("prompt_card_to_steal"), currentPlayer.getId(), playerCards);

        // TODO: is this the right behavior
        eggHatchingLogic.hatchEgg(toSteal, false, currentPlayer);
    }

}
