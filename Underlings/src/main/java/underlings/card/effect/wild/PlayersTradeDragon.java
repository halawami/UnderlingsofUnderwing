package underlings.card.effect.wild;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import underlings.card.Card;
import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class PlayersTradeDragon extends PlayersEffect {

    // TODO: clean up
    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        Player playerWithMinCards = players.get(0);
        int min = playerWithMinCards.hatchedCards.size();
        Map<Integer, List<Player>> highestCards = new HashMap<Integer, List<Player>>();
        for (Player player : players) {
            if (!highestCards.containsKey(player.hatchedCards.size())) {
                highestCards.put(player.hatchedCards.size(), new LinkedList<>());
            }
            if (player.hatchedCards.size() <= min) {
                highestCards.get(player.hatchedCards.size()).add(player);
                min = player.hatchedCards.size();
            }
        }
        if (highestCards.get(min).size() > 1) {
            gui.notifyAction(FakePlayer.getInstance().getPlayerId(), LocaleWrap.get("notify_no_player_least_dragons"));
        } else {
            playerWithMinCards = highestCards.get(min).get(0);
            for (Player player : players) {
                if (player != playerWithMinCards) {
                    Card cardToTrade = gui.promptCard(LocaleWrap.get("prompt_card_to_trade"), player.hatchedCards);
                    Card secondCardToTrade =
                            gui.promptCard(LocaleWrap.get("prompt_card_to_trade"), playerWithMinCards.hatchedCards);
                    player.hatchedCards.remove(cardToTrade);
                    playerWithMinCards.hatchedCards.add(cardToTrade);
                    player.hatchedCards.add(secondCardToTrade);
                    playerWithMinCards.hatchedCards.remove(secondCardToTrade);
                }
            }
        }
    }

}
