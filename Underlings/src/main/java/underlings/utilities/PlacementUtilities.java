package underlings.utilities;

import java.util.List;
import java.util.function.Function;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;

public class PlacementUtilities {

    private HatchingGround hatchingGround;
    private Gui gui;
    private Runnable displayMethod;

    public PlacementUtilities(HatchingGround hatchingGround, Gui gui, Runnable displayMethod) {
        this.hatchingGround = hatchingGround;
        this.gui = gui;
        this.displayMethod = displayMethod;
    }

    public Card selectCard(List<Card> cards, Player player) {
        Function<Card, Boolean> isValid = (Card c) -> {
            return cards.contains(c);
        };
        return gui.getCard(player.getPlayerId(), "Pick a card to place an element on.", this.hatchingGround, isValid);
    }

    public ElementSpace selectElementSpace(Card card, Player player) {
        List<ElementSpace> spaces = player.elementSpaceLogic.getPlayableSpaces(card, player.getElements());
        ElementSpace space = this.gui.promptHandler.promptChoice("Pick an element space to place an element on.",
                spaces, player.getPlayerId());
        return space;
    }

    public void placeElements(ElementSpace space, Player player) {
        List<Element> choices = player.elementSpaceLogic.getPlayableElements(space, player.getElements());
        boolean moreMoves = true;
        while (moreMoves) {
            Element element =
                    this.gui.promptHandler.promptChoice("Pick an element to place", choices, player.getPlayerId());

            if (player.elementSpaceLogic.isOpenElement(element.getColor())) {

            }

            space.addElements(element);
            player.removeElement(element);
            this.displayMethod.run();

            choices = player.elementSpaceLogic.getPlayableElements(space, player.getElements());
            moreMoves = this.gui.getMoreMovesDecision(choices.size(), player.getPlayerId());
        }
    }
}
