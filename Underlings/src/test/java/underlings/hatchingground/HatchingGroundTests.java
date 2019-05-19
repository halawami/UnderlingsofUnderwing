package underlings.hatchingground;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.ElementBag;
import underlings.element.ElementSpaceUtilities;
import underlings.game.Game;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.player.PlayerFactory;

public class HatchingGroundTests {

    @Test
    public void testReplaceCard() {
        ElementSpaceUtilities logic = EasyMock.mock(ElementSpaceUtilities.class);
        EasyMock.expect(logic.isComplete(EasyMock.anyObject(Card.class))).andReturn(false).anyTimes();

        Deck deck = EasyMock.mock(Deck.class);
        Card card1 = new Card();
        Card card2 = new Card();

        EasyMock.expect(deck.draw()).andReturn(card1);
        EasyMock.expect(deck.draw()).andReturn(card2);

        EasyMock.replay(deck, logic);
        HatchingGround hatchingGround = new HatchingGround(deck, logic);
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();
        hatchingGround.replaceCard(card1);
        assertEquals(card2, hatchingGround.cards[0][0]);
        EasyMock.verify(deck, logic);
    }

    @Test
    public void testReplaceCardBigger() {
        ElementSpaceUtilities logic = EasyMock.mock(ElementSpaceUtilities.class);
        EasyMock.expect(logic.isComplete(EasyMock.anyObject(Card.class))).andReturn(false).anyTimes();

        Deck deck = EasyMock.mock(Deck.class);
        Card card1 = new Card();
        Card card2 = new Card();

        EasyMock.expect(deck.draw()).andReturn(new Card()).times(3);
        EasyMock.expect(deck.draw()).andReturn(card1);
        EasyMock.expect(deck.draw()).andReturn(card2);

        EasyMock.replay(deck, logic);
        HatchingGround hatchingGround = new HatchingGround(deck, logic);
        hatchingGround.setDimensions(2, 2);
        hatchingGround.populate();
        hatchingGround.replaceCard(card1);
        assertEquals(card2, hatchingGround.cards[1][1]);
        EasyMock.verify(deck, logic);
    }


    private Game game;
    private HatchingGround hatchingGround;
    private Stack<Card> cards;

    @Before
    public void init() {
        ElementSpaceUtilities logic = EasyMock.niceMock(ElementSpaceUtilities.class);
        EasyMock.replay(logic);

        this.cards = new Stack<Card>();
        for (int i = 0; i < 50; i++) {
            this.cards.push(new Card());
        }

        this.hatchingGround = new HatchingGround(new Deck(this.cards, null), logic);
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


    private ElementSpaceUtilities niceLogic;

    @Before
    public void loadRecipes() {
        this.niceLogic = EasyMock.niceMock(ElementSpaceUtilities.class);
        EasyMock.replay(this.niceLogic);
    }

    @Test
    public void testInitUnclaimedEggs() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(6);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, this.niceLogic);
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

        HatchingGround hatchingGround = new HatchingGround(deck, this.niceLogic);
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

