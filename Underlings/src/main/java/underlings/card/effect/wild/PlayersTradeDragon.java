package underlings.card.effect.wild;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.player.Player;

public class PlayersTradeDragon extends PlayersEffect {

    @Override
    protected void apply(List<Player> players, Gui gui) {
        Card cardToTrade = gui.promptCard("Choose a card to trade", players.get(0).hatchedCards);
        players.get(0).hatchedCards.remove(cardToTrade);
        players.get(1).hatchedCards.add(cardToTrade);
        Card secondCard = gui.promptCard("Choose a card to trade", players.get(1).hatchedCards);
        players.get(0).hatchedCards.add(secondCard);
        players.get(1).hatchedCards.remove(secondCard);
    }

}
