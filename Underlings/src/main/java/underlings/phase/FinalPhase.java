package underlings.phase;

import java.util.List;

import underlings.element.ElementBag;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public class FinalPhase implements Phase {

	private List<Player> players;
	private Phase dragonPhase;

	public FinalPhase(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod, Field field) {
		this.players = players;
		this.dragonPhase = new DragonPhase(players, gui, elementBag, hatchingGround, displayMethod, field);
	}

	@Override
	public void execute(int turnLeader) {

		this.dragonPhase.setup();

		for (Player player : this.players) {
			this.turn(player);
		}
		
		System.out.println(new ScoreUtils().calculateScores(this.players));

	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
	}

	@Override
	public void turn(Player player) {

		this.dragonPhase.turn(player);
		this.dragonPhase.turn(player);

	}

}
