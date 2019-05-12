package underlings.element;

import underlings.gui.Choice;

public class Element implements Choice {

    private ElementColor elementColor;
    private ElementColor elementAlias;

    public Element(ElementColor elementColor) {
        this.elementColor = elementColor;
        this.elementAlias = elementColor;
    }

    public ElementColor getColor() {
        return this.elementColor;
    }

    @Override
    public String toString() {
        return elementColor.toString();
    }

    public ElementColor getAlias() {
        return this.elementAlias;
    }

    public void setAlias(ElementColor newColor) {
        this.elementAlias = newColor;
    }

}
