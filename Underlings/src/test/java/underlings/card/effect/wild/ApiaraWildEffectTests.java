package underlings.card.effect.wild;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.hatchingground.Deck;
import underlings.hatchingground.EggHatchingUtilities;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class ApiaraWildEffectTests extends MockTest {

    @Before
    public void init() {
        this.card = new Card();
        this.effect = new ApiaraWildEffect();

        this.hatchingGround = this.mock(HatchingGround.class);
        this.deck = this.mock(Deck.class);
        this.player = this.mock(Player.class);
        this.eggHatchingLogic = this.mock(EggHatchingUtilities.class);
    }

    @Test
    public void testEffect() {
        this.hatchingGround.replaceCard(this.card);
        this.deck.addCard(this.card, true);

        this.replayAll();

        this.effect.on(this.hatchingGround).on(this.card).on(this.deck).apply();
    }

    @Test
    public void testEffectTwiceNoCards() {
        this.hatchingGround.replaceCard(this.card);
        this.deck.addCard(this.card, true);

        List<Card> cards = new ArrayList<>();
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(cards);

        this.replayAll();

        this.effect.on(this.hatchingGround).on(this.card).on(this.deck).apply();
        this.effect.on(this.hatchingGround).on(this.card).on(this.deck).apply();
    }

    @Test
    public void testEffectTwiceOneCard() {
        this.hatchingGround.replaceCard(this.card);
        this.deck.addCard(this.card, true);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(cards);

        EasyMock.expect(this.player.getId()).andReturn(4).anyTimes();

        this.eggHatchingLogic.hatchEgg(cards.get(0), this.player);

        this.replayAll();

        this.effect.on(this.hatchingGround).on(this.card).on(this.deck).on(this.player).on(this.eggHatchingLogic)
                .apply();
        this.effect.on(this.hatchingGround).on(this.card).on(this.deck).on(this.player).on(this.eggHatchingLogic)
                .apply();
    }

    @Test
    public void testEffectTwiceTwoCards() {
        this.hatchingGround.replaceCard(this.card);
        this.deck.addCard(this.card, true);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        cards.add(new Card());
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(cards);

        EasyMock.expect(this.player.getId()).andReturn(4).anyTimes();

        this.eggHatchingLogic.hatchEgg(cards.get(0), this.player);
        this.eggHatchingLogic.hatchEgg(cards.get(1), this.player);

        this.replayAll();

        this.effect.on(this.hatchingGround).on(this.card).on(this.deck).on(this.player).on(this.eggHatchingLogic)
                .apply();
        this.effect.on(this.hatchingGround).on(this.card).on(this.deck).on(this.player).on(this.eggHatchingLogic)
                .apply();
    }

    @Test
    public void testToStringDestroy() {
        this.replayAll();
        ApiaraWildEffect effect = new ApiaraWildEffect();
        assertEquals(LocaleUtilities.get("apiara_wild_effect"), effect.toString());
    }
}
