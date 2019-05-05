package underlings.phase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import underlings.gui.PromptHandler;
import underlings.handler.WildHandler;
import underlings.player.Player;

public class PlacementPhase extends RotationPhase {

    private Map<Player, Integer> turnCounts;

    public PlacementPhase(List<Player> players, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            Runnable displayMethod, Field field) {
        super(players, gui, elementBag, hatchingGround, displayMethod, field);
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
        int playerTurns = turnCounts.get(player);
        if (playerTurns <= 0) {
            return;
        } else {
            PromptHandler prompts = this.gui.promptHandler;
            int playerNum = player.getPlayerId();

            ElementSpaceLogic logic = player.elementSpaceLogic;
            List<Card> cards = this.getPlayableCards(logic, player.getElements());

            if (cards.isEmpty()) {
                prompts.displayMessage("Player has no valid placements", playerNum, JOptionPane.WARNING_MESSAGE);
            } else {
                this.phaseComplete = false;
                Card card = prompts.promptChoice("Pick a card to place an element on.", cards, playerNum);
                List<ElementSpace> spaces = logic.getPlayableSpaces(card, player.getElements());
                ElementSpace space =
                        prompts.promptChoice("Pick an element space to place an element on.", spaces, playerNum);

                boolean moreMoves = true;
                while (moreMoves) {
                    List<Element> choices = this.playableElements(logic.getValidAdditions(space), player.getElements());
                    Element element = prompts.promptChoice("Pick an element to place", choices, playerNum);

                    space.addElements(element);
                    player.removeElement(element);
                    this.displayMethod.run();

                    choices = this.playableElements(logic.getValidAdditions(space), player.getElements());
                    if (choices.isEmpty()) {
                        moreMoves = false;
                    } else {
                        moreMoves = prompts.promptDecision("Would you like to place another element?", playerNum);
                    }
                }

                if (logic.isComplete(card) && card.handler == null) {
                    card.handler = WildHandler.getInstance();
                    for (int i = 0; i < card.wildEffects.length; i++) {
                        card.wildEffects[i].on(this.elementBag).on(this.hatchingGround).on(player.elementSpaceLogic)
                                .on(player).apply();
                        this.gui.notifyAction(-1, card.wildEffects[i].toString() + " has been applied");
                    }

                    boolean gameOver = true;
                    for (Card groundCard : this.hatchingGround) {
                        if (groundCard.handler != WildHandler.getInstance()) {
                            gameOver = false;
                        }
                    }
                    if (gameOver) {
                        this.gui.promptHandler.displayMessage("All dragons hatched wild! You lose!", -1,
                                JOptionPane.WARNING_MESSAGE);
                        this.gameComplete = true;
                        return;
                    }
                }
            }
            this.turnCounts.put(player, playerTurns - 1);
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

    public List<Card> getPlayableCards(ElementSpaceLogic logic, List<Element> elements) {
        List<Card> cards = new ArrayList<Card>();
        for (Card card : this.hatchingGround) {
            if (!logic.getPlayableSpaces(card, elements).isEmpty()) {
                cards.add(card);
            }
        }
        return cards;
    }

}
