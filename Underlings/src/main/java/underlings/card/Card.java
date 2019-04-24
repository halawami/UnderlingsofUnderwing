package underlings.card;

import underlings.card.effect.Effect;
import underlings.element.ElementSpace;
import underlings.gui.Choice;
import underlings.handler.Handler;

public class Card implements Choice {
    public String name;
    public int points;
    public Temperature temperature;
    public Family family;
	public Handler handler;
    public Effect[] domesticEffects;
    public Effect[] wildEffects;
    public ElementSpace[] elementSpaces;
    
    @Override public String toString() {
    	return this.name;
    }
    
}
