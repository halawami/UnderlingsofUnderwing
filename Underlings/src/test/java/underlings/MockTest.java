package underlings;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.After;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Display;
import underlings.gui.Gui;
import underlings.gui.PromptHandler;
import underlings.handler.HandlerMovementLogic;
import underlings.phase.Phase;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public abstract class MockTest {

    protected Gui gui;
    protected HatchingGround hatchingGround;
    protected HandlerMovementLogic handlerMovementLogic;
    protected ElementSpaceLogic elementSpaceLogic;
    protected Runnable displayMethod;
    protected Player player;
    protected List<Player> players;
    protected ScoreUtils scoreUtils;
    protected Phase dragonPhase;
    protected Deck deck;
    protected Display display;
    protected PromptHandler promptHandler;

    protected List<Object> mocks = new ArrayList<>();

    public <T> T mock(Class<T> mockClass) {
        T mock = EasyMock.mock(mockClass);
        this.mocks.add(mock);
        return mock;
    }

    public void replayAll() {
        EasyMock.replay(this.mocks.toArray());
    }

    @After
    public void verifyAll() {
        EasyMock.verify(this.mocks.toArray());
    }

}
