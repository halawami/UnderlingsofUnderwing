package game.setup;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import card.Card;
import element.ElementBag;
import game.Deck;
import game.Game;
import game.HatchingGround;
import gui.Gui;
import handler.HandlerFactory;
import player.PlayerFactory;

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

        this.hatchingGround = new HatchingGround(new Deck(this.cards));
        this.game = new Game(EasyMock.mock(Gui.class), this.hatchingGround, new PlayerFactory(new HandlerFactory()),
                EasyMock.mock(ElementBag.class));

    }

    @Test
    public void test2PlayerSize() {
        this.game.setUp(2);
        assertEquals(3, this.game.getHatchingGround().getWidth());
        assertEquals(2, this.game.getHatchingGround().getHeight());
    }

    @Test
    public void test3PlayerSize() {
        this.game.setUp(3);
        assertEquals(4, this.game.getHatchingGround().getWidth());
        assertEquals(3, this.game.getHatchingGround().getHeight());
    }

    @Test
    public void test4PlayerSize() {
        this.game.setUp(4);
        assertEquals(4, this.game.getHatchingGround().getWidth());
        assertEquals(4, this.game.getHatchingGround().getHeight());
    }

    @Test
    public void test6PlayerSize() {
        this.game.setUp(6);
        assertEquals(4, this.game.getHatchingGround().getWidth());
        assertEquals(4, this.game.getHatchingGround().getHeight());
    }

}
