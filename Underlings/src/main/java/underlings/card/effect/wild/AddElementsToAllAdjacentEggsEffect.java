package underlings.card.effect.wild;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

import java.util.Arrays;
import java.util.List;

public class AddElementsToAllAdjacentEggsEffect extends HatchingGroundEffect {

    public ElementColor[] elementColors;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic) {
        List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
        Card adjacentCard = adjacentCards.get(0);
        ElementSpace elementSpaceToAddTo = elementSpaceLogic.getPlayableSpaces(Arrays.asList(this.elementColors), adjacentCard).get(0);

        elementSpaceLogic.getValidAdditions(elementSpaceToAddTo);
        Element elementToAdd = elementBag.drawElementFromList(elementColors);
        elementSpaceToAddTo.addElements(elementToAdd);
    }
}
