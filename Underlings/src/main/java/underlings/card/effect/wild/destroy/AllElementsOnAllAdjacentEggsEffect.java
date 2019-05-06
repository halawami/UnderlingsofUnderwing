package underlings.card.effect.wild.destroy;

import underlings.element.ElementColor;
import underlings.element.ElementSpace;

public class AllElementsOnAllAdjacentEggsEffect extends DestroyElementsOnAllAdjacentEggsEffect {

    @Override
    public void destroyElementsOfColorOnSpace(ElementSpace destroyableSpace, ElementColor elementColorToDestroy) {
        destroyableSpace.destroyAllElementsOfColor(elementColorToDestroy);
    }
}
