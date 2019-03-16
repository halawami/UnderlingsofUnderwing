package Game;

public class Game {

	private int roundsLeft;
	
	public void setUp(int numberOfPlayers) {
		switch(numberOfPlayers) {
		case 2:
			this.roundsLeft = 15;
			break;
		case 3:
			this.roundsLeft = 13;
			break;
		case 4:
		case 5:
		case 6:
			this.roundsLeft = 12;
			break;
		default:
			throw new IllegalArgumentException("Player count must be between 2 and 6, inclusive");
		}
		
	}

	public int getRoundsLeft() {
		return this.roundsLeft;
	}

	
	
}
