package Game;

public class Game {

	private int roundsLeft;
	
	public void setUp(int numberOfPlayers) {
		if (numberOfPlayers == 2) {
			this.roundsLeft = 15;
		} else if (numberOfPlayers == 3) {
			this.roundsLeft = 13;
		}
		else {
			this.roundsLeft = 12;
		}
		
	}

	public int getRoundsLeft() {
		return this.roundsLeft;
	}

	
	
}
