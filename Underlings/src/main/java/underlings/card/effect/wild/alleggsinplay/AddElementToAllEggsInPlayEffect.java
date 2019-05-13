package underlings.card.effect.wild.alleggsinplay;

import java.util.List;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class AddElementToAllEggsInPlayEffect extends AllEggsInPlayEffect {

    public ElementColor elementColor;

    @Override
    public void applyOnCardInPlay(Card cardInPlay, ElementSpaceLogic elementSpaceLogic, ElementBag elementBag) {
        this.addElementsToCard(this.elementColor, cardInPlay, elementSpaceLogic, elementBag);
    }

    public void addElementsToCard(ElementColor color, Card cardToAddTo, ElementSpaceLogic elementSpaceLogic,
            ElementBag elementBag) {
        List<ElementSpace> playableSpaces = elementSpaceLogic.getPlayableSpaces(cardToAddTo, color);
        for (ElementSpace playableSpace : playableSpaces) {
            Element elementToAdd = elementBag.drawElementFromList(color);
            playableSpace.addElements(elementToAdd);
        }
    }
}
