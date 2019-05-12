package underlings.element;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import underlings.gui.Choice;
import underlings.utilities.LocaleWrap;

public class ElementSpace implements Choice {

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
        return MessageFormat.format(LocaleWrap.get("element_space"), this.color);
    }

    public List<ElementColor> getElementColors() {
        List<ElementColor> colors = new ArrayList<>();
        this.elements.forEach((element) -> colors.add(element.getColor()));
        return colors;
    }

    public void destroyAllElements() {
        if (!this.elements.isEmpty()) {
            this.elements.remove(0);
        }
    }
}
