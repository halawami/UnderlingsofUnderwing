package Game;

public class Game {

	private final int ROUNDS_TWO_PLAYERS = 15, ROUNDS_THREE_PLAYERS = 13, ROUNDS_FOUR_SIX_PLAYERS = 12;

	private int roundsLeft;
	private HatchingGround hatchingGround;

	public void setUp(int numberOfPlayers) {
		
		// Set Round Number
		switch (numberOfPlayers) {
		case 2:
			this.roundsLeft = ROUNDS_TWO_PLAYERS;
			break;
		case 3:
			this.roundsLeft = ROUNDS_THREE_PLAYERS;
			break;
		case 4:
		case 5:
		case 6:
			this.roundsLeft = ROUNDS_FOUR_SIX_PLAYERS;
			break;
		default:
			throw new IllegalArgumentException("Player count must be between 2 and 6, inclusive");
		}

		// Setup Hatching Ground
		hatchingGround = new HatchingGround();
		
	}

	public int getRoundsLeft() {
		return this.roundsLeft;
	}

	public HatchingGround getHatchingGround() {
		return this.hatchingGround;
	}

}
