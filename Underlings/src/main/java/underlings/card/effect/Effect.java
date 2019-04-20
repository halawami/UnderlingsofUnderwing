package underlings.card.effect;

import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.player.Player;

public interface Effect {
 
	public void apply(Player player);
	
	public void apply(HatchingGround hatchingGround);
	
	public void apply(Player player, ElementBag elementBag);
	
}
