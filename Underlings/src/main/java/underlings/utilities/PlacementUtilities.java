package underlings.utilities;

import java.util.ArrayList;
import java.util.List;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
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
        String prompt = LocaleWrap.get("prompt_element_card");
        return this.gui.getCard(player.getPlayerId(), prompt, this.hatchingGround, cards);
    }

    public ElementSpace selectElementSpace(Card card, Player player) {
        List<ElementSpace> spaces = player.elementSpaceLogic.getPlayableSpaces(card, player.getElements());
        ElementSpace space = this.gui.promptHandler.promptChoice(LocaleWrap.get("prompt_element_space"), spaces,
                player.getPlayerId());
        return space;
    }

    public void placeElements(ElementSpace space, Player player) {
        List<Element> choices = player.elementSpaceLogic.getPlayableElements(space, player.getElements());
        boolean moreMoves = true;
        while (moreMoves) {
            Element element = this.gui.promptHandler.promptChoice(LocaleWrap.get("prompt_element"), choices,
                    player.getPlayerId());

            if (player.elementSpaceLogic.isOpenElement(element.getColor())) {
                List<ElementColor> validAdditions = player.elementSpaceLogic.getValidAdditions(space);
                ElementColor color = this.gui.promptHandler.promptChoice(LocaleWrap.get("prompt_element_color"),
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
