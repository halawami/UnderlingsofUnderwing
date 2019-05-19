package underlings.card.effect.wild.adjacenteggs.elements.destroy;

import underlings.element.ElementColor;
import underlings.element.ElementSpace;

public class OneElementEffect extends DestroyElementsEffect {

    @Override
    public void destroyElementsOfColorOnSpace(ElementSpace destroyableSpace, ElementColor elementColorToDestroy) {
        destroyableSpace.destroyOneElementOfColor(elementColorToDestroy);
    }
}
