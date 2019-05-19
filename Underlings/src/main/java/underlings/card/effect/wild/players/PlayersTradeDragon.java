package underlings.card.effect.wild.players;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class PlayersTradeDragon extends PlayersEffect {

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        Player playerWithMinCards = players.get(0);
        Map<Integer, List<Player>> playersNumberOfCards = new HashMap<Integer, List<Player>>();
        int minNumberOfCards = findMin(playersNumberOfCards, players);
        if (playersNumberOfCards.get(minNumberOfCards).size() > 1) {
            gui.notifyAction(FakePlayer.getInstance().getId(), LocaleWrap.get("notify_no_player_least_dragons"));
        } else {
            playerWithMinCards = playersNumberOfCards.get(minNumberOfCards).get(0);
            for (Player player : players) {
                if (player != playerWithMinCards) {
                    tradeCards(gui, playerWithMinCards, player);
                }
            }
        }
    }

    protected int findMin(Map<Integer, List<Player>> playersNumberOfCards, List<Player> players) {
        int min = players.get(0).hatchedCards.size();
        for (Player player : players) {
            if (!playersNumberOfCards.containsKey(player.hatchedCards.size())) {
                playersNumberOfCards.put(player.hatchedCards.size(), new LinkedList<>());
            }
            if (player.hatchedCards.size() <= min) {
                playersNumberOfCards.get(player.hatchedCards.size()).add(player);
                min = player.hatchedCards.size();
            }
        }
        return min;
    }

    protected void tradeCards(Gui gui, Player playerWithMinCards, Player secondPlayer) {
        Card cardToTrade =
                gui.promptChoice(LocaleWrap.get("prompt_card_to_trade"), secondPlayer.hatchedCards, secondPlayer.id);
        Card secondCardToTrade = EmptyCard.getInstance();
        if (!playerWithMinCards.hatchedCards.isEmpty()) {
            secondCardToTrade = gui.promptChoice(LocaleWrap.get("prompt_card_to_trade"),
                    playerWithMinCards.hatchedCards, playerWithMinCards.id);
            secondPlayer.hatchedCards.add(secondCardToTrade);
            playerWithMinCards.hatchedCards.remove(secondCardToTrade);
        }
        secondPlayer.hatchedCards.remove(cardToTrade);
        playerWithMinCards.hatchedCards.add(cardToTrade);
    }

    @Override
    public String toString() {
        return LocaleWrap.get("trade_dragons_effect");
    }
}
