package underlings.phase;

import java.util.List;

import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.player.Player;

public abstract class Phase {

	protected List<Player> players;
	protected GUI gui;
	protected ElementBag elementBag;
	protected HatchingGround hatchingGround;
	protected Runnable displayMethod;

	protected boolean phaseComplete;

	public Phase(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod) {
		this.players = players;
		this.gui = gui;
		this.elementBag = elementBag;
		this.hatchingGround = hatchingGround;
		this.displayMethod = displayMethod;
	}

	public void execute() {
		this.setup();

		while (!this.phaseComplete) {
			this.phaseComplete = true;
			for (Player player : this.players) {
				this.turn(player);
			}
		}

	}

	protected abstract void setup();

	protected abstract void turn(Player player);

}
