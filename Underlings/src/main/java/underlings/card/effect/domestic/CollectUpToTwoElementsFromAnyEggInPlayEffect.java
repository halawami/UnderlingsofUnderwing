package underlings.card.effect.domestic;

import underlings.element.Element;
import underlings.element.ElementSpace;
import underlings.player.Player;

public class CollectUpToTwoElementsFromAnyEggInPlayEffect extends UptoTwoElementsFromAnyEggInPlayEffect {

    @Override
    public void applyOnSelectedElement(Element selectedElement, ElementSpace selectedSpace, Player currentPlayer) {
        selectedSpace.destroyOneElementOfColor(selectedElement.getColor());
        currentPlayer.addElement(selectedElement);
    }
}
