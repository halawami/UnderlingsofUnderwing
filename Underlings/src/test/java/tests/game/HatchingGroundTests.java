package tests.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.handler.Handler;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;

public class HatchingGroundTests {

    @Test
    public void testInitUnclaimedEggs() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(6);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        EasyMock.verify(deck);

        assertEquals(6, hatchingGround.getUnclaimedEggs().size());
    }

    @Test
    public void testNotAllUnclaimedEggs() {
        Deck deck = EasyMock.strictMock(Deck.class);
        Card card = new Card();
        card.handler = new Handler(HandlerState.CARD);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(5);
        EasyMock.expect(deck.draw()).andReturn(card);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        EasyMock.verify(deck);

        assertEquals(5, hatchingGround.getUnclaimedEggs().size());
    }

    @Test
    public void testDeal3By2() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(6);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testDeal4By3() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(12);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(4, 3);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testDeal4By4() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(16);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testPullAndReplaceOneCompleteEggs() {
        final Deck deck = EasyMock.strictMock(Deck.class);
        final Card card = new Card();
        Card card2 = new Card();
        Card fakeCard = new Card();
        ElementSpace[] fakeSpaces = {new ElementSpace(ElementColor.PURPLE)};
        card2.elementSpaces = fakeSpaces;
        fakeCard.elementSpaces = fakeSpaces;
        card2.elementSpaces[0].elements = new ArrayList<>();
        fakeCard.elementSpaces[0].elements = new ArrayList<>();
        card2.name = "temp";
        Handler handler = EasyMock.mock(Handler.class);
        ElementSpace[] spaces = {new ElementSpace(ElementColor.PURPLE)};
        card.elementSpaces = spaces;
        spaces[0].elements = Arrays.asList(ElementColor.PURPLE);
        card.handler = handler;
        EasyMock.expect(deck.draw()).andReturn(card);
        EasyMock.expect(deck.draw()).andReturn(fakeCard).times(15);
        EasyMock.expect(deck.draw()).andReturn(card2);

        EasyMock.replay(deck, handler);
        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();
        assertEquals(card, hatchingGround.cards[0][0]);
        assertEquals(Arrays.asList(card),
            hatchingGround.pullAndReplaceCompleteEggs());

        EasyMock.verify(deck, handler);
        assertEquals(hatchingGround.cards[0][0], card2);
    }

    @Test
    public void testPullAndReplaceTwoCompleteEggs() {
        final Deck deck = EasyMock.strictMock(Deck.class);
        final Card card = new Card();
        Card card2 = new Card();
        Card fakeCard = new Card();
        ElementSpace[] fakeSpaces = {new ElementSpace(ElementColor.PURPLE)};
        card2.elementSpaces = fakeSpaces;
        fakeCard.elementSpaces = fakeSpaces;
        card2.elementSpaces[0].elements = new ArrayList<>();
        fakeCard.elementSpaces[0].elements = new ArrayList<>();
        card2.name = "temp";
        Handler handler = EasyMock.mock(Handler.class);
        ElementSpace[] spaces = {new ElementSpace(ElementColor.PURPLE)};
        card.elementSpaces = spaces;
        spaces[0].elements = Arrays.asList(ElementColor.PURPLE);
        card.handler = handler;
        EasyMock.expect(deck.draw()).andReturn(card).times(2);
        EasyMock.expect(deck.draw()).andReturn(fakeCard).times(14);
        EasyMock.expect(deck.draw()).andReturn(card2).times(2);

        EasyMock.replay(deck, handler);
        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();
        assertEquals(card, hatchingGround.cards[0][0]);
        assertEquals(card, hatchingGround.cards[0][1]);
        assertEquals(Arrays.asList(card, card),
            hatchingGround.pullAndReplaceCompleteEggs());

        EasyMock.verify(deck, handler);
        assertEquals(hatchingGround.cards[0][0], card2);
        assertEquals(hatchingGround.cards[0][1], card2);
    }

    @Test
    public void testPullAndReplaceNoCompleteEggs() {
        Deck deck = EasyMock.strictMock(Deck.class);
        Handler handler = EasyMock.mock(Handler.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(16);

        EasyMock.replay(deck, handler);
        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();

        EasyMock.verify(deck, handler);
    }

    @Test
    public void testPullAndReplaceOneCompleteEggsWithTwoElementSpacesFail() {
        final Deck deck = EasyMock.strictMock(Deck.class);
        final Card card = new Card();
        Card card2 = new Card();
        Card fakeCard = new Card();
        ElementSpace[] fakeSpaces = {new ElementSpace(ElementColor.PURPLE)};
        card2.elementSpaces = fakeSpaces;
        fakeCard.elementSpaces = fakeSpaces;
        card2.elementSpaces[0].elements = new ArrayList<>();
        fakeCard.elementSpaces[0].elements = new ArrayList<>();
        card2.name = "temp";
        final Handler handler = EasyMock.mock(Handler.class);
        ElementSpace[] spaces = {new ElementSpace(ElementColor.PURPLE),
            new ElementSpace(ElementColor.BLACK)};
        card.elementSpaces = spaces;
        spaces[0].elements = Arrays.asList(ElementColor.PURPLE);
        spaces[1].elements = Arrays.asList(ElementColor.RED);
        card.handler = handler;
        EasyMock.expect(deck.draw()).andReturn(card);
        EasyMock.expect(deck.draw()).andReturn(fakeCard).times(15);

        EasyMock.replay(deck, handler);
        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();
        assertEquals(card, hatchingGround.cards[0][0]);
        assertEquals(Arrays.asList(),
            hatchingGround.pullAndReplaceCompleteEggs());

        EasyMock.verify(deck, handler);
    }

    @Test
    public void testPullAndReplaceOneCompleteEggsWithTwoElementSpacesPass() {
        final Deck deck = EasyMock.strictMock(Deck.class);
        final Card card = new Card();
        Card card2 = new Card();
        Card fakeCard = new Card();
        ElementSpace[] fakeSpaces = {new ElementSpace(ElementColor.PURPLE)};
        card2.elementSpaces = fakeSpaces;
        fakeCard.elementSpaces = fakeSpaces;
        card2.elementSpaces[0].elements = new ArrayList<>();
        fakeCard.elementSpaces[0].elements = new ArrayList<>();
        card2.name = "temp";
        final Handler handler = EasyMock.mock(Handler.class);
        ElementSpace[] spaces = {new ElementSpace(ElementColor.PURPLE),
            new ElementSpace(ElementColor.BLACK)};
        card.elementSpaces = spaces;
        spaces[0].elements = Arrays.asList(ElementColor.PURPLE);
        spaces[1].elements = Arrays.asList(ElementColor.BLACK);
        card.handler = handler;
        EasyMock.expect(deck.draw()).andReturn(card);
        EasyMock.expect(deck.draw()).andReturn(fakeCard).times(15);
        EasyMock.expect(deck.draw()).andReturn(card2);

        EasyMock.replay(deck, handler);
        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();
        assertEquals(card, hatchingGround.cards[0][0]);
        assertEquals(Arrays.asList(card),
            hatchingGround.pullAndReplaceCompleteEggs());

        EasyMock.verify(deck, handler);
        assertEquals(hatchingGround.cards[0][0], card2);
    }

    @Test
    public void testPullAndReplaceOneCompleteWildEgg() {
        final Deck deck = EasyMock.strictMock(Deck.class);
        final Card fakeCard = new Card();
        Card card = new Card();
        card.handler = WildHandler.getInstance();
        ElementSpace[] spaces = {new ElementSpace(ElementColor.PURPLE),
            new ElementSpace(ElementColor.BLACK)};
        card.elementSpaces = spaces;
        spaces[0].elements = Arrays.asList(ElementColor.PURPLE);
        spaces[1].elements = Arrays.asList(ElementColor.BLACK);
        ElementSpace[] fakeSpaces = {new ElementSpace(ElementColor.PURPLE)};
        fakeCard.elementSpaces = fakeSpaces;
        fakeCard.elementSpaces[0].elements = new ArrayList<>();
        EasyMock.expect(deck.draw()).andReturn(card);
        EasyMock.expect(deck.draw()).andReturn(fakeCard).times(15);

        EasyMock.replay(deck);
        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();
        assertEquals(card, hatchingGround.cards[0][0]);
        assertEquals(Arrays.asList(),
            hatchingGround.pullAndReplaceCompleteEggs());

        EasyMock.verify(deck);
    }

}
