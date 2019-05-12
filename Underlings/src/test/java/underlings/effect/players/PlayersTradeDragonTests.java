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
    public void testApply() {
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
        Player playerWithLeastDragons = player2;
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
        assertEquals(0, player.hatchedCards.size());

        EasyMock.verify(player, player2, mockedEffect, gui);
    }

}
