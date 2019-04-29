package tests.card.factory;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import underlings.card.Card;
import underlings.card.construction.CardFactory;
import underlings.card.effect.Effect;

public class GetCardsTests {

    @Test
    public void testGetOneCard() {
        CardFactory cardFactory = new CardFactory(
                "\\src\\test\\java\\tests\\card\\factory\\json\\sky_whelp.json");
        List<Card> cards = cardFactory.getCards();
        assertEquals(1, cards.size());

        Card testCard = cards.get(0);
        Effect[] domesticEffects = testCard.domesticEffects;

        assertEquals(2, domesticEffects.length);
    }

}
