package underlings.game.setup;

import static org.junit.Assert.assertEquals;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.element.ElementBag;
import underlings.game.Game;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.player.PlayerFactory;

public class GameTests {

    private Game game;

    @Before
    public void init() throws IOException {
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        PlayerFactory factory = new PlayerFactory(new HandlerFactory(), recipes);

        this.game = new Game(EasyMock.mock(Gui.class), EasyMock.mock(HatchingGround.class), factory,
                EasyMock.mock(ElementBag.class));
    }

    @Test
    public void test2PlayerCount() {
        this.game.setUp(2);
        assertEquals(2, this.game.getPlayers().size());
    }

    @Test
    public void test6PlayerCount() {
        this.game.setUp(6);
        assertEquals(6, this.game.getPlayers().size());
    }

    @Test
    public void test2PlayerRounds() {
        this.game.setUp(2);
        assertEquals(15, this.game.getRoundsLeft());
    }

    @Test
    public void test3PlayerRounds() {
        this.game.setUp(3);
        assertEquals(13, this.game.getRoundsLeft());
    }

    @Test
    public void test4PlayerRounds() {
        this.game.setUp(4);
        assertEquals(12, this.game.getRoundsLeft());
    }

    @Test
    public void test6PlayerRounds() {
        this.game.setUp(6);
        assertEquals(12, this.game.getRoundsLeft());
    }

}
