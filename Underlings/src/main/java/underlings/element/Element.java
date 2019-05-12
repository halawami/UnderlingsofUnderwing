package underlings.element;

import underlings.gui.Choice;

public class Element implements Choice {

    private ElementColor elementColor;

    public Element(ElementColor elementColor) {
        this.elementColor = elementColor;
    }

    public ElementColor getColor() {
        return this.elementColor;
    }

    @Override
    public String toString() {
        return elementColor.toString();
    }

    public ElementColor getAlias() {
        return ElementColor.BLUE;
    }

    public void setAlias(ElementColor blue) {

    }

}
