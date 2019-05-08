package underlings.card.effect.wild.adjacenteggs.destroy;

import underlings.element.ElementColor;
import underlings.element.ElementSpace;

public class OneElementEffect extends ElementsEffect {

    @Override
    public void destroyElementsOfColorOnSpace(ElementSpace destroyableSpace, ElementColor elementColorToDestroy) {
        destroyableSpace.destroyOneElementOfColor(elementColorToDestroy);
    }
}
