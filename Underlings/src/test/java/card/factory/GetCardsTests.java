package card.factory;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import card.Card;
import card.CardFactory;
import card.effect.Effect;

public class GetCardsTests {

    @Test
    public void testGetOneCard() {
        CardFactory cardFactory = new CardFactory("sky_whelp.json");
        List<Card> cards = cardFactory.getCards();
        assertEquals(1, cards.size());

        Card testCard = cards.get(0);
        Effect[] domesticEffects = testCard.domesticEffects;

        assertEquals(2, domesticEffects.length);
    }

}
