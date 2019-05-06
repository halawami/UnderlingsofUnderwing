package underlings.card.effect.wild.destroy;

import underlings.element.ElementColor;
import underlings.element.ElementSpace;

public class OneElementOnAllAdjacentEggsEffect extends ElementsOnAllAdjacentEggsEffect {

    @Override
    public void destroyElementsOfColorOnSpace(ElementSpace destroyableSpace, ElementColor elementColorToDestroy) {
        destroyableSpace.destroyOneElementOfColor(elementColorToDestroy);
    }
}
