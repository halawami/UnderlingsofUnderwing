package underlings.gui;

import underlings.element.ElementColor;
import underlings.utilities.LocaleWrap;

public enum DrawChoice {
    BLUE, RED, GREEN, PURPLE, ORANGE, YELLOW, BLACK, WHITE, RANDOM, COOL, WARM, NULL;

    public ElementColor elementColor;

    static {
        BLUE.elementColor = ElementColor.BLUE;
        RED.elementColor = ElementColor.RED;
        GREEN.elementColor = ElementColor.GREEN;
        PURPLE.elementColor = ElementColor.PURPLE;
        ORANGE.elementColor = ElementColor.ORANGE;
        YELLOW.elementColor = ElementColor.YELLOW;
        BLACK.elementColor = ElementColor.BLACK;
        WHITE.elementColor = ElementColor.WHITE;
    }

    public static DrawChoice getChoiceFromColor(ElementColor color) {
        for (DrawChoice drawChoice : DrawChoice.values()) {
            if (drawChoice.elementColor == color) {
                return drawChoice;
            }
        }
        return NULL;
    }

    @Override
    public String toString() {
        return LocaleWrap.format("draw_choice", LocaleWrap.get(this.name()));
    }
}
