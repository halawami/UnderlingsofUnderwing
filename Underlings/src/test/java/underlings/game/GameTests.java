package underlings.game;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.element.ElementBag;
import underlings.gui.Gui;
import underlings.phase.FinalPhase;
import underlings.phase.FinalPhase.FinalPhaseType;
import underlings.phase.Phase;
import underlings.player.PlayerFactory;
import underlings.utilities.LocaleWrap;

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
    public void testPromptLocale() {
        EasyMock.expect(this.gui.promptLocale(EasyMock.anyObject())).andReturn(Locale.FRENCH);

        EasyMock.replay(this.gui, this.hatchingGround, this.playerFactory, this.elementBag);

        this.game.promptLocale();

        EasyMock.verify(this.gui, this.hatchingGround, this.playerFactory, this.elementBag);
        assertEquals(Locale.FRENCH, LocaleWrap.locale);
    }

    @Test
    public void testSetup() {
        List<Phase> phases = new ArrayList<>();
        Map<FinalPhaseType, FinalPhase> finalPhaseMap = new HashMap<>();
        FinalPhase finalPhase = EasyMock.mock(FinalPhase.class);
        finalPhaseMap.put(FinalPhaseType.REGULAR, finalPhase);

        Game mockedGame = EasyMock.createMockBuilder(Game.class).addMockedMethod("promptLocale")
                .addMockedMethod("promptPlayerCount").addMockedMethod("setUp").addMockedMethod("gameLoop")
                .addMockedMethod("display").createMock();

        mockedGame.numberOfPlayers = 2;

        mockedGame.promptLocale();
        mockedGame.promptPlayerCount();
        mockedGame.setUp(2);
        mockedGame.gameLoop();
        mockedGame.display();
        finalPhase.execute();

        EasyMock.replay(mockedGame, finalPhase);

        mockedGame.start(phases, finalPhaseMap);

        EasyMock.verify(mockedGame, finalPhase);

        assertEquals(mockedGame.phases, phases);
        assertEquals(mockedGame.finalPhaseMap, finalPhaseMap);
        assertEquals(mockedGame.finalPhase, finalPhase);

    }

}
