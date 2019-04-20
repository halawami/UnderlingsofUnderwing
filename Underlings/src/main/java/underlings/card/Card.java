package underlings.card;

import underlings.card.Family;
import underlings.card.Temperature;
import underlings.game.Handler;

public class Card {
    public String name;
    public String hatchedFilePath;
    public String unhatchedFilePath;
    public int points;
    public Temperature temperature;
    public Family family;
	public Handler handler;
    public Effect[] domesticEffects;
    public Effect[] wildEffects;
}
