package underlings.game.setup;

import static org.junit.Assert.assertEquals;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.element.ElementBag;
import underlings.game.Game;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerState;
import underlings.player.Player;
import underlings.player.PlayerFactory;

public class HandlerTests {

    private Game game;

    @Before
    public void init() throws Exception {
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        PlayerFactory factory = new PlayerFactory(new HandlerFactory(), recipes);
        this.game = new Game(EasyMock.mock(Gui.class), EasyMock.mock(HatchingGround.class), factory,
                EasyMock.mock(ElementBag.class));
    }

    @Test
    public void test2PlayerHandlerCount() {
        this.game.setUp(2);

        for (Player player : this.game.getPlayers()) {
            assertEquals(2, player.getHandlerCount());
        }
    }

    @Test
    public void test6PlayerHandlerCount() {
        this.game.setUp(6);

        for (Player player : this.game.getPlayers()) {
            assertEquals(2, player.getHandlerCount());
        }
    }

    @Test
    public void test2PlayerReadyRoom() {
        this.game.setUp(2);
        for (Player player : this.game.getPlayers()) {
            for (Handler handler : player.handlers) {
                assertEquals(HandlerState.READY_ROOM, handler.getState());
            }
        }
    }

    @Test
    public void test6PlayerReadyRoom() {
        this.game.setUp(6);
        for (Player player : this.game.getPlayers()) {
            for (Handler handler : player.handlers) {
                assertEquals(HandlerState.READY_ROOM, handler.getState());
            }
        }
    }

    @Test
    public void test2PlayerMaxHandlers() {
        this.game.setUp(2);
        for (Player player : this.game.getPlayers()) {
            assertEquals(4, player.maxHandlers);
        }
    }

    @Test
    public void test3PlayerMaxHandlers() {
        this.game.setUp(3);
        for (Player player : this.game.getPlayers()) {
            assertEquals(5, player.maxHandlers);
        }
    }

    @Test
    public void test4PlayerMaxHandlers() {
        this.game.setUp(4);
        for (Player player : this.game.getPlayers()) {
            assertEquals(6, player.maxHandlers);
        }
    }

    @Test
    public void test6PlayerMaxHandlers() {
        this.game.setUp(6);
        for (Player player : this.game.getPlayers()) {
            assertEquals(6, player.maxHandlers);
        }
    }

}
