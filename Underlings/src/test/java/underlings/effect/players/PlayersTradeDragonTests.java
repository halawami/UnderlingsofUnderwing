package underlings.effect.players;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.PlayersTradeDragon;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class PlayersTradeDragonTests {

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
        this.card.domesticEffects = new Effect[] {mockedEffect};
        this.card2 = new Card();
        this.card2.points = 12;
        this.card.domesticEffects = new Effect[] {mockedEffect};
        this.mockedEffect = EasyMock.mock(Effect.class);
        this.effect = new PlayersTradeDragon();
        this.player.hatchedCards = new ArrayList<>();
        this.player.hatchedCards.add(card);
        this.player2.hatchedCards = new ArrayList<>();
    }

    @Test
    public void testApplyTwoPlayersOneCard() {
        effect.on(Arrays.asList(player, player2)).on(gui);
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_card_to_trade"), player.hatchedCards, 0))
                .andReturn(card);
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_card_to_trade"), player2.hatchedCards, 0))
                .andReturn(EmptyCard.getInstance());

        EasyMock.replay(player, player2, mockedEffect, gui);

        effect.apply();
        assertTrue(player2.hatchedCards.contains(card));
        assertFalse(player.hatchedCards.contains(card));
        assertTrue(player.hatchedCards.contains(EmptyCard.getInstance()));
        assertEquals(1, player.hatchedCards.size());

        EasyMock.verify(player, player2, mockedEffect, gui);
    }

    @Test
    public void testApplyTwoPlayersThreeCards() {
        Card card3 = new Card();
        card3.points = 12;
        card3.domesticEffects = new Effect[] {mockedEffect};
        player.hatchedCards.add(card3);
        player2.hatchedCards.add(card2);
        effect.on(Arrays.asList(player, player2)).on(gui);

        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_card_to_trade"), player.hatchedCards, 0))
                .andReturn(card3);
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_card_to_trade"), player2.hatchedCards, 0))
                .andReturn(card2);

        EasyMock.replay(player, player2, mockedEffect, gui);

        effect.apply();
        assertTrue(player2.hatchedCards.contains(card3));
        assertFalse(player.hatchedCards.contains(card3));
        assertTrue(player.hatchedCards.contains(card2));
        assertFalse(player2.hatchedCards.contains(card2));
        assertEquals(2, player.hatchedCards.size());
        assertEquals(1, player2.hatchedCards.size());

        EasyMock.verify(player, player2, mockedEffect, gui);
    }

    @Test
    public void testApplyMultiplePlayers() {

        Card card3 = new Card();
        card3.points = 12;
        card3.domesticEffects = new Effect[] {mockedEffect};
        player.hatchedCards.add(card3);
        player2.hatchedCards.add(card2);
        player2.hatchedCards.add(card3);
        Player player3 = EasyMock.mock(Player.class);
        player3.hatchedCards = new ArrayList<>();
        player3.hatchedCards.add(card3);
        effect.on(Arrays.asList(player, player2, player3)).on(gui);
        Player playerWithLeastDragons = player3;

        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_card_to_trade"), player.hatchedCards, 0))
                .andReturn(card);
        EasyMock.expect(
                gui.promptChoice(LocaleWrap.get("prompt_card_to_trade"), playerWithLeastDragons.hatchedCards, 0))
                .andReturn(card3);
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_card_to_trade"), player2.hatchedCards, 0))
                .andReturn(card2);
        EasyMock.expect(
                gui.promptChoice(LocaleWrap.get("prompt_card_to_trade"), playerWithLeastDragons.hatchedCards, 0))
                .andReturn(card);

        EasyMock.replay(player, player2, mockedEffect, gui, player3);

        effect.apply();
        assertTrue(player2.hatchedCards.contains(card));
        assertFalse(player2.hatchedCards.contains(card2));
        assertTrue(player.hatchedCards.contains(card3));
        assertFalse(player.hatchedCards.contains(card));
        assertTrue(player3.hatchedCards.contains(card2));
        assertFalse(player3.hatchedCards.contains(card3));
        assertEquals(2, player.hatchedCards.size());
        assertEquals(2, player2.hatchedCards.size());
        assertEquals(1, player3.hatchedCards.size());

        EasyMock.verify(player, player2, mockedEffect, gui, player3);
    }

    @Test
    public void testApplySameNumberOfDragons() {
        player2.hatchedCards.add(card2);
        effect.on(Arrays.asList(player, player2)).on(gui);
        gui.notifyAction(-1, LocaleWrap.get("notify_no_player_least_dragons"));

        EasyMock.replay(player, player2, mockedEffect, gui);

        effect.apply();

        EasyMock.verify(player, player2, mockedEffect, gui);
    }

    @Test
    public void testApplyMultipleSameNumberOfDragons() {
        player.hatchedCards.add(card2);
        player2.hatchedCards.add(card2);
        Player player3 = EasyMock.mock(Player.class);
        player3.hatchedCards = new ArrayList<>();
        player3.hatchedCards.add(card2);
        Player player4 = EasyMock.mock(Player.class);
        player4.hatchedCards = new ArrayList<>();
        player4.hatchedCards.add(card2);
        player4.hatchedCards.add(card);
        effect.on(Arrays.asList(player, player2, player3, player4)).on(gui);
        gui.notifyAction(-1, LocaleWrap.get("notify_no_player_least_dragons"));

        EasyMock.replay(player, player2, mockedEffect, gui, player3, player4);

        effect.apply();

        EasyMock.verify(player, player2, mockedEffect, gui, player3, player4);
    }

    @Test
    public void testToString() {
        Effect effect = new PlayersTradeDragon();
        assertEquals(LocaleWrap.get("trade_dragons_effect"), effect.toString());
    }

}
