package underlings.game;

import java.util.LinkedList;
import java.util.List;

import underlings.element.ElementBag;
import underlings.gui.GUI;
import underlings.handler.Handler;
import underlings.phase.Phase;
import underlings.player.Player;
import underlings.player.PlayerFactory;

public class Game {
	private final static int MIN_PLAYERS = 2, MAX_PLAYERS = 6;

	private int maxHandlers;
	private int numberOfPlayers;
	private int roundsLeft;
	private HatchingGround hatchingGround;
	private GUI gui;
	private PlayerFactory playerFactory;
	private ElementBag elementBag;

	private List<Player> players = new LinkedList<>();

	public Game(GUI gui, HatchingGround hatchingGround, PlayerFactory playerFactory, ElementBag elementBag) {
		this.gui = gui;
		this.hatchingGround = hatchingGround;
		this.playerFactory = playerFactory;
		this.elementBag = elementBag;
	}

	public void setUp(int numberOfPlayers) {
		this.setUpProperties(numberOfPlayers);
		this.hatchingGround.populate();
		this.setUpPlayerList(numberOfPlayers);
	}

	private void setUpProperties(int numberOfPlayers) {
		GameProperties correspondingProps = GameProperties.getPropertiesOf(numberOfPlayers);

		this.roundsLeft = correspondingProps.numberOfRounds;
		this.hatchingGround.setDimensions(correspondingProps.hatchingGroundWidth, correspondingProps.hatchingGroundHeight);
		this.maxHandlers = correspondingProps.maxHandlers;
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

	public void start(List<Phase> phases) {
		this.promptPlayerCount();
		this.setUp(this.numberOfPlayers);

		this.display();

		for (Phase phase : phases) {
			phase.execute();
		}
	}

	public void promptPlayerCount() {
		this.numberOfPlayers = this.gui.getPlayerCount(MIN_PLAYERS, MAX_PLAYERS);
	}

	public int getPlayerCount() {
		return this.numberOfPlayers;
	}

	public void display() {
		this.gui.display.displayBackground();
		this.hatchingGround.display(this.gui);
		this.displayPlayers();

		this.gui.display.update();
	}

	public void displayPlayers() {
		for (int playerNumber = 0; playerNumber < this.players.size(); playerNumber++) {
			Player player = this.players.get(playerNumber);
			this.gui.display.displayPlayer(playerNumber, player);
			List<Handler> handlers = player.getHandlers();
			this.gui.display.displayHandlers(playerNumber, handlers);
		}
	}
}
