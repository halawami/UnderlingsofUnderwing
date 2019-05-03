package card.effect.wild;

import element.ElementColor;
import element.ElementSpace;

public class DestroyAllElementsOnAllAdjacentEggsEffect extends DestroyElementsOnAllAdjacentEggsEffect {

    @Override
    public void destroyElementsOfColorOnSpace(ElementSpace destroyableSpace, ElementColor elementColorToDestroy) {
        destroyableSpace.destroyAllElementsOfColor(elementColorToDestroy);
    }
}
