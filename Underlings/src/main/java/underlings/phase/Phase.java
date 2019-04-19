package underlings.phase;

import java.util.List;

import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.player.Player;

public interface Phase {

	void execute(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
                 Runnable displayMethod);
	
}
