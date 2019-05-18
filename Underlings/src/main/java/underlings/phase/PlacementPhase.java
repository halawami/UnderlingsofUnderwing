package underlings.phase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import underlings.card.Card;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;
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
        if (!this.checkAndDecrementTurnCount(player)) {
            return;
        }

        List<Card> cards = this.utils.getPlayableCards(player.elementSpaceLogic, player.getElements());
        if (cards.isEmpty()) {
            this.gui.alert(LocaleWrap.get("no_placements"), player.getId(), PromptType.WARNING);
            return;
        } else {
            this.setPhaseComplete(false);
        }

        Card card = this.utils.selectCard(cards, player);
        ElementSpace space = this.utils.selectElementSpace(card, player);

        this.utils.placeElements(space, player);

        if (this.hatchingGround.logic.isComplete(card) && card.handler == null) {
            this.wildEggHatchingLogic.hatchEgg(card, FakePlayer.getInstance());
            this.checkGameover();
        }
    }

    protected void checkGameover() {
        this.gameComplete = true;
        this.setPhaseComplete(true);
        for (Card groundCard : this.hatchingGround) {
            if (groundCard.handler != WildHandler.getInstance()) {
                this.gameComplete = false;
                this.setPhaseComplete(false);
            }
        }
    }

    protected boolean checkAndDecrementTurnCount(Player player) {
        int playerTurns = this.turnCounts.get(player);
        if (playerTurns <= 0) {
            return false;
        }

        this.turnCounts.put(player, playerTurns - 1);
        return true;
    }
}
