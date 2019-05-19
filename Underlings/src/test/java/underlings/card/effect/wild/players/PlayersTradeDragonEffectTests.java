package underlings.card.effect.wild.players;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class PlayersTradeDragonEffectTests {

    private Player player;
    private Player player2;
    private Gui gui;
    private Card card;
    private Card card2;
    private Effect mockedEffect;
    private Effect effect;

    @Before
    public void testInit() {
        this.player = EasyMock.mock(Player.class);
        this.player2 = EasyMock.mock(Player.class);
        this.gui = EasyMock.mock(Gui.class);
        this.card = new Card();
        this.card.points = 12;
        this.card.domesticEffects = new Effect[] {this.mockedEffect};
        this.card2 = new Card();
        this.card2.points = 12;
        this.card.domesticEffects = new Effect[] {this.mockedEffect};
        this.mockedEffect = EasyMock.mock(Effect.class);
        this.effect = new PlayersTradeDragonEffect();
        this.player.hatchedCards = new ArrayList<>();
        this.player.hatchedCards.add(this.card);
        this.player2.hatchedCards = new ArrayList<>();
    }

    @Test
    public void testApplyTwoPlayersThreeCards() {
        Card card3 = new Card();
        card3.points = 12;
        card3.domesticEffects = new Effect[] {this.mockedEffect};
        this.player.hatchedCards.add(card3);
        this.player2.hatchedCards.add(this.card2);
        this.effect.on(Arrays.asList(this.player, this.player2)).on(this.gui);

        EasyMock.expect(this.gui.promptChoice(LocaleUtilities.get("prompt_card_to_trade"), this.player.hatchedCards, 0))
                .andReturn(card3);
        EasyMock.expect(
                this.gui.promptChoice(LocaleUtilities.get("prompt_card_to_trade"), this.player2.hatchedCards, 0))
                .andReturn(this.card2);

        EasyMock.replay(this.player, this.player2, this.mockedEffect, this.gui);

        this.effect.apply();
        assertTrue(this.player2.hatchedCards.contains(card3));
        assertFalse(this.player.hatchedCards.contains(card3));
        assertTrue(this.player.hatchedCards.contains(this.card2));
        assertFalse(this.player2.hatchedCards.contains(this.card2));
        assertEquals(2, this.player.hatchedCards.size());
        assertEquals(1, this.player2.hatchedCards.size());

        EasyMock.verify(this.player, this.player2, this.mockedEffect, this.gui);
    }

    @Test
    public void testApplyMultiplePlayers() {
        Card card3 = new Card();
        card3.points = 12;
        card3.domesticEffects = new Effect[] {this.mockedEffect};
        this.player.hatchedCards.add(card3);
        this.player2.hatchedCards.add(this.card2);
        this.player2.hatchedCards.add(card3);
        Player player3 = EasyMock.mock(Player.class);
        player3.hatchedCards = new ArrayList<>();
        player3.hatchedCards.add(card3);
        this.effect.on(Arrays.asList(this.player, this.player2, player3)).on(this.gui);
        Player playerWithLeastDragons = player3;

        EasyMock.expect(this.gui.promptChoice(LocaleUtilities.get("prompt_card_to_trade"), this.player.hatchedCards, 0))
                .andReturn(this.card);
        EasyMock.expect(this.gui.promptChoice(LocaleUtilities.get("prompt_card_to_trade"),
                playerWithLeastDragons.hatchedCards, 0)).andReturn(card3);
        EasyMock.expect(
                this.gui.promptChoice(LocaleUtilities.get("prompt_card_to_trade"), this.player2.hatchedCards, 0))
                .andReturn(this.card2);
        EasyMock.expect(this.gui.promptChoice(LocaleUtilities.get("prompt_card_to_trade"),
                playerWithLeastDragons.hatchedCards, 0)).andReturn(this.card);

        EasyMock.replay(this.player, this.player2, this.mockedEffect, this.gui, player3);

        this.effect.apply();
        assertTrue(this.player2.hatchedCards.contains(this.card));
        assertFalse(this.player2.hatchedCards.contains(this.card2));
        assertTrue(this.player.hatchedCards.contains(card3));
        assertFalse(this.player.hatchedCards.contains(this.card));
        assertTrue(player3.hatchedCards.contains(this.card2));
        assertFalse(player3.hatchedCards.contains(card3));
        assertEquals(2, this.player.hatchedCards.size());
        assertEquals(2, this.player2.hatchedCards.size());
        assertEquals(1, player3.hatchedCards.size());

        EasyMock.verify(this.player, this.player2, this.mockedEffect, this.gui, player3);
    }

    @Test
    public void testApplySameNumberOfDragons() {
        this.player2.hatchedCards.add(this.card2);
        this.effect.on(Arrays.asList(this.player, this.player2)).on(this.gui);
        this.gui.notifyAction(-1, LocaleUtilities.get("notify_no_player_least_dragons"));

        EasyMock.replay(this.player, this.player2, this.mockedEffect, this.gui);

        this.effect.apply();

        EasyMock.verify(this.player, this.player2, this.mockedEffect, this.gui);
    }

    @Test
    public void testApplyNoHatchedCards() {
        EasyMock.expect(this.gui.promptChoice(LocaleUtilities.get("prompt_card_to_trade"), this.player.hatchedCards, 0))
                .andReturn(this.card);

        EasyMock.replay(this.player, this.player2, this.mockedEffect, this.gui);

        PlayersTradeDragonEffect effect = new PlayersTradeDragonEffect();
        effect.tradeCards(this.gui, this.player2, this.player);

        EasyMock.verify(this.player, this.player2, this.mockedEffect, this.gui);
    }

    @Test
    public void testApplyMultipleSameNumberOfDragons() {
        Player player3 = EasyMock.mock(Player.class);
        this.player.hatchedCards.add(this.card2);
        this.player2.hatchedCards.add(this.card2);
        player3.hatchedCards = new ArrayList<>();
        player3.hatchedCards.add(this.card2);
        Player player4 = EasyMock.mock(Player.class);
        player4.hatchedCards = new ArrayList<>();
        player4.hatchedCards.add(this.card2);
        player4.hatchedCards.add(this.card);
        this.effect.on(Arrays.asList(this.player, this.player2, player3, player4)).on(this.gui);
        this.gui.notifyAction(-1, LocaleUtilities.get("notify_no_player_least_dragons"));

        EasyMock.replay(this.player, this.player2, this.mockedEffect, this.gui, player3, player4);

        this.effect.apply();

        EasyMock.verify(this.player, this.player2, this.mockedEffect, this.gui, player3, player4);
    }

    @Test
    public void testToString() {
        Effect effect = new PlayersTradeDragonEffect();
        assertEquals(LocaleUtilities.get("trade_dragons_effect"), effect.toString());
    }

}
