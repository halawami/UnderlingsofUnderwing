package underlings.card.effect.domestic;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class DestroyUpToElementsOnAnyEggInPlayEffect extends UptoElementsFromAnyEggInPlayEffect {

    @Override
    public void applyOnSelectedElement(Element selectedElement, ElementSpace selectedSpace, Player currentPlayer) {
        selectedSpace.destroyOneElementOfColor(selectedElement.getColor());
    }

    @Override
    public String toString() {
        StringBuilder elements = new StringBuilder();
        for (ElementColor color : this.elementChoices) {
            elements.append(color);
            elements.append(" ");
        }
        return LocaleWrap.format("up_to_effect", LocaleWrap.get("destroy"), this.upTo, elements);
    }
}
