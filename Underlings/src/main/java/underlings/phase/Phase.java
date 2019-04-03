package underlings.phase;

import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.player.Player;

public interface Phase {

	public void execute(Player player, GUI gui, ElementBag elementBag, HatchingGround hatchingGround);
	
}
