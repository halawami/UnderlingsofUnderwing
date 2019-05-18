package underlings.card.effect.wild;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class ApiaraWildEffectTests {

    private Card apiara;
    private HatchingGround hatchingGround;
    private Deck deck;
    private Effect effect;
    private Player player;
    private EggHatchingLogic logic;

    @Before
    public void init() {
        this.apiara = new Card();
        this.hatchingGround = EasyMock.mock(HatchingGround.class);
        this.deck = EasyMock.mock(Deck.class);
        this.effect = new ApiaraWildEffect();
        this.player = EasyMock.mock(Player.class);
        this.logic = EasyMock.mock(EggHatchingLogic.class);
    }

    @After
    public void verify() {
        EasyMock.verify(this.hatchingGround, this.deck, this.logic, this.player);
    }

    private void replay() {
        EasyMock.replay(this.hatchingGround, this.deck, this.logic, this.player);
    }

    @Test
    public void testEffect() {
        this.hatchingGround.replaceCard(this.apiara);
        this.deck.addCard(this.apiara);

        this.replay();

        this.effect.on(this.hatchingGround).on(this.apiara).on(this.deck).apply();
    }

    @Test
    public void testEffectTwiceNoCards() {
        this.hatchingGround.replaceCard(this.apiara);
        this.deck.addCard(this.apiara);

        List<Card> cards = new ArrayList<>();
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(cards);

        this.replay();

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

        this.replay();

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

        this.replay();

        this.effect.on(this.hatchingGround).on(this.apiara).on(this.deck).on(this.player).on(this.logic).apply();
        this.effect.on(this.hatchingGround).on(this.apiara).on(this.deck).on(this.player).on(this.logic).apply();
    }

}
