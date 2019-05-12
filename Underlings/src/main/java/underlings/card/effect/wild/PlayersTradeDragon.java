package underlings.card.effect.wild;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.player.Player;

public class PlayersTradeDragon extends PlayersEffect {

    @Override
    protected void apply(List<Player> players, Gui gui) {
        Player playerWithMinCards = players.get(0);
        for (Player player : players) {
            if (player.hatchedCards.size() < playerWithMinCards.hatchedCards.size()) {
                playerWithMinCards = player;
            }
        }
        for (Player player : players) {
            if (player != playerWithMinCards) {
                Card cardToTrade = gui.promptCard("Choose a card to trade", player.hatchedCards);
                Card secondCardToTrade = gui.promptCard("Choose a card to trade", playerWithMinCards.hatchedCards);
                player.hatchedCards.remove(cardToTrade);
                playerWithMinCards.hatchedCards.add(cardToTrade);
                player.hatchedCards.add(secondCardToTrade);
                playerWithMinCards.hatchedCards.remove(secondCardToTrade);
            }
        }
    }

}
