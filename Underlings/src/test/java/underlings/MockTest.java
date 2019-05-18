package underlings;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.ElementBag;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Display;
import underlings.gui.Gui;
import underlings.gui.PromptHandler;
import underlings.handler.Handler;
import underlings.handler.HandlerMovementLogic;
import underlings.phase.Phase;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;
import underlings.utilities.EggHatchingLogic;

public abstract class MockTest {

    protected Gui gui;
    protected HatchingGround hatchingGround;
    protected HandlerMovementLogic handlerMovementLogic;
    protected ElementSpaceLogic elementSpaceLogic;
    protected Runnable displayMethod;
    protected Player player, player2;
    protected List<Player> players;
    protected ScoreUtils scoreUtils;
    protected Phase dragonPhase;
    protected Deck deck;
    protected Display display;
    protected PromptHandler promptHandler;
    protected ElementBag elementBag;
    protected Card card, card2, card3;
    protected ElementSpace[] elementSpaces;
    protected EggHatchingLogic eggHatchingLogic;
    protected Handler handler;
    protected Effect effect, effect2;
    protected ElementSpace elementSpace, elementSpace2;

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

    public void addMock(Object mock) {
        this.mocks.add(mock);
    }

    public <T> TestUtils<T> mockListOf(Class<T> objectsClass) {
        TestUtils<T> utilsContainingType = new TestUtils<>();
        utilsContainingType.objectsClass = objectsClass;
        return utilsContainingType;
    }

    public class TestUtils<T> {

        private Class<T> objectsClass;

        public List<T> withLength(int length) {
            List<T> mockObjects = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                mockObjects.add(EasyMock.mock(this.objectsClass));
            }
            return mockObjects;
        }

    }

}
