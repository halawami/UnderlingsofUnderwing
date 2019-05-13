package underlings.card.effect.wild;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class PlayersTradeDragon extends PlayersEffect {

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
        Player playerWithMinCards = players.get(0);
        for (Player player : players) {
            if (player.hatchedCards.size() < playerWithMinCards.hatchedCards.size()) {
                playerWithMinCards = player;
            }
        }
        int count = 0;
        for (Player player : players) {
            if (player != playerWithMinCards && playerWithMinCards.hatchedCards.size() == player.hatchedCards.size()) {
                count++;
            }
        }
        if (count > 0) {
            gui.notifyAction(FakePlayer.getInstance().getPlayerId(), LocaleWrap.get("notify_no_player_least_dragons"));
        } else {
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
