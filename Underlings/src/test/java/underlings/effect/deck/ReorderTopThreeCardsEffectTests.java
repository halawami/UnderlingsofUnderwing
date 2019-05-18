package underlings.effect.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.card.effect.domestic.ReorderTopThreeCardsEffect;
import underlings.game.Deck;
import underlings.gui.Gui;

public class ReorderTopThreeCardsEffectTests {

    @Test
    public void testThreeCards() {
        Deck deck = EasyMock.mock(Deck.class);
        List<Card> topThreeCards = TestUtils.mockListOf(Card.class).withLength(3);
        Gui gui = EasyMock.mock(Gui.class);
        List<Card> reorderedCards = new ArrayList<>(topThreeCards);
        Collections.shuffle(reorderedCards);

        for (Card topCard : topThreeCards) {
            EasyMock.expect(deck.draw()).andReturn(topCard);
        }
        EasyMock.expect(gui.reorderCards(topThreeCards)).andReturn(reorderedCards);
        for (Card reorderedCard : reorderedCards) {
            deck.addCard(reorderedCard);
        }

        EasyMock.replay(deck, gui);
        topThreeCards.forEach(EasyMock::replay);

        ReorderTopThreeCardsEffect testedEffect = new ReorderTopThreeCardsEffect();
        testedEffect.on(deck).on(gui).apply();

        EasyMock.verify(deck, gui);
        topThreeCards.forEach(EasyMock::verify);
    }

    @Test
    public void testTwoCards() {
        Deck deck = EasyMock.mock(Deck.class);
        List<Card> topThreeCards = TestUtils.mockListOf(Card.class).withLength(2);
        topThreeCards.add(EmptyCard.getInstance());
        Gui gui = EasyMock.mock(Gui.class);
        List<Card> reorderedCards = new ArrayList<>(topThreeCards);
        reorderedCards.removeIf(EmptyCard.getInstance()::equals);
        Collections.shuffle(reorderedCards);

        for (Card topCard : topThreeCards) {
            EasyMock.expect(deck.draw()).andReturn(topCard);
        }
        EasyMock.expect(gui.reorderCards(topThreeCards)).andReturn(reorderedCards);
        for (Card reorderedCard : reorderedCards) {
            deck.addCard(reorderedCard);
        }

        EasyMock.replay(deck, gui);
        for (Card topCard : topThreeCards) {
            if (topCard != EmptyCard.getInstance()) {
                EasyMock.replay(topCard);
            }
        }

        ReorderTopThreeCardsEffect testedEffect = new ReorderTopThreeCardsEffect();
        testedEffect.on(deck).on(gui).apply();

        EasyMock.verify(deck, gui);
        for (Card topCard : topThreeCards) {
            if (topCard != EmptyCard.getInstance()) {
                EasyMock.verify(topCard);
            }
        }
    }

}
