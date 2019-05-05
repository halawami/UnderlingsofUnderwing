package underlings.phase;

import java.util.List;

import underlings.element.ElementBag;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;

public abstract class Phase {

	protected List<Player> players;
	public Gui gui;
	protected ElementBag elementBag;
	protected HatchingGround hatchingGround;
	protected Runnable displayMethod;
	protected Field field;

	protected boolean phaseComplete;
	protected boolean gameComplete;

	public Phase(List<Player> players, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod, Field field) {
		this.players = players;
		this.gui = gui;
		this.elementBag = elementBag;
		this.hatchingGround = hatchingGround;
		this.displayMethod = displayMethod;
		this.field = field;
	}

	public abstract void execute(int turnLeader);

	public abstract void setup();

	public abstract boolean turn(Player player);

}
