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
import underlings.gui.Display;
import underlings.gui.Gui;
import underlings.gui.PromptHandler;
import underlings.handler.Handler;
import underlings.handler.HandlerMovementLogic;
import underlings.hatchingground.Deck;
import underlings.hatchingground.HatchingGround;
import underlings.phase.Phase;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;
import underlings.utilities.EggHatchingUtilities;

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
    protected ElementBag elementBag;
    protected Card card;
    protected Card card2;
    protected ElementSpace[] elementSpaces;
    protected EggHatchingUtilities eggHatchingLogic;
    protected Handler handler;
    protected Effect effect;
    protected Effect effect2;
    protected ElementSpace elementSpace;
    protected ElementSpace elementSpace2;
    protected List<Card> cards;

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

    public <T> MockListHelper<T> mockListOf(Class<T> objectsClass) {
        MockListHelper<T> utilsContainingType = new MockListHelper<>();
        utilsContainingType.objectsClass = objectsClass;
        return utilsContainingType;
    }

    public class MockListHelper<T> {

        private Class<T> objectsClass;

        public List<T> withLengthOf(int length) {
            List<T> mockObjects = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                T mock = EasyMock.mock(this.objectsClass);
                mockObjects.add(mock);
                MockTest.this.mocks.add(mock);
            }
            return mockObjects;
        }

    }

}
