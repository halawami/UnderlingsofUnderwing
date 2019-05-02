package underlings.element;

import underlings.gui.Choice;

import java.util.ArrayList;
import java.util.List;

public class ElementSpace implements Choice {

    public List<ElementColor> elements;
    public ElementColor color;
    public ElementSpacePosition position;

    public ElementSpace() {
        this.elements = new ArrayList<>();
    }

    public ElementSpace(ElementColor color) {
        this();
        this.color = color;
    }

    public void addElements(Element... elementsToAdd) {
        for (Element element : elementsToAdd) {
            this.elements.add(element.getColor());
        }
    }

    public void destroyAllElementsOfColor(ElementColor colorOfElementsToDestroy) {
        this.elements.removeIf(colorOfElementsToDestroy::equals);
    }

    public void destroyOneElementOfColor(ElementColor colorOfElementsToDestroy) {
        if (this.elements.size() == 3){
            this.elements.remove(0);
        }
    }

    @Override
    public String toString() {
        return color + " Element Space";
    }

}
