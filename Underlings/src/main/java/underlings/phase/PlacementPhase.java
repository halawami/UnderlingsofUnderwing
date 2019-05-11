package underlings.phase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import underlings.card.Card;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.PlacementUtilities;

public class PlacementPhase extends RotationPhase {

    private Map<Player, Integer> turnCounts;
    private EggHatchingLogic wildEggHatchingLogic;
    private PlacementUtilities utils;

    public PlacementPhase(List<Player> players, Gui gui, HatchingGround hatchingGround, Runnable displayMethod,
            EggHatchingLogic eggHatchingLogic, PlacementUtilities utils) {
        super(players, gui, null, hatchingGround, displayMethod, null);
        this.wildEggHatchingLogic = eggHatchingLogic;
        this.utils = utils;
    }

    @Override
    public void setup() {
        this.turnCounts = new HashMap<Player, Integer>();
        for (Player player : this.players) {
            this.turnCounts.put(player, player.getHandlerCount());
        }
    }

    @Override
    public void turn(Player player) {
        if (!checkAndDecrementTurnCount(player)) {
            return;
        }

        List<Card> cards = this.hatchingGround.getPlayableCards(player.elementSpaceLogic, player.getElements());
        if (cards.isEmpty()) {
            this.gui.promptHandler.displayMessage("Player has no valid placements", player.getPlayerId(),
                    JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            this.phaseComplete = false;
        }

        Card card = this.utils.selectCard(cards, player);
        ElementSpace space = this.utils.selectElementSpace(card, player);

        this.utils.placeElements(space, player);

        if (player.elementSpaceLogic.isComplete(card) && card.handler == null) {
            this.wildEggHatchingLogic.hatchEgg(card, true, FakePlayer.getInstance());
            checkGameover();
        }
    }

    private void checkGameover() {
        this.gameComplete = true;
        this.phaseComplete = true;
        for (Card groundCard : this.hatchingGround) {
            if (groundCard.handler != WildHandler.getInstance()) {
                this.gameComplete = false;
                this.phaseComplete = false;
            }
        }
    }

    private boolean checkAndDecrementTurnCount(Player player) {
        int playerTurns = turnCounts.get(player);
        if (playerTurns <= 0) {
            return false;
        }

        this.turnCounts.put(player, playerTurns - 1);
        return true;
    }
}
