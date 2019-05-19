package underlings.card.effect.wild.adjacenteggs;

import java.util.List;
import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.WildHandler;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class AddElementsEffect extends AdjacentEggsEffect {

    public ElementColor[] elementColors;

    @Override
    public void applyOnAdjacentEgg(Card adjacentEgg, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic,
            EggHatchingLogic eggHatchingLogic, Deck deck, HandlerMovementLogic handlerMovementLogic,
            HatchingGround hatchingGround) {
        if (adjacentEgg.handler == WildHandler.getInstance()) {
            return;
        }
        for (ElementColor elementColorToAdd : this.elementColors) {
            this.addElementToCard(elementColorToAdd, adjacentEgg, elementSpaceLogic, elementBag);
        }
    }

    public void addElementToCard(ElementColor elementColorToAdd, Card card, ElementSpaceLogic elementSpaceLogic,
            ElementBag elementBag) {
        List<ElementSpace> playableSpaces = elementSpaceLogic.getPlayableSpaces(card, elementColorToAdd);
        if (!playableSpaces.isEmpty()) {
            Element elementToAdd = elementBag.drawElementFromList(elementColorToAdd);
            if (elementToAdd != NullElement.getInstance()) {
                playableSpaces.get(0).addElements(elementToAdd);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder elements = new StringBuilder();
        for (ElementColor color : this.elementColors) {
            elements.append(color);
            elements.append(LocaleWrap.get("space"));
        }
        return LocaleWrap.format("place_element_on_all_eggs_effect", elements);
    }

}
