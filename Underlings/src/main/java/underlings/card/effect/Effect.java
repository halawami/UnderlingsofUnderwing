package underlings.card.effect;

import underlings.game.HatchingGround;
import underlings.player.Player;

public interface Effect {
 
	public void apply(Player player);
	
	public void apply(HatchingGround hatchingGround);
	
}
