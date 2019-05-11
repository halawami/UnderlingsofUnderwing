package underlings.element;

import underlings.gui.Choice;
import underlings.utilities.LocaleWrap;

public enum ElementColor implements Choice {
    BLUE, RED, GREEN, YELLOW, ORANGE, PURPLE, WHITE, BLACK, NULL;

    @Override
    public String toString() {
        return LocaleWrap.get(this.name());
    }

}
