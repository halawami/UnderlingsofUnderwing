package underlings.card.effect.wild;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class ApiaraWildEffectTests {

    Card apiara;
    HatchingGround hatchingGround;
    Deck deck;

    @Before
    public void init() {
        this.apiara = new Card();
        this.hatchingGround = EasyMock.mock(HatchingGround.class);
        this.deck = EasyMock.mock(Deck.class);
    }

    @Test
    public void testEffect() {
        this.hatchingGround.replaceCard(this.apiara);
        this.deck.addCard(this.apiara);

        EasyMock.replay(this.hatchingGround, this.deck);

        Effect effect = new ApiaraWildEffect();
        effect.on(this.hatchingGround).on(this.apiara).on(this.deck).apply();

        EasyMock.verify(this.hatchingGround, this.deck);
    }

    @Test
    public void testEffectTwiceNoCards() {
        this.hatchingGround.replaceCard(this.apiara);
        this.deck.addCard(this.apiara);

        List<Card> cards = new ArrayList<>();
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(cards);

        EasyMock.replay(this.hatchingGround, this.deck);
        Effect effect = new ApiaraWildEffect();
        effect.on(this.hatchingGround).on(this.apiara).on(this.deck).apply();
        effect.on(this.hatchingGround).on(this.apiara).on(this.deck).apply();
        EasyMock.verify(this.hatchingGround, this.deck);
    }

    @Test
    public void testEffectTwiceOneCard() {
        this.hatchingGround.replaceCard(this.apiara);
        this.deck.addCard(this.apiara);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(cards);

        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getId()).andReturn(4).anyTimes();

        EggHatchingLogic logic = EasyMock.mock(EggHatchingLogic.class);
        logic.hatchEgg(cards.get(0), true, player);

        EasyMock.replay(this.hatchingGround, this.deck, logic, player);
        Effect effect = new ApiaraWildEffect();
        effect.on(this.hatchingGround).on(this.apiara).on(this.deck).on(player).on(logic).apply();
        effect.on(this.hatchingGround).on(this.apiara).on(this.deck).on(player).on(logic).apply();
        EasyMock.verify(this.hatchingGround, this.deck, logic, player);
    }

    @Test
    public void testEffectTwiceTwoCards() {
        this.hatchingGround.replaceCard(this.apiara);
        this.deck.addCard(this.apiara);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        cards.add(new Card());
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(cards);

        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getId()).andReturn(4).anyTimes();

        EggHatchingLogic logic = EasyMock.mock(EggHatchingLogic.class);
        logic.hatchEgg(cards.get(0), true, player);
        logic.hatchEgg(cards.get(1), true, player);

        EasyMock.replay(this.hatchingGround, this.deck, logic, player);
        Effect effect = new ApiaraWildEffect();
        effect.on(this.hatchingGround).on(this.apiara).on(this.deck).on(player).on(logic).apply();
        effect.on(this.hatchingGround).on(this.apiara).on(this.deck).on(player).on(logic).apply();
        EasyMock.verify(this.hatchingGround, this.deck, logic, player);
    }
}
