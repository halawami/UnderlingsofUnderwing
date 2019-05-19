package underlings.card.effect.domestic;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class CollectUpToElementsFromAnyEggInPlayEffect extends UptoElementsFromAnyEggInPlayEffect {

    @Override
    public void applyOnSelectedElement(Element selectedElement, ElementSpace selectedSpace, Player currentPlayer) {
        selectedSpace.destroyOneElementOfColor(selectedElement.getColor());
        currentPlayer.addElement(selectedElement);
    }

    @Override
    public String toString() {
        StringBuilder elements = new StringBuilder();
        for (ElementColor color : this.elementChoices) {
            elements.append(color);
            elements.append(" ");
        }
        return LocaleWrap.format("collect_up_to_effect", this.upTo, elements);
    }
}
