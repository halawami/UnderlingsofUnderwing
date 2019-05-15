package underlings.element;

import java.util.List;

import underlings.gui.DrawChoice;

public class ElementGiverFactory {

    public ElementGiver createElementGiver(List<DrawChoice> drawChoices) {
        ElementGiver elementGiver = new ElementGiver();
        elementGiver.drawChoices = drawChoices;
        return elementGiver;
    }

}
