package underlings.game.setup;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Stack;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Deck;
import underlings.game.Game;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.player.PlayerFactory;

public class HatchingGroundTests {

    private Game game;
    private HatchingGround hatchingGround;
    private Stack<Card> cards;

    @Before
    public void init() {

        this.cards = new Stack<Card>();
        for (int i = 0; i < 50; i++) {
            this.cards.push(new Card());
        }

        this.hatchingGround = new HatchingGround(new Deck(this.cards), new ElementSpaceLogic(Arrays.asList()));
        this.game = new Game(EasyMock.mock(Gui.class), this.hatchingGround,
                new PlayerFactory(new HandlerFactory(), Arrays.asList()), EasyMock.mock(ElementBag.class));

    }

    @Test
    public void test2PlayerSize() {
        this.game.setUp(2);
        assertEquals(3, this.game.hatchingGround.getWidth());
        assertEquals(2, this.game.hatchingGround.getHeight());
    }

    @Test
    public void test3PlayerSize() {
        this.game.setUp(3);
        assertEquals(4, this.game.hatchingGround.getWidth());
        assertEquals(3, this.game.hatchingGround.getHeight());
    }

    @Test
    public void test4PlayerSize() {
        this.game.setUp(4);
        assertEquals(4, this.game.hatchingGround.getWidth());
        assertEquals(4, this.game.hatchingGround.getHeight());
    }

    @Test
    public void test6PlayerSize() {
        this.game.setUp(6);
        assertEquals(4, this.game.hatchingGround.getWidth());
        assertEquals(4, this.game.hatchingGround.getHeight());
    }

}
