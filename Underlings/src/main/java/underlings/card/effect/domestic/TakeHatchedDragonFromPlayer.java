package underlings.card.effect.domestic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.card.effect.PlayersHatchingEffect;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class TakeHatchedDragonFromPlayer extends PlayersHatchingEffect {
    public int points;
    public Temperature[] temperatures;

    @Override
    protected void apply(Player currentPlayer, List<Player> players, Gui gui, EggHatchingLogic eggHatchingLogic) {
        Map<Player, List<Card>> playerCards = new HashMap<>();
        playerCards.put(players.get(0), players.get(0).hatchedCards);
        Card toSteal = gui.promptCardToSteal("Choose a card to steal", currentPlayer.getPlayerId(), playerCards);
        eggHatchingLogic.hatchEgg(toSteal, false, currentPlayer);
    }

}
