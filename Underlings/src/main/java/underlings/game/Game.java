package underlings.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import underlings.element.ElementBag;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.phase.FinalPhase;
import underlings.phase.Phase;
import underlings.player.Player;
import underlings.player.PlayerFactory;

public class Game {

	private static final int MIN_PLAYERS = 2;
	private static final int MAX_PLAYERS = 6;

	private int maxHandlers;
	private int numberOfPlayers;
	private int roundsLeft;
	private int currentPhase = 0;
	private int turnLeader = 0;
	private HatchingGround hatchingGround;
	private Gui gui;
	private PlayerFactory playerFactory;
	private ElementBag elementBag;

	private List<Player> players = new LinkedList<>();

	public Game(Gui gui, HatchingGround hatchingGround, PlayerFactory playerFactory, ElementBag elementBag) {
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
		this.hatchingGround.setDimensions(correspondingProps.hatchingGroundWidth,
				correspondingProps.hatchingGroundHeight);
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

	public void start(List<Phase> phases, Map<String, FinalPhase> finalPhaseMap) {
		FinalPhase finalPhase = finalPhaseMap.get("Regular");
		this.promptPlayerCount();
		this.setUp(this.numberOfPlayers);

		boolean gameOver = false;
		while (this.roundsLeft > 0 && !gameOver) {

			for (Phase phase : phases) {
				this.currentPhase++;
				this.display();
				phase.execute(this.turnLeader);
				gameOver = phase.isGameComplete();
				if (gameOver) {
					finalPhase = finalPhaseMap.get("Wild");
					System.out.println("here");
					break;
				}
			}
			this.currentPhase = 0;
			this.turnLeader++;
			this.turnLeader %= 4;
			this.roundsLeft--;
		}

		finalPhase.execute();
	}

	public void promptPlayerCount() {
		this.numberOfPlayers = this.gui.getPlayerCount(MIN_PLAYERS, MAX_PLAYERS);
	}

	public int getPlayerCount() {
		return this.numberOfPlayers;
	}

	public void display() {
		this.gui.display.displayBackground();
		this.gui.display.displayHatchingGround(this.hatchingGround);
		this.displayPlayers();
		this.gui.display.displayStats(this.elementBag, this.roundsLeft, this.currentPhase, this.turnLeader + 1);

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
