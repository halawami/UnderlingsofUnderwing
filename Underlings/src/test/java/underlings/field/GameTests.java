package underlings.field;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.Card;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Game;
import underlings.handler.WildHandler;
import underlings.hatchingground.Deck;
import underlings.hatchingground.HatchingGround;
import underlings.phase.Phase;

public class GameTests {

    @Test
    public void testCheckGameoverPhaseEnd() {
        Deck deck = EasyMock.mock(Deck.class);
        ElementSpaceLogic logic = EasyMock.niceMock(ElementSpaceLogic.class);
        HatchingGround hatchingGround = new HatchingGround(deck, logic);

        Phase phase = EasyMock.mock(Phase.class);
        phase.gameComplete = true;

        EasyMock.replay(deck, logic, phase);

        Game game = new Game(null, hatchingGround, null, null);
        assertTrue(game.checkGameover(phase));

        EasyMock.verify(deck, logic, phase);
    }

    @Test
    public void testCheckGameoverNoCards() {
        Deck deck = EasyMock.mock(Deck.class);
        ElementSpaceLogic logic = EasyMock.niceMock(ElementSpaceLogic.class);
        HatchingGround hatchingGround = new HatchingGround(deck, logic);
        hatchingGround.setDimensions(0, 0);
        hatchingGround.populate();

        Phase phase = EasyMock.mock(Phase.class);
        phase.gameComplete = false;

        EasyMock.replay(deck, logic, phase);

        Game game = new Game(null, hatchingGround, null, null);
        assertTrue(game.checkGameover(phase));

        EasyMock.verify(deck, logic, phase);
    }

    @Test
    public void testCheckGameoverWithCardsTrue() {
        Deck deck = EasyMock.mock(Deck.class);
        Card card1 = new Card();
        EasyMock.expect(deck.draw()).andReturn(card1);
        Card card2 = new Card();
        EasyMock.expect(deck.draw()).andReturn(card2);

        Phase phase = EasyMock.mock(Phase.class);
        phase.gameComplete = false;

        ElementSpaceLogic logic = EasyMock.niceMock(ElementSpaceLogic.class);

        EasyMock.replay(deck, logic, phase);

        HatchingGround hatchingGround = new HatchingGround(deck, logic);
        hatchingGround.setDimensions(1, 2);
        hatchingGround.populate();

        card1.handler = WildHandler.getInstance();
        card2.handler = WildHandler.getInstance();

        Game game = new Game(null, hatchingGround, null, null);
        assertTrue(game.checkGameover(phase));

        EasyMock.verify(deck, logic, phase);
    }

    @Test
    public void testCheckGameoverWithCardsFalse() {
        Deck deck = EasyMock.mock(Deck.class);
        Card card1 = new Card();
        EasyMock.expect(deck.draw()).andReturn(card1);
        Card card2 = new Card();
        EasyMock.expect(deck.draw()).andReturn(card2);

        Phase phase = EasyMock.mock(Phase.class);
        phase.gameComplete = false;

        ElementSpaceLogic logic = EasyMock.niceMock(ElementSpaceLogic.class);

        EasyMock.replay(deck, logic, phase);

        HatchingGround hatchingGround = new HatchingGround(deck, logic);
        hatchingGround.setDimensions(1, 2);
        hatchingGround.populate();

        card1.handler = WildHandler.getInstance();

        Game game = new Game(null, hatchingGround, null, null);
        assertFalse(game.checkGameover(phase));

        EasyMock.verify(deck, logic, phase);
    }
}
