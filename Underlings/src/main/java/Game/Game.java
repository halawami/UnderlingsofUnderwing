package Game;

public class Game {

	private int roundsLeft;
	
	public void setUp(int numberOfPlayers) {
		if (numberOfPlayers == 2) {
			this.roundsLeft = 15;
		} else {
			this.roundsLeft = 13;
		}
		
	}

	public int getRoundsLeft() {
		return this.roundsLeft;
	}

	
	
}
