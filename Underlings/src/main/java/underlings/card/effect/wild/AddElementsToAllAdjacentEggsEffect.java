package underlings.card.effect.wild;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

public class AddElementsToAllAdjacentEggsEffect extends HatchingGroundEffect {

	public ElementColor[] elementColors;

	@Override
	protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
			ElementSpaceLogic elementSpaceLogic) {
		List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
		for (Card adjacentCard : adjacentCards) {
			List<ElementSpace> playableSpaces = elementSpaceLogic.getPlayableSpaces(Arrays.asList(this.elementColors),
					adjacentCard);
			for (ElementSpace playableSpace : playableSpaces) {
				List<ElementColor> playableElementColors = elementSpaceLogic.getValidAdditions(playableSpace);
				Set<ElementColor> elementColorsToAdd = this.getIntersection(playableElementColors, this.elementColors);
				for (ElementColor elementColorToAdd : elementColorsToAdd) {
					Element element = elementBag.drawElementFromList(elementColorToAdd);
					if (element != NullElement.getInstance()) {
						playableSpace.addElements();
					}
				}
			}
		}
	}

	private Set<ElementColor> getIntersection(List<ElementColor> playableElementColors,
			ElementColor[] possibleElementColorsToAdd) {
		Set<ElementColor> playableElementColorsSet = new HashSet<>(playableElementColors);
		Set<ElementColor> possibleElementColorsToAddSet = new HashSet<>(Arrays.asList(possibleElementColorsToAdd));

		return Sets.intersection(playableElementColorsSet, possibleElementColorsToAddSet);

	}
}
