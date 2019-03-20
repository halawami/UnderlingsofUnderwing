package Game;

import java.util.LinkedList;
import java.util.List;

import GUI.GUI;

public class Game {

    private final int ROUNDS_TWO_PLAYERS = 15, ROUNDS_THREE_PLAYERS = 13, ROUNDS_FOUR_SIX_PLAYERS = 12;
    private final int HATCHING_TWO_WIDTH = 3, HATCHING_THREE_WIDTH = 4, HATCHING_FOUR_SIX_WIDTH = 4;
    private final int HATCHING_TWO_HEIGHT = 2, HATCHING_THREE_HEIGHT = 3, HATCHING_FOUR_SIX_HEIGHT = 4;
    private final int MAX_HANDLERS_TWO = 4, MAX_HANDLERS_THREE = 5, MAX_HANDLERS_FOUR_SIX = 6; 
    private Deck deck;
    private int maxHandlers;
    private GUI gui;
    
    private int roundsLeft;
    private HatchingGround hatchingGround;

    private List<Player> players = new LinkedList<Player>();

    public Game(GUI gui) {
		this.gui = gui;
	}

	public void setUp(int numberOfPlayers, CardFactory cardFactory) {

        this.deck = new Deck(cardFactory.getCards());

        // Set Round Number and Hatching Ground
        switch (numberOfPlayers) {
            case 2:
                this.roundsLeft = this.ROUNDS_TWO_PLAYERS;
                this.hatchingGround = new HatchingGround(this.HATCHING_TWO_WIDTH, this.HATCHING_TWO_HEIGHT, this.deck);
                this.maxHandlers = this.MAX_HANDLERS_TWO;
                break;
            case 3:
                this.roundsLeft = this.ROUNDS_THREE_PLAYERS;
                this.hatchingGround = new HatchingGround(this.HATCHING_THREE_WIDTH, this.HATCHING_THREE_HEIGHT, this.deck);
                this.maxHandlers = this.MAX_HANDLERS_THREE;
                break;
            case 4:
            case 5:
            case 6:
                this.roundsLeft = this.ROUNDS_FOUR_SIX_PLAYERS;
                this.hatchingGround = new HatchingGround(this.HATCHING_FOUR_SIX_WIDTH, this.HATCHING_FOUR_SIX_HEIGHT, this.deck);
                this.maxHandlers = this.MAX_HANDLERS_FOUR_SIX;
                break;
            default:
                throw new IllegalArgumentException("Player count must be between 2 and 6, inclusive");
        }

        // Set Player List
        for (int i = 0; i < numberOfPlayers; i++) {
            this.players.add(new Player(this.maxHandlers));
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
		this.gui.promptPlayerCount();
	}

	public int getPlayerCount() {
		return 2;
	}

}
