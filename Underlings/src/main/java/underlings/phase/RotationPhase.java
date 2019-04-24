package underlings.phase;

import java.util.List;

import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.player.Player;

public abstract class RotationPhase implements Phase {

	protected List<Player> players;
	public GUI gui;
	protected ElementBag elementBag;
	protected HatchingGround hatchingGround;
	protected Runnable displayMethod;

	protected boolean phaseComplete;

	public RotationPhase(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod) {
		this.players = players;
		this.gui = gui;
		this.elementBag = elementBag;
		this.hatchingGround = hatchingGround;
		this.displayMethod = displayMethod;
	}

	@Override
	public void execute() {
		this.setup();

		while (!this.phaseComplete) {
			this.phaseComplete = true;
			for (Player player : this.players) {
				this.turn(player);
			}
		}

	}

	@Override
	public abstract void setup();

	@Override
	public abstract void turn(Player player);

}
