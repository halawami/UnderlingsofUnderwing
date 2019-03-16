package Game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Game {

    private final int ROUNDS_TWO_PLAYERS = 15, ROUNDS_THREE_PLAYERS = 13, ROUNDS_FOUR_SIX_PLAYERS = 12;
    private final int HATCHING_TWO_WIDTH = 3, HATCHING_THREE_WIDTH = 4, HATCHING_FOUR_SIX_WIDTH = 4;
    private final int HATCHING_TWO_HEIGHT = 2, HATCHING_THREE_HEIGHT = 3, HATCHING_FOUR_SIX_HEIGHT = 4;
    private Deck deck;

    private int roundsLeft;
    private HatchingGround hatchingGround;

    private List<Player> players = new LinkedList<Player>();

    public void setUp(int numberOfPlayers) {

        Stack<Card> cards = new Stack<>();
        for (int i = 0; i < 16; i++) {
            cards.push(new Card());
        }
        this.deck = new Deck(cards);


        // Set Round Number and Hatching Ground
        switch (numberOfPlayers) {
            case 2:
                this.roundsLeft = ROUNDS_TWO_PLAYERS;
                this.hatchingGround = new HatchingGround(HATCHING_TWO_WIDTH, HATCHING_TWO_HEIGHT, deck);
                break;
            case 3:
                this.roundsLeft = ROUNDS_THREE_PLAYERS;
                this.hatchingGround = new HatchingGround(HATCHING_THREE_WIDTH, HATCHING_THREE_HEIGHT, deck);
                break;
            case 4:
            case 5:
            case 6:
                this.roundsLeft = ROUNDS_FOUR_SIX_PLAYERS;
                this.hatchingGround = new HatchingGround(HATCHING_FOUR_SIX_WIDTH, HATCHING_FOUR_SIX_HEIGHT, deck);
                break;
            default:
                throw new IllegalArgumentException("Player count must be between 2 and 6, inclusive");
        }

        // Set Player List
        for (int i = 0; i < numberOfPlayers; i++) {
            this.players.add(new Player());
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

}
