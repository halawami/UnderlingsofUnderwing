package underlings.element;

import underlings.utilities.LocaleUtilities;

public enum ElementColor {
    BLUE, RED, GREEN, YELLOW, ORANGE, PURPLE, WHITE, BLACK, NULL;

    @Override
    public String toString() {
        return LocaleUtilities.get(this.name());
    }

}
