package underlings.phase;

import java.util.List;

import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.player.Player;

public abstract class Phase {

	List<Player> players;
	GUI gui;
	ElementBag elementBag;
	HatchingGround hatchingGround;
	Runnable displayMethod;

	public Phase(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod) {
		this.players = players;
		this.gui = gui;
		this.elementBag = elementBag;
		this.hatchingGround = hatchingGround;
		this.displayMethod = displayMethod;
	}

	public abstract void execute();

}
