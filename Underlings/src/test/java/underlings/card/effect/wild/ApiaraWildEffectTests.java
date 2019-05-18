package underlings.card.effect.wild;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class ApiaraWildEffectTests extends MockTest {

    private Card apiara;
    private HatchingGround hatchingGround;
    private Deck deck;
    private Effect effect;
    private Player player;
    private EggHatchingLogic logic;

    @Before
    public void init() {
        this.apiara = new Card();
        this.effect = new ApiaraWildEffect();

        this.hatchingGround = this.mock(HatchingGround.class);
        this.deck = this.mock(Deck.class);
        this.player = this.mock(Player.class);
        this.logic = this.mock(EggHatchingLogic.class);
    }

    @Test
    public void testEffect() {
        this.hatchingGround.replaceCard(this.apiara);
        this.deck.addCard(this.apiara);

        this.replayAll();

        this.effect.on(this.hatchingGround).on(this.apiara).on(this.deck).apply();
    }

    @Test
    public void testEffectTwiceNoCards() {
        this.hatchingGround.replaceCard(this.apiara);
        this.deck.addCard(this.apiara);

        List<Card> cards = new ArrayList<>();
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(cards);

        this.replayAll();

        this.effect.on(this.hatchingGround).on(this.apiara).on(this.deck).apply();
        this.effect.on(this.hatchingGround).on(this.apiara).on(this.deck).apply();
    }

    @Test
    public void testEffectTwiceOneCard() {
        this.hatchingGround.replaceCard(this.apiara);
        this.deck.addCard(this.apiara);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(cards);


        EasyMock.expect(this.player.getId()).andReturn(4).anyTimes();

        this.logic.hatchEgg(cards.get(0), true, this.player);

        this.replayAll();

        this.effect.on(this.hatchingGround).on(this.apiara).on(this.deck).on(this.player).on(this.logic).apply();
        this.effect.on(this.hatchingGround).on(this.apiara).on(this.deck).on(this.player).on(this.logic).apply();
    }

    @Test
    public void testEffectTwiceTwoCards() {
        this.hatchingGround.replaceCard(this.apiara);
        this.deck.addCard(this.apiara);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        cards.add(new Card());
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(cards);

        EasyMock.expect(this.player.getId()).andReturn(4).anyTimes();

        this.logic.hatchEgg(cards.get(0), true, this.player);
        this.logic.hatchEgg(cards.get(1), true, this.player);

        this.replayAll();

        this.effect.on(this.hatchingGround).on(this.apiara).on(this.deck).on(this.player).on(this.logic).apply();
        this.effect.on(this.hatchingGround).on(this.apiara).on(this.deck).on(this.player).on(this.logic).apply();
    }

}
