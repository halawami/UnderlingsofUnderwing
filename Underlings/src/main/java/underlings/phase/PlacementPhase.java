package underlings.phase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.swing.JOptionPane;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class PlacementPhase extends RotationPhase {

    private Map<Player, Integer> turnCounts;
    private EggHatchingLogic wildEggHatchingLogic;

    public PlacementPhase(List<Player> players, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            Runnable displayMethod, Field field, EggHatchingLogic eggHatchingLogic) {
        super(players, gui, elementBag, hatchingGround, displayMethod, field);
        this.wildEggHatchingLogic = eggHatchingLogic;
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

        int playerNum = player.getPlayerId();

        ElementSpaceLogic logic = player.elementSpaceLogic;
        List<Card> cards = this.hatchingGround.getPlayableCards(logic, player.getElements());

        if (cards.isEmpty()) {
            this.gui.promptHandler.displayMessage("Player has no valid placements", playerNum,
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        this.phaseComplete = false;

        Function<Card, Boolean> isValid = (Card c) -> {
            return cards.contains(c);
        };

        Card card = this.gui.getCard(playerNum, "Pick a card to place an element on.", this.hatchingGround, isValid);
        List<ElementSpace> spaces = logic.getPlayableSpaces(card, player.getElements());
        ElementSpace space =
                this.gui.promptHandler.promptChoice("Pick an element space to place an element on.", spaces, playerNum);

        List<Element> choices = this.playableElements(logic.getValidAdditions(space), player.getElements());
        boolean moreMoves = true;
        while (moreMoves) {
            Element element = this.gui.promptHandler.promptChoice("Pick an element to place", choices, playerNum);

            space.addElements(element);
            player.removeElement(element);
            this.displayMethod.run();

            choices = this.playableElements(logic.getValidAdditions(space), player.getElements());
            moreMoves = this.gui.getMoreMovesDecision(choices.size(), playerNum);
        }

        if (logic.isComplete(card) && card.handler == null) {
            hatchWildDragon(card);
        }
    }

    public void hatchWildDragon(Card card) {
        this.wildEggHatchingLogic.hatchEgg(card, true, FakePlayer.getInstance());
        this.gameComplete = true;
        this.phaseComplete = true;
        for (Card groundCard : this.hatchingGround) {
            if (groundCard.handler != WildHandler.getInstance()) {
                this.gameComplete = false;
                this.phaseComplete = false;
            }
        }
    }

    public List<Element> playableElements(List<ElementColor> list, List<Element> elements) {
        elements = new ArrayList<Element>(elements);
        for (int i = 0; i < elements.size(); i++) {
            if (!list.contains(elements.get(i).getColor())) {
                elements.remove(i--);
            }
        }
        return elements;
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
