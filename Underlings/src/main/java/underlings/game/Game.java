package underlings.game;

import java.util.LinkedList;
import java.util.List;

import underlings.gui.GUI;
import underlings.player.Player;
import underlings.player.PlayerFactory;

public class Game {

	private final static int ROUNDS_TWO_PLAYERS = 15, ROUNDS_THREE_PLAYERS = 13, ROUNDS_FOUR_SIX_PLAYERS = 12;
	private final static int HATCHING_TWO_WIDTH = 3, HATCHING_THREE_WIDTH = 4, HATCHING_FOUR_SIX_WIDTH = 4;
	private final static int HATCHING_TWO_HEIGHT = 2, HATCHING_THREE_HEIGHT = 3, HATCHING_FOUR_SIX_HEIGHT = 4;
	private final static int MAX_HANDLERS_TWO = 4, MAX_HANDLERS_THREE = 5, MAX_HANDLERS_FOUR_SIX = 6;
	private final static int MIN_PLAYERS = 2, MAX_PLAYERS = 6;

	private final static int[] NUMBER_OF_ROUNDS = { ROUNDS_TWO_PLAYERS, ROUNDS_THREE_PLAYERS, ROUNDS_FOUR_SIX_PLAYERS,
			ROUNDS_FOUR_SIX_PLAYERS, ROUNDS_FOUR_SIX_PLAYERS };
	private final static int[] HATCHING_GROUND_WIDTH = { HATCHING_TWO_WIDTH, HATCHING_THREE_WIDTH, HATCHING_FOUR_SIX_WIDTH,
			HATCHING_FOUR_SIX_WIDTH, HATCHING_FOUR_SIX_WIDTH };
	private final static int[] HATCHING_GROUND_HEIGHT = { HATCHING_TWO_HEIGHT, HATCHING_THREE_HEIGHT, HATCHING_FOUR_SIX_HEIGHT,
			HATCHING_FOUR_SIX_HEIGHT, HATCHING_FOUR_SIX_HEIGHT };
	private final static int[] MAX_HANDLERS = { MAX_HANDLERS_TWO, MAX_HANDLERS_THREE, MAX_HANDLERS_FOUR_SIX,
			MAX_HANDLERS_FOUR_SIX, MAX_HANDLERS_FOUR_SIX };

	private int maxHandlers, numberOfPlayers, roundsLeft;

	private HatchingGround hatchingGround;
	private GUI gui;
	private PlayerFactory playerFactory;

	private List<Player> players = new LinkedList<Player>();

	public Game(GUI gui, HatchingGround hatchingGround, PlayerFactory playerFactory) {
		this.gui = gui;
		this.hatchingGround = hatchingGround;
		this.playerFactory = playerFactory;
	}

	public void setUp(int numberOfPlayers) {
		if (numberOfPlayers < MIN_PLAYERS || numberOfPlayers > MAX_PLAYERS) {
			throw new IllegalArgumentException("Player count must be between 2 and 6, inclusive");
		}

		this.setUpProperties(numberOfPlayers);
		this.hatchingGround.populate();
		this.setUpPlayerList(numberOfPlayers);
	}

	private void setUpProperties(int numberOfPlayers) {
		int propertyIndex = numberOfPlayers - this.MIN_PLAYERS;

		this.roundsLeft = this.NUMBER_OF_ROUNDS[propertyIndex];
		this.hatchingGround.setDimensions(this.HATCHING_GROUND_WIDTH[propertyIndex],
				HATCHING_GROUND_HEIGHT[propertyIndex]);
		this.maxHandlers = this.MAX_HANDLERS[propertyIndex];
	}

	public void setUpPlayerList(int numberOfPlayers) {
		for (int i = 0; i < numberOfPlayers; i++) {
			this.players.add(this.playerFactory.createPlayer(this.maxHandlers));
		}
	}

	public int getRoundsLeft() {
		return this.roundsLeft;
	}

	public HatchingGround getHatchingGround() {
		return this.hatchingGround;
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public void start() {
		this.numberOfPlayers = this.gui.promptPlayerCount();
		this.setUp(this.numberOfPlayers);
	}

	public int getPlayerCount() {
		return this.numberOfPlayers;
	}

}