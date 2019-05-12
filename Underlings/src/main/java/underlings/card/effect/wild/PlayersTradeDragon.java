package underlings.card.effect.wild;

import java.util.List;

import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.card.effect.PlayersEffect;
import underlings.gui.Gui;
import underlings.player.Player;

public class PlayersTradeDragon extends PlayersEffect {

    @Override
    protected void apply(List<Player> players, Gui gui) {
        gui.promptCard("Choose a card to trade", players.get(0).hatchedCards);
        Card cardToTrade = players.get(0).hatchedCards.get(0);
        players.get(0).hatchedCards.remove(cardToTrade);
        gui.promptCard("Choose a card to trade", players.get(1).hatchedCards);
        players.get(1).hatchedCards.add(cardToTrade);
        players.get(0).hatchedCards.add(EmptyCard.getInstance());
    }

}
