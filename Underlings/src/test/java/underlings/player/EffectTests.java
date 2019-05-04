package underlings.player;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.handler.Handler;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerState;

public class EffectTests {

    @Test
    public void testNoUnhatchedEggs() {
        Player player = new Player(6, new HandlerFactory(), 0);
        assertEquals(0, player.getAllEffects().size());
    }

    @Test
    public void testOneHatchedEgg() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = EasyMock.mock(Card.class);
        Handler handler = EasyMock.mock(Handler.class);
        handler.moveToState(HandlerState.READY_ROOM);
        card.handler = handler;
        player.unhatchedCards.add(card);
        card.domesticEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        card.domesticEffects[0] = effect;

        EasyMock.replay(card, handler, effect);
        List<Effect> effects = player.getAllEffects();

        EasyMock.verify(card, handler, effect);
        assertEquals(effect, effects.get(0));
        assertEquals(1, effects.size());
        assertEquals(1, player.hatchedCards.size());
        assertEquals(card, player.hatchedCards.get(0));
        assertEquals(0, player.unhatchedCards.size());
    }

}
