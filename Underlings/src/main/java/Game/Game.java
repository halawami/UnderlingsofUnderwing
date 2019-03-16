package Game;

public class Game {

	private final int ROUNDS_TWO_PLAYERS = 15, ROUNDS_THREE_PLAYERS = 13, ROUNDS_FOUR_SIX_PLAYERS = 12;
	private final int HATCHING_TWO_WIDTH = 3, HATCHING_THREE_WIDTH = 4;
	private final int HATCHING_TWO_HEIGHT = 2, HATCHING_THREE_HEIGHT = 3;

	private int roundsLeft;
	private HatchingGround hatchingGround;

	public void setUp(int numberOfPlayers) {
		
		// Set Round Number and Hatching Ground
		switch (numberOfPlayers) {
		case 2:
			this.roundsLeft = ROUNDS_TWO_PLAYERS;
			this.hatchingGround = new HatchingGround(HATCHING_TWO_WIDTH, HATCHING_TWO_HEIGHT);
			break;
		case 3:
			this.roundsLeft = ROUNDS_THREE_PLAYERS;
			this.hatchingGround = new HatchingGround(HATCHING_THREE_WIDTH, HATCHING_THREE_HEIGHT);
			break;
		case 4:
		case 5:
		case 6:
			this.roundsLeft = ROUNDS_FOUR_SIX_PLAYERS;
			break;
		default:
			throw new IllegalArgumentException("Player count must be between 2 and 6, inclusive");
		}

		
	}

	public int getRoundsLeft() {
		return this.roundsLeft;
	}

	public HatchingGround getHatchingGround() {
		return this.hatchingGround;
	}

}
