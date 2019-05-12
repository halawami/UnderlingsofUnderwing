package underlings.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.handler.Handler;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;

public class HatchingGroundTests {

    private List<String> recipes;

    @Before
    public void loadRecipes() throws Exception {
        this.recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
    }

    @Test
    public void testInitUnclaimedEggs() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(6);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
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

        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
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

        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testDeal4By3() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(12);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
        hatchingGround.setDimensions(4, 3);
        hatchingGround.populate();

        EasyMock.verify(deck);
    }

    @Test
    public void testDeal4By4() {
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(16);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
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
        spaces[0].elements = Arrays.asList(new Element(ElementColor.PURPLE));
        card.handler = handler;
        EasyMock.expect(deck.draw()).andReturn(card);
        EasyMock.expect(deck.draw()).andReturn(fakeCard).times(15);
        EasyMock.expect(deck.draw()).andReturn(card2);

        EasyMock.replay(deck, handler);
        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();
        assertEquals(card, hatchingGround.cards[0][0]);
        assertEquals(Arrays.asList(card), hatchingGround.pullAndReplaceCompleteEggs());

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
        spaces[0].elements = Arrays.asList(new Element(ElementColor.PURPLE));
        card.handler = handler;
        EasyMock.expect(deck.draw()).andReturn(card).times(2);
        EasyMock.expect(deck.draw()).andReturn(fakeCard).times(14);
        EasyMock.expect(deck.draw()).andReturn(card2).times(2);

        EasyMock.replay(deck, handler);
        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();
        assertEquals(card, hatchingGround.cards[0][0]);
        assertEquals(card, hatchingGround.cards[0][1]);
        assertEquals(Arrays.asList(card, card), hatchingGround.pullAndReplaceCompleteEggs());

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
        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
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
        ElementSpace[] spaces = {new ElementSpace(ElementColor.PURPLE), new ElementSpace(ElementColor.BLACK)};
        card.elementSpaces = spaces;
        spaces[0].elements = Arrays.asList(new Element(ElementColor.PURPLE));
        spaces[1].elements = Arrays.asList(new Element(ElementColor.RED));
        card.handler = handler;
        EasyMock.expect(deck.draw()).andReturn(card);
        EasyMock.expect(deck.draw()).andReturn(fakeCard).times(15);

        EasyMock.replay(deck, handler);
        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();
        assertEquals(card, hatchingGround.cards[0][0]);
        assertEquals(Arrays.asList(), hatchingGround.pullAndReplaceCompleteEggs());

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
        ElementSpace[] spaces = {new ElementSpace(ElementColor.PURPLE), new ElementSpace(ElementColor.BLACK)};
        card.elementSpaces = spaces;
        spaces[0].elements = Arrays.asList(new Element(ElementColor.PURPLE));
        spaces[1].elements = Arrays.asList(new Element(ElementColor.BLACK));
        card.handler = handler;
        EasyMock.expect(deck.draw()).andReturn(card);
        EasyMock.expect(deck.draw()).andReturn(fakeCard).times(15);
        EasyMock.expect(deck.draw()).andReturn(card2);

        EasyMock.replay(deck, handler);
        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();
        assertEquals(card, hatchingGround.cards[0][0]);
        assertEquals(Arrays.asList(card), hatchingGround.pullAndReplaceCompleteEggs());

        EasyMock.verify(deck, handler);
        assertEquals(hatchingGround.cards[0][0], card2);
    }

    @Test
    public void testPullAndReplaceOneCompleteWildEgg() {
        final Deck deck = EasyMock.strictMock(Deck.class);
        final Card fakeCard = new Card();
        Card card = new Card();
        card.handler = WildHandler.getInstance();
        ElementSpace[] spaces = {new ElementSpace(ElementColor.PURPLE), new ElementSpace(ElementColor.BLACK)};
        card.elementSpaces = spaces;
        spaces[0].elements = Arrays.asList(new Element(ElementColor.PURPLE));
        spaces[1].elements = Arrays.asList(new Element(ElementColor.BLACK));
        ElementSpace[] fakeSpaces = {new ElementSpace(ElementColor.PURPLE)};
        fakeCard.elementSpaces = fakeSpaces;
        fakeCard.elementSpaces[0].elements = new ArrayList<>();
        EasyMock.expect(deck.draw()).andReturn(card);
        EasyMock.expect(deck.draw()).andReturn(fakeCard).times(15);

        EasyMock.replay(deck);
        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
        hatchingGround.setDimensions(4, 4);
        hatchingGround.populate();
        assertEquals(card, hatchingGround.cards[0][0]);
        assertEquals(Arrays.asList(), hatchingGround.pullAndReplaceCompleteEggs());

        EasyMock.verify(deck);
    }

    @Test
    public void testGetAllCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            cards.add(new Card());
        }
        Deck deck = new Deck(cards);
        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        List<Card> allCards = hatchingGround.getAllCards();

        assertEquals(6, allCards.size());

        for (Card card : allCards) {
            assertTrue(cards.contains(card));
        }

    }

    @Test
    public void testIsUnclaimedTrue() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cards.add(new Card());
        }
        Card unclaimedCard = new Card();
        cards.add(unclaimedCard);

        Deck deck = new Deck(cards);
        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        assertTrue(hatchingGround.isUnclaimed(unclaimedCard));

    }

    @Test
    public void testIsUnclaimedFalse() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cards.add(new Card());
        }
        Card claimedCard = new Card();
        claimedCard.handler = new Handler(HandlerState.CARD);
        cards.add(claimedCard);

        Deck deck = new Deck(cards);
        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(this.recipes));
        hatchingGround.setDimensions(3, 2);
        hatchingGround.populate();

        assertFalse(hatchingGround.isUnclaimed(claimedCard));

    }

}
