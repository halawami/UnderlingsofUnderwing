package underlings.card.effect.wild;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

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

    @Test
    public void testEffectTwiceNoCards() {
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        Deck deck = EasyMock.mock(Deck.class);
        Card apiara = new Card();

        hatchingGround.replaceCard(apiara);
        deck.addCard(apiara);

        List<Card> cards = new ArrayList<>();
        EasyMock.expect(hatchingGround.getUnclaimedEggs()).andReturn(cards);

        EasyMock.replay(hatchingGround, deck);
        Effect effect = new ApiaraWildEffect();
        effect.on(hatchingGround).on(apiara).on(deck).apply();
        effect.on(hatchingGround).on(apiara).on(deck).apply();
        EasyMock.verify(hatchingGround, deck);
    }

    @Test
    public void testEffectTwiceOneCard() {
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        Deck deck = EasyMock.mock(Deck.class);
        Card apiara = new Card();

        hatchingGround.replaceCard(apiara);
        deck.addCard(apiara);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        EasyMock.expect(hatchingGround.getUnclaimedEggs()).andReturn(cards);

        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getId()).andReturn(4).anyTimes();

        EggHatchingLogic logic = EasyMock.mock(EggHatchingLogic.class);
        logic.hatchEgg(cards.get(0), true, player);

        EasyMock.replay(hatchingGround, deck, logic, player);
        Effect effect = new ApiaraWildEffect();
        effect.on(hatchingGround).on(apiara).on(deck).on(player).on(logic).apply();
        effect.on(hatchingGround).on(apiara).on(deck).on(player).on(logic).apply();
        EasyMock.verify(hatchingGround, deck, logic, player);
    }

    @Test
    public void testEffectTwiceTwoCards() {
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        Deck deck = EasyMock.mock(Deck.class);
        Card apiara = new Card();

        hatchingGround.replaceCard(apiara);
        deck.addCard(apiara);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        cards.add(new Card());
        EasyMock.expect(hatchingGround.getUnclaimedEggs()).andReturn(cards);

        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getId()).andReturn(4).anyTimes();

        EggHatchingLogic logic = EasyMock.mock(EggHatchingLogic.class);
        logic.hatchEgg(cards.get(0), true, player);
        logic.hatchEgg(cards.get(1), true, player);

        EasyMock.replay(hatchingGround, deck, logic, player);
        Effect effect = new ApiaraWildEffect();
        effect.on(hatchingGround).on(apiara).on(deck).on(player).on(logic).apply();
        effect.on(hatchingGround).on(apiara).on(deck).on(player).on(logic).apply();
        EasyMock.verify(hatchingGround, deck, logic, player);
    }

    @Test
    public void testToStringDestroy() {
        ApiaraWildEffect effect = new ApiaraWildEffect();
        assertEquals(LocaleWrap.get("apiara_wild_effect"), effect.toString());
    }
}
