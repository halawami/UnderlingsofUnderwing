package underlings.card.effect.wild;

import java.util.Arrays;
import java.util.List;
import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

public class AddElementsToAllAdjacentEggsEffect extends HatchingGroundEffect {

    public ElementColor[] elementColors;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround,
            ElementBag elementBag, ElementSpaceLogic elementSpaceLogic) {
        List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
        for (Card adjacentCard : adjacentCards) {
            List<ElementSpace> playableSpaces =
                    elementSpaceLogic.getPlayableSpaces(
                            Arrays.asList(this.elementColors), adjacentCard);
            for (ElementSpace playableSpace : playableSpaces) {
                List<ElementColor> playableElementColors =
                        elementSpaceLogic.getValidAdditions(playableSpace);
                for (ElementColor playableElementColor : playableElementColors) {
                    if (elementColorsContains(playableElementColor)) {
                        Element element = elementBag
                                .drawElementFromList(playableElementColor);
                        playableSpace.addElements(element);
                    }
                }
            }
        }
    }

    private boolean elementColorsContains(ElementColor elementColor) {
        for (int i = 0; i < this.elementColors.length; i++) {
            if (this.elementColors[i] == elementColor) {
                return true;
            }
        }
        return false;
    }


}
