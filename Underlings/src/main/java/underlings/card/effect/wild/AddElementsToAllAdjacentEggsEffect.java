package underlings.card.effect.wild;

import com.google.common.collect.Sets;
import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddElementsToAllAdjacentEggsEffect extends HatchingGroundEffect {

    public ElementColor[] elementColors;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic) {
        List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
        for (Card adjacentCard : adjacentCards) {
            List<ElementSpace> playableSpaces = elementSpaceLogic.getPlayableSpaces(Arrays.asList(elementColors), adjacentCard);
            for (ElementSpace playableSpace : playableSpaces) {
                List<ElementColor> playableElementColors = elementSpaceLogic.getValidAdditions(playableSpace);
                Set<ElementColor> elementColorsToAdd = getIntersection(playableElementColors, this.elementColors);
                for (ElementColor elementColorToAdd : elementColorsToAdd) {
                    playableSpace.addElements(elementBag.drawElementFromList(elementColorToAdd));
                }
            }
        }

    }

    private Set<ElementColor> getIntersection(List<ElementColor> playableElementColors, ElementColor[] possibleElementColorsToAdd) {
        Set<ElementColor> playableElementColorsSet = new HashSet<>(playableElementColors);
        Set<ElementColor> possibleElementColorsToAddSet = new HashSet<>(Arrays.asList(possibleElementColorsToAdd));

        return Sets.intersection(playableElementColorsSet, possibleElementColorsToAddSet);

    }
}
