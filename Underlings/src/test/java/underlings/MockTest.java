package underlings;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.After;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerMovementLogic;
import underlings.phase.Phase;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public abstract class MockTest {

    protected Gui gui;
    protected HatchingGround hatchingGround;
    protected HandlerMovementLogic handlerMovementLogic;
    protected Runnable displayMethod;
    protected Player player;
    protected List<Player> players;
    protected ScoreUtils scoreUtils;
    protected Phase dragonPhase;

    protected List<Object> mocks = new ArrayList<>();

    public <T> T mock(Class<T> mockClass) {
        T mock = EasyMock.mock(mockClass);
        this.mocks.add(mock);
        return mock;
    }

    public void replay() {
        EasyMock.replay(this.mocks.toArray());
    }

    @After
    public void verify() {
        EasyMock.verify(this.mocks.toArray());
    }

}
