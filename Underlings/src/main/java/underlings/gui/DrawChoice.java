package underlings.gui;

import underlings.element.ElementColor;

public enum DrawChoice implements Choice {
    BLUE, RED, GREEN, PURPLE, ORANGE, YELLOW, BLACK, WHITE, RANDOM, COOL, WARM;

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
	
    @Override
    public String toString(){
        return this.name() + " Element";
    }
}
