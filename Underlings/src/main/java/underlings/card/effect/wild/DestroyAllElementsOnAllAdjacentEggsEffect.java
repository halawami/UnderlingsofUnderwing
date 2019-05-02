package underlings.card.effect.wild;

import underlings.element.ElementColor;
import underlings.element.ElementSpace;

public class DestroyAllElementsOnAllAdjacentEggsEffect extends DestroyElementsOnAllAdjacentEggsEffect {

    @Override
    public void destroyElementsOfColorOnSpace(ElementSpace destroyableSpace, ElementColor elementColorToDestroy) {
        destroyableSpace.destroyAllElementsOfColor(elementColorToDestroy);
    }
}
