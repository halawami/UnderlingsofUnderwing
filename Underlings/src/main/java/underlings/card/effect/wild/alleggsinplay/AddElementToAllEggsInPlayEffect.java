package underlings.card.effect.wild.alleggsinplay;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.AllEggsInPlayEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.ElementSpaceUtilities;
import underlings.handler.HandlerMovementLogic;
import underlings.utilities.LocaleUtilities;

public class AddElementToAllEggsInPlayEffect extends AllEggsInPlayEffect {

    public ElementColor elementColor;

    @Override
    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceUtilities elementSpaceLogic, ElementBag elementBag,
            HandlerMovementLogic handlerMovementLogic) {
        this.addElementsToCard(this.elementColor, cardInPlay, elementSpaceLogic, elementBag);
    }

    public void addElementsToCard(ElementColor color, Card cardToAddTo, ElementSpaceUtilities elementSpaceLogic,
            ElementBag elementBag) {
        List<ElementSpace> playableSpaces = elementSpaceLogic.getPlayableSpaces(cardToAddTo, color);
        for (ElementSpace playableSpace : playableSpaces) {
            Element elementToAdd = elementBag.drawElementFromList(color);
            playableSpace.addElements(elementToAdd);
        }
    }

    @Override
    public String toString() {
        return LocaleUtilities.format("place_element_on_all_eggs_effect", elementColor);
    }
}
