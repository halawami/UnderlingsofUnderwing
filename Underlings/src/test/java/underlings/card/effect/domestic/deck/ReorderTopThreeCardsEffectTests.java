package underlings.card.effect.domestic.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.gui.Gui;
import underlings.hatchingground.Deck;

public class ReorderTopThreeCardsEffectTests extends MockTest {

    @Test
    public void testThreeOrMoreCardsLeft() {
        this.testCardsLeft(3);
    }

    @Test
    public void testTwoCardsLeft() {
        this.testCardsLeft(2);
    }

    @Test
    public void testOneCardLeft() {
        this.testCardsLeft(1);
    }

    @Test
    public void testNoCardsLeft() {
        this.testCardsLeft(0);
    }

    private void testCardsLeft(int cardsLeft) {
        Deck deck = this.mock(Deck.class);

        List<Card> topThreeCards = this.mockListOf(Card.class).withLengthOf(cardsLeft);
        this.addEmptyCards(topThreeCards, cardsLeft);

        List<Card> reorderedCards = new ArrayList<>(topThreeCards);
        reorderedCards.removeIf(EmptyCard.getInstance()::equals);
        Collections.shuffle(reorderedCards);

        for (Card topCard : topThreeCards) {
            EasyMock.expect(deck.draw()).andReturn(topCard);
        }
        Gui gui = this.mock(Gui.class);
        EasyMock.expect(gui.reorderCards(topThreeCards)).andReturn(reorderedCards);
        for (Card reorderedCard : reorderedCards) {
            deck.addCard(reorderedCard, false);
        }

        this.replayAll();

        ReorderTopThreeCardsEffect testedEffect = new ReorderTopThreeCardsEffect();
        testedEffect.on(deck).on(gui).apply();
    }

    private void addEmptyCards(List<Card> topCards, int cardsLeft) {
        for (int i = cardsLeft; i < 3; i++) {
            topCards.add(EmptyCard.getInstance());
        }
    }

}