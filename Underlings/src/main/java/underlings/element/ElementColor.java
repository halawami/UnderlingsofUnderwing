package underlings.element;

import underlings.utilities.LocaleWrap;

public enum ElementColor {
	BLUE, RED, GREEN, YELLOW, ORANGE, PURPLE, WHITE, BLACK, NULL;

	@Override
	public String toString() {
		return LocaleWrap.get(this.name());
	}

}
