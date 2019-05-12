package underlings.utilities;

import java.util.List;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;

public class PlacementUtilities {

    private HatchingGround hatchingGround;
    private Gui gui;
    private Runnable displayMethod;
    private List<Card> cards;

    public PlacementUtilities(HatchingGround hatchingGround, Gui gui, Runnable displayMethod) {
        this.hatchingGround = hatchingGround;
        this.gui = gui;
        this.displayMethod = displayMethod;
    }

    public Boolean isValid(Card c) {
        return cards.contains(c);
    }

    public Card selectCard(List<Card> cards, Player player) {
        this.cards = cards;
        String prompt = "Pick a card to place an element on.";
        return gui.getCard(player.getPlayerId(), prompt, this.hatchingGround, this::isValid);
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
                List<ElementColor> validAdditions = player.elementSpaceLogic.getValidAdditions(space);
                ElementColor color = this.gui.promptHandler.promptChoice("Pick a color to play element as",
                        validAdditions, player.getPlayerId());
                element.setAlias(color);
            }

            space.addElements(element);
            player.removeElement(element);
            this.displayMethod.run();

            choices = player.elementSpaceLogic.getPlayableElements(space, player.getElements());
            moreMoves = this.gui.getMoreMovesDecision(choices.size(), player.getPlayerId());
        }
    }
}
