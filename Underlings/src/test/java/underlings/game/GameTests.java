package underlings.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.element.ElementBag;
import underlings.gui.Gui;
import underlings.hatchingground.HatchingGround;
import underlings.phase.FinalPhase;
import underlings.phase.FinalPhase.FinalPhaseType;
import underlings.phase.Phase;
import underlings.player.Player;
import underlings.player.PlayerFactory;

public class GameTests {

    private Gui gui;
    private HatchingGround hatchingGround;
    private PlayerFactory playerFactory;
    private ElementBag elementBag;
    private Game game;

    @Before
    public void init() {
        this.gui = EasyMock.mock(Gui.class);
        this.hatchingGround = EasyMock.mock(HatchingGround.class);
        this.playerFactory = EasyMock.mock(PlayerFactory.class);
        this.elementBag = EasyMock.mock(ElementBag.class);

        this.game = new Game(this.gui, this.hatchingGround, this.playerFactory, this.elementBag);
    }

    @Test
    public void testDisplay() {
        this.gui.display(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(),
                EasyMock.anyObject(), EasyMock.anyObject());

        EasyMock.replay(this.gui, this.hatchingGround, this.playerFactory, this.elementBag);

        this.game.display();

        EasyMock.verify(this.gui, this.hatchingGround, this.playerFactory, this.elementBag);
    }

    @Test
    public void testSetup() {
        Map<FinalPhaseType, FinalPhase> finalPhaseMap = new HashMap<>();
        FinalPhase finalPhase = EasyMock.mock(FinalPhase.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        finalPhaseMap.put(FinalPhaseType.REGULAR, finalPhase);

        Game mockedGame = EasyMock.createMockBuilder(Game.class).addMockedMethod("promptPlayerCount")
                .addMockedMethod("gameLoop").addMockedMethod("display").addMockedMethod("setUpProperties")
                .addMockedMethod("setUpPlayerList").createMock();
        mockedGame.hatchingGround = hatchingGround;

        mockedGame.numberOfPlayers = 2;

        mockedGame.promptPlayerCount();

        mockedGame.setUpProperties(EasyMock.anyInt());
        hatchingGround.populate();
        mockedGame.setUpPlayerList(EasyMock.anyInt());
        mockedGame.gameLoop();
        mockedGame.display();
        finalPhase.execute();

        EasyMock.replay(mockedGame, finalPhase, hatchingGround);

        List<Phase> phases = new ArrayList<>();
        mockedGame.start(phases, finalPhaseMap);

        EasyMock.verify(mockedGame, finalPhase, hatchingGround);

        assertEquals(mockedGame.phases, phases);
        assertEquals(mockedGame.finalPhaseMap, finalPhaseMap);
        assertEquals(mockedGame.finalPhase, finalPhase);

    }

    @Test
    public void testGameLoopRoundsCompleted() {

        List<Phase> phases = new ArrayList<>();
        Phase phaseOne = EasyMock.mock(Phase.class);
        Phase phaseTwo = EasyMock.mock(Phase.class);
        phases.add(phaseOne);
        phases.add(phaseTwo);

        Gui gui = EasyMock.mock(Gui.class);
        Game mockedGame = EasyMock.createMockBuilder(Game.class).addMockedMethod("checkGameover")
                .withConstructor(Gui.class, HatchingGround.class, PlayerFactory.class, ElementBag.class)
                .withArgs(gui, null, null, null).createMock();

        mockedGame.numberOfPlayers = 2;
        mockedGame.roundsLeft = 2;
        mockedGame.phases = phases;

        List<Player> players = new ArrayList<>();
        gui.display(2, 1, 0, null, players, null);
        phaseOne.execute(0);
        EasyMock.expect(mockedGame.checkGameover(phaseOne)).andReturn(false);

        gui.display(2, 2, 0, null, players, null);
        phaseTwo.execute(0);
        EasyMock.expect(mockedGame.checkGameover(phaseTwo)).andReturn(false);

        gui.display(1, 1, 1, null, players, null);
        phaseOne.execute(1);
        EasyMock.expect(mockedGame.checkGameover(phaseOne)).andReturn(false);

        gui.display(1, 2, 1, null, players, null);
        phaseTwo.execute(1);
        EasyMock.expect(mockedGame.checkGameover(phaseTwo)).andReturn(false);

        EasyMock.replay(phaseOne, phaseTwo, mockedGame, gui);

        mockedGame.gameLoop();

        EasyMock.verify(phaseOne, phaseTwo, mockedGame, gui);

    }

    @Test
    public void testGameLoopWildHatched() {

        List<Phase> phases = new ArrayList<>();
        Phase phaseOne = EasyMock.mock(Phase.class);
        Phase phaseTwo = EasyMock.mock(Phase.class);
        phases.add(phaseOne);
        phases.add(phaseTwo);

        Map<FinalPhaseType, FinalPhase> finalPhaseMap = new HashMap<>();
        FinalPhase wildFinalPhase = EasyMock.mock(FinalPhase.class);
        finalPhaseMap.put(FinalPhaseType.WILD, wildFinalPhase);

        Game mockedGame = EasyMock.createMockBuilder(Game.class).addMockedMethod("display").createMock();

        mockedGame.numberOfPlayers = 2;
        mockedGame.roundsLeft = 2;
        mockedGame.finalPhaseMap = finalPhaseMap;
        mockedGame.phases = phases;

        mockedGame.display();
        phaseOne.execute(0);
        phaseOne.gameComplete = true;

        FinalPhase finalPhase = EasyMock.mock(FinalPhase.class);
        EasyMock.replay(phaseOne, phaseTwo, wildFinalPhase, finalPhase, mockedGame);

        mockedGame.gameLoop();

        EasyMock.verify(phaseOne, phaseTwo, wildFinalPhase, finalPhase, mockedGame);

        assertEquals(mockedGame.finalPhase, wildFinalPhase);

    }

}
