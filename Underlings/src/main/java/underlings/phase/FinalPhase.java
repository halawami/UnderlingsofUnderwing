package underlings.phase;

import java.util.List;

import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public class FinalPhase implements Phase {

	private List<Player> players;

	public FinalPhase(List<Player> players) {
		this.players = players;
	}

	@Override
	public void execute(int turnLeader) {
		System.out.println(new ScoreUtils().calculateScores(this.players));
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
	}

	@Override
	public void turn(Player player) {
		// TODO Auto-generated method stub
	}

}
