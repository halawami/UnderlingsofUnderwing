package underlings.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import underlings.utilities.LocaleWrap;

public class ElementSpace {

    public List<Element> elements;
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
        this.elements.addAll(Arrays.asList(elementsToAdd));
    }

    public void destroyAllElementsOfColor(ElementColor colorOfElementsToDestroy) {
        Predicate<Element> removeMethod = (Element e) -> e.getColor().equals(colorOfElementsToDestroy);
        this.elements.removeIf(removeMethod);
    }

    public void destroyOneElementOfColor(ElementColor colorOfElementsToDestroy) {
        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).getColor().equals(colorOfElementsToDestroy)) {
                this.elements.remove(i);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return LocaleWrap.format("element_space", this.color);
    }

    public List<ElementColor> getElementColors() {
        List<ElementColor> colors = new ArrayList<>();
        this.elements.forEach((element) -> colors.add(element.getAlias()));
        return colors;
    }

    public void destroyAllElements() {
        this.elements.clear();
    }
}
