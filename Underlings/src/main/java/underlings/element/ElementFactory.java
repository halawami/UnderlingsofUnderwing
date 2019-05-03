package underlings.element;

public class ElementFactory {

    public Element createElement(ElementColor color) {
        return new Element(color);
    }

}
