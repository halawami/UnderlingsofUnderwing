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

    // TODO: Add more tests
	public boolean isComplete() {
//		for(int i = 0; i < this.elementSpaces.length; i++){
			if(this.elementSpaces.length > 0 && this.elementSpaces[0].elements.size() > 0 &&
					this.elementSpaces[0].elements.get(0).equals(this.elementSpaces[0].color)){
				System.out.println("this.elementSpaces[0].elements.get(0) " + this.elementSpaces[0].elements.get(0));
				System.out.println("this.elementSpaces[0].color " + this.elementSpaces[0].color);
				return true;
			}
//		}
		return false;
	}

}
