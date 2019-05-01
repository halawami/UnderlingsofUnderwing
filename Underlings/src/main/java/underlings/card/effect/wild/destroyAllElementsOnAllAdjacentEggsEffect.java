package underlings.card.effect.wild;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

import java.util.List;

public class destroyAllElementsOnAllAdjacentEggsEffect extends HatchingGroundEffect {

    public ElementColor[] elementColors;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic) {
        List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
        for (Card adjacentCard : adjacentCards) {
            destroyAllElementsOfColorOnCard(elementColors[0], adjacentCard);
        }
    }

    public void destroyAllElementsOfColorOnCard(ElementColor elementColorToDestroy, Card card) {

    }
}
