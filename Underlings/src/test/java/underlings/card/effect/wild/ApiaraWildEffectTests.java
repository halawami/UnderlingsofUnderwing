package underlings.card.effect.wild;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.game.Deck;
import underlings.game.HatchingGround;

public class ApiaraWildEffectTests {

    @Test
    public void testEffect() {
        Card apiara = new Card();

        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        hatchingGround.replaceCard(apiara);

        Deck deck = EasyMock.mock(Deck.class);
        deck.addCard(apiara);

        EasyMock.replay(hatchingGround, deck);

        Effect effect = new ApiaraWildEffect();
        effect.on(hatchingGround).on(apiara).on(deck).apply();

        EasyMock.verify(hatchingGround, deck);
    }
}
