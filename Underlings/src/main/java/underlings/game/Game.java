package underlings.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import underlings.element.ElementBag;
import underlings.gui.Gui;
import underlings.phase.FinalPhase;
import underlings.phase.FinalPhase.FinalPhaseType;
import underlings.phase.Phase;
import underlings.player.Player;
import underlings.player.PlayerFactory;
import underlings.utilities.LocaleWrap;

public class Game {

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 6;

    private int maxHandlers;
    public int numberOfPlayers;
    public int roundsLeft;
    private int currentPhase = 0;
    private int turnLeader = 0;
    public HatchingGround hatchingGround;
    private Gui gui;
    private PlayerFactory playerFactory;
    private ElementBag elementBag;
    private boolean gameOver = false;
    private FinalPhase finalPhase;
    private List<Phase> phases;
    private Map<FinalPhaseType, FinalPhase> finalPhaseMap;

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

    public List<Player> getPlayers() {
        return this.players;
    }

    public void start(List<Phase> phases, Map<FinalPhaseType, FinalPhase> finalPhaseMap) {
        this.phases = phases;
        this.finalPhaseMap = finalPhaseMap;
        this.finalPhase = finalPhaseMap.get(FinalPhaseType.REGULAR);

        this.promptLocale();
        this.promptPlayerCount();
        this.setUp(this.numberOfPlayers);

        this.gameLoop();

        this.display();
        this.finalPhase.execute();
    }

    private void gameLoop() {
        while (this.roundsLeft > 0) {

            for (Phase phase : this.phases) {
                this.currentPhase++;
                this.display();
                phase.execute(this.turnLeader);
                this.gameOver = phase.isGameComplete();
                if (this.gameOver) {
                    this.roundsLeft = 0;
                    this.finalPhase = this.finalPhaseMap.get(FinalPhaseType.WILD);
                    break;
                }
            }
            this.currentPhase = 0;
            this.turnLeader = (this.turnLeader + 1) % this.numberOfPlayers;
            this.roundsLeft--;
        }
    }

    public void promptLocale() {
        Locale locale = this.gui.promptLocale(Locale.getAvailableLocales());
        LocaleWrap.locale = locale;
    }

    public void promptPlayerCount() {
        this.numberOfPlayers = this.gui.getPlayerCount(MIN_PLAYERS, MAX_PLAYERS);
    }

    public void display() {
        this.gui.display(this.roundsLeft, this.currentPhase, this.turnLeader, this.hatchingGround, this.players,
                this.elementBag);
    }
}
