package underlings.card.effect.wild.adjacenteggs.elements.destroy;

import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.utilities.LocaleUtilities;

public class AllElementsEffect extends DestroyElementsEffect {

    @Override
    public void destroyElementsOfColorOnSpace(ElementSpace destroyableSpace, ElementColor elementColorToDestroy) {
        destroyableSpace.destroyAllElementsOfColor(elementColorToDestroy);
    }

    @Override
    public String toString() {
        StringBuilder elements = new StringBuilder();
        for (ElementColor color : this.elementColors) {
            elements.append(color);
            elements.append(" ");
        }
        return LocaleUtilities.format("destroy_all_elements_on_adjacent_eggs_effect", elements);
    }
}
