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
    public void testOneUnHatchedEgg() {
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

    @Test
    public void testTwoUnHatchedEggs() {
        Card card = EasyMock.mock(Card.class);
        Handler handler = EasyMock.mock(Handler.class);
        handler.moveToState(HandlerState.READY_ROOM);
        EasyMock.expectLastCall().times(2);
        card.handler = handler;
        Player player = new Player(6, new HandlerFactory(), 0);
        player.unhatchedCards.add(card);
        player.unhatchedCards.add(card);
        card.domesticEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        card.domesticEffects[0] = effect;

        EasyMock.replay(card, handler, effect);
        List<Effect> effects = player.getAllEffects();

        EasyMock.verify(card, handler, effect);
        assertEquals(effect, effects.get(0));
        assertEquals(effect, effects.get(1));
        assertEquals(2, effects.size());
        assertEquals(2, player.hatchedCards.size());
        assertEquals(card, player.hatchedCards.get(0));
        assertEquals(card, player.hatchedCards.get(1));
        assertEquals(0, player.unhatchedCards.size());
    }

    @Test
    public void testOneUnHatchedEggWithMultipleEffects() {
        Player player = new Player(6, new HandlerFactory(), 0);
        Card card = EasyMock.mock(Card.class);
        Handler handler = EasyMock.mock(Handler.class);
        handler.moveToState(HandlerState.READY_ROOM);
        card.handler = handler;
        player.unhatchedCards.add(card);
        card.domesticEffects = new Effect[2];
        Effect effect = EasyMock.mock(Effect.class);
        card.domesticEffects[0] = effect;
        card.domesticEffects[1] = effect;

        EasyMock.replay(card, handler, effect);
        List<Effect> effects = player.getAllEffects();

        EasyMock.verify(card, handler, effect);
        assertEquals(effect, effects.get(0));
        assertEquals(effect, effects.get(1));
        assertEquals(2, effects.size());
        assertEquals(1, player.hatchedCards.size());
        assertEquals(card, player.hatchedCards.get(0));
        assertEquals(0, player.unhatchedCards.size());
    }

}
