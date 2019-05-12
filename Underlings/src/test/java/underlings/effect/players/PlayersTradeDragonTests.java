package underlings.effect.players;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.PlayersTradeDragon;
import underlings.gui.Gui;
import underlings.player.Player;

public class PlayersTradeDragonTests {

    @Test
    public void testApplyTwoPlayersOneCard() {
        Player player = EasyMock.mock(Player.class);
        Player player2 = EasyMock.mock(Player.class);
        Gui gui = EasyMock.mock(Gui.class);
        Card card = new Card();
        card.points = 12;
        card.domesticEffects = new Effect[1];
        Effect mockedEffect = EasyMock.mock(Effect.class);
        card.domesticEffects[0] = mockedEffect;
        Effect effect = new PlayersTradeDragon();
        player.hatchedCards = new ArrayList<>();
        player.hatchedCards.add(card);
        player2.hatchedCards = new ArrayList<>();
        effect.on(Arrays.asList(player, player2)).on(gui);
        // TODO: this should probably be differnet than prompt card because we want to show cards
        // and points and effects
        // need to take playerid
        EasyMock.expect(gui.promptCard("Choose a card to trade", player.hatchedCards)).andReturn(card);
        EasyMock.expect(gui.promptCard("Choose a card to trade", player2.hatchedCards))
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
        Player player = EasyMock.mock(Player.class);
        Player player2 = EasyMock.mock(Player.class);
        Gui gui = EasyMock.mock(Gui.class);
        Card card = new Card();
        card.points = 12;
        card.domesticEffects = new Effect[1];
        Effect mockedEffect = EasyMock.mock(Effect.class);
        card.domesticEffects[0] = mockedEffect;
        Card card3 = new Card();
        card3.points = 12;
        card3.domesticEffects = new Effect[] {mockedEffect};
        Card card2 = new Card();
        card2.points = 12;
        card2.domesticEffects = new Effect[] {mockedEffect};
        Effect effect = new PlayersTradeDragon();
        player.hatchedCards = new ArrayList<>();
        player.hatchedCards.add(card);
        player.hatchedCards.add(card3);
        player2.hatchedCards = new ArrayList<>();
        player2.hatchedCards.add(card2);
        effect.on(Arrays.asList(player, player2)).on(gui);
        // TODO: this should probably be differnet than prompt card because we want to show cards
        // and points and effects
        // need to take playerid

        EasyMock.expect(gui.promptCard("Choose a card to trade", player.hatchedCards)).andReturn(card3);
        EasyMock.expect(gui.promptCard("Choose a card to trade", player2.hatchedCards)).andReturn(card2);

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
        Player player = EasyMock.mock(Player.class);
        Player player2 = EasyMock.mock(Player.class);
        Player player3 = EasyMock.mock(Player.class);
        Gui gui = EasyMock.mock(Gui.class);
        Card card = new Card();
        card.points = 12;
        card.domesticEffects = new Effect[1];
        Effect mockedEffect = EasyMock.mock(Effect.class);
        card.domesticEffects[0] = mockedEffect;
        Card card3 = new Card();
        card3.points = 12;
        card3.domesticEffects = new Effect[] {mockedEffect};
        Card card2 = new Card();
        card2.points = 12;
        card2.domesticEffects = new Effect[] {mockedEffect};
        Effect effect = new PlayersTradeDragon();
        player.hatchedCards = new ArrayList<>();
        player.hatchedCards.add(card);
        player.hatchedCards.add(card3);
        player2.hatchedCards = new ArrayList<>();
        player2.hatchedCards.add(card2);
        player2.hatchedCards.add(card3);
        player3.hatchedCards = new ArrayList<>();
        player3.hatchedCards.add(card3);
        effect.on(Arrays.asList(player, player2, player3)).on(gui);
        Player playerWithLeastDragons = player3;
        // TODO: this should probably be differnet than prompt card because we want to show cards
        // and points and effects
        // need to take playerid
        EasyMock.expect(gui.promptCard("Choose a card to trade", player.hatchedCards)).andReturn(card);
        EasyMock.expect(gui.promptCard("Choose a card to trade", playerWithLeastDragons.hatchedCards)).andReturn(card3);
        EasyMock.expect(gui.promptCard("Choose a card to trade", player2.hatchedCards)).andReturn(card2);
        EasyMock.expect(gui.promptCard("Choose a card to trade", playerWithLeastDragons.hatchedCards)).andReturn(card);

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

}