        HatchingGround hatchingGround = new HatchingGround(deck, this.niceLogic);
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testDeal4By3() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(12);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, this.niceLogic);
        hatchingGround.setDimensions(4, 3);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testDeal4By4() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(16);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, this.niceLogic);
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testPullAndReplace() {
        ElementSpaceUtilities logic = EasyMock.mock(ElementSpaceUtilities.class);

        Card card1 = new Card();
        EasyMock.expect(logic.isComplete(card1)).andReturn(false);

        Card card2 = new Card();
        EasyMock.expect(logic.isComplete(card2)).andReturn(false);

        Card card3 = new Card();
        EasyMock.expect(logic.isComplete(card3)).andReturn(true);

        Card card4 = new Card();
        EasyMock.expect(logic.isComplete(card4)).andReturn(true);

        Deck deck = EasyMock.mock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(card1);
        EasyMock.expect(deck.draw()).andReturn(card2);
        EasyMock.expect(deck.draw()).andReturn(card3);
        EasyMock.expect(deck.draw()).andReturn(card4);
        EasyMock.expect(deck.draw()).andReturn(new Card());

        HatchingGround hatchingGround =
                EasyMock.partialMockBuilder(HatchingGround.class).addMockedMethod("placeCard").createMock();
        hatchingGround.placeCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject());
        EasyMock.expectLastCall().times(5);
        EasyMock.replay(logic, deck, hatchingGround);
        hatchingGround.logic = logic;
        hatchingGround.deck = deck;
        hatchingGround.setDimensions(2, 2);
        hatchingGround.populate();
        hatchingGround.cards[0][0] = card1;
        hatchingGround.cards[0][1] = card2;
        hatchingGround.cards[1][0] = card3;
        hatchingGround.cards[1][1] = card4;
        card1.handler = WildHandler.getInstance();
        card3.handler = WildHandler.getInstance();
        List<Card> cards = hatchingGround.pullAndReplaceCompleteEggs();

        EasyMock.verify(logic, deck, hatchingGround);

        assertEquals(1, cards.size());
        assertTrue(cards.contains(card4));
    }

    @Test
    public void testGetAllCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            cards.add(new Card());
        }
        Deck deck = new Deck(cards, null);
        HatchingGround hatchingGround = new HatchingGround(deck, this.niceLogic);
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        List<Card> allCards = hatchingGround.getAllCards();

        assertEquals(6, allCards.size());

        for (Card card : allCards) {
            assertTrue(cards.contains(card));
        }

    }

    @Test
    public void testGetUnclaimedDragon() {
        final Deck deck = EasyMock.strictMock(Deck.class);
        Card card = new Card();
        card.points = 3;
        for (int i = 0; i < 5; i++) {
            Card card2 = new Card();
            card2.points = 6;
            EasyMock.expect(deck.draw()).andReturn(card2);
        }
        EasyMock.expect(deck.draw()).andReturn(card);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, this.niceLogic);
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        EasyMock.verify(deck);

        assertEquals(1, hatchingGround.getDragons(4, true).size());
    }

    @Test
    public void testGetClaimedDragon() {
        final Deck deck = EasyMock.strictMock(Deck.class);
        Card card = new Card();
        card.points = 3;
        card.handler = WildHandler.getInstance();
        card.wildEffects = new Effect[0];
        Card card2 = new Card();
        card2.points = 3;
        card2.handler = EasyMock.mock(Handler.class);
        Card card3 = new Card();
        card3.points = 3;
        card3.handler = null;
        for (int i = 0; i < 3; i++) {
            Card card4 = new Card();
            card4.points = 6;
            EasyMock.expect(deck.draw()).andReturn(card4);
        }
        EasyMock.expect(deck.draw()).andReturn(card);
        EasyMock.expect(deck.draw()).andReturn(card2);
        EasyMock.expect(deck.draw()).andReturn(card3);

        EasyMock.replay(deck, card2.handler);

        HatchingGround hatchingGround = new HatchingGround(deck, this.niceLogic);
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        EasyMock.verify(deck, card2.handler);

        List<Card> cards = hatchingGround.getClaimedEggs();
        assertEquals(2, cards.size());
        assertTrue(cards.contains(card));
        assertTrue(cards.contains(card2));
    }

    @Test
    public void testGetDragonsSamePoints() {
        final Deck deck = EasyMock.strictMock(Deck.class);
        Card card = new Card();
        card.points = 3;
        card.handler = WildHandler.getInstance();
        card.wildEffects = new Effect[0];
        Card card2 = new Card();
        card2.points = 3;
        card2.handler = EasyMock.mock(Handler.class);
        Card card3 = new Card();
        card3.points = 3;
        card3.handler = null;
        for (int i = 0; i < 3; i++) {
            Card card4 = new Card();
            card4.points = 6;
            EasyMock.expect(deck.draw()).andReturn(card4);
        }
        EasyMock.expect(deck.draw()).andReturn(card);
        EasyMock.expect(deck.draw()).andReturn(card2);
        EasyMock.expect(deck.draw()).andReturn(card3);

        EasyMock.replay(deck, card2.handler);

        HatchingGround hatchingGround = new HatchingGround(deck, this.niceLogic);
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        EasyMock.verify(deck, card2.handler);

        List<Card> cards = hatchingGround.getDragons(3, true);
        assertEquals(1, cards.size());
        assertTrue(cards.contains(card3));
    }

    @Test
    public void testPlaceCardIncomplete() {
        Card card = new Card();
        card.name = "TestCard";

        Deck deck = EasyMock.mock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(card);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, null);
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testPlaceCardCompleteNotWild() {
        Card card = new Card();
        card.name = "TestCard";

        Deck deck = EasyMock.mock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(card);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, null);
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testPlaceCardCompleteWildOneEffect() {
        Card card = new Card();
        card.name = "TestCard";
        card.handler = WildHandler.getInstance();

        Effect effect = EasyMock.mock(Effect.class);
        card.wildEffects = new Effect[1];
        card.wildEffects[0] = effect;
        effect.apply();

        Deck deck = EasyMock.mock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(card);

        EasyMock.replay(effect, deck);

        HatchingGround hatchingGround = new HatchingGround(deck, null);
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();

        EasyMock.verify(effect, deck);
    }

    @Test
    public void testPlaceCardCompleteWildNoEffects() {
        Card card = new Card();
        card.name = "TestCard";
        card.handler = WildHandler.getInstance();

        card.wildEffects = new Effect[0];

        Deck deck = EasyMock.mock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(card);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, null);
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testPlaceCardCompleteWildTwoEffects() {
        Card card = new Card();
        card.name = "TestCard";
        card.handler = WildHandler.getInstance();

        card.wildEffects = new Effect[2];

        Effect effect1 = EasyMock.mock(Effect.class);
        effect1.apply();
        card.wildEffects[0] = effect1;

        Effect effect2 = EasyMock.mock(Effect.class);
        effect2.apply();
        card.wildEffects[1] = effect2;

        Deck deck = EasyMock.mock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(card);

        EasyMock.replay(deck, effect1, effect2);

        HatchingGround hatchingGround = new HatchingGround(deck, null);
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();

        EasyMock.verify(deck, effect1, effect2);
    }

}
