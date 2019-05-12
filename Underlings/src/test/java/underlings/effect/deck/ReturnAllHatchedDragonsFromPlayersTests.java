package underlings.effect.deck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.card.effect.wild.ReturnAllHatchedDragonsFromPlayers;
import underlings.game.Deck;
import underlings.player.Player;

public class ReturnAllHatchedDragonsFromPlayersTests {

    @Test
    public void testApply() {
        Player player = EasyMock.mock(Player.class);
        Card card = new Card();
        card.temperature = Temperature.NEUTRAL;
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        Deck deck = EasyMock.mock(Deck.class);
        ReturnAllHatchedDragonsFromPlayers effect = new ReturnAllHatchedDragonsFromPlayers();
        effect.on(Arrays.asList(player)).on(deck);
        deck.addCard(card);
        EasyMock.replay(player, deck);

        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();
        assertTrue(player.hatchedCards.isEmpty());

        EasyMock.verify(player, deck);
    }

    @Test
    public void testTwoCardsSamePlayer() {
        Player player = EasyMock.mock(Player.class);
        Card card = new Card();
        card.temperature = Temperature.NEUTRAL;
        Card card2 = new Card();
        card2.temperature = Temperature.NEUTRAL;
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        player.hatchedCards.add(card2);
        Deck deck = EasyMock.mock(Deck.class);
        ReturnAllHatchedDragonsFromPlayers effect = new ReturnAllHatchedDragonsFromPlayers();
        effect.on(Arrays.asList(player)).on(deck);
        deck.addCard(card);
        deck.addCard(card2);

        EasyMock.replay(player, deck);

        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();
        assertTrue(player.hatchedCards.isEmpty());

        EasyMock.verify(player, deck);
    }

    @Test
    public void testMultipleCardsAndPlayers() {
        Player player = EasyMock.mock(Player.class);
        Player player2 = EasyMock.mock(Player.class);
        Card card = new Card();
        card.temperature = Temperature.NEUTRAL;
        Card card2 = new Card();
        card2.temperature = Temperature.NEUTRAL;
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        player2.hatchedCards = new LinkedList<>();
        player2.hatchedCards.add(card2);
        Deck deck = EasyMock.mock(Deck.class);
        ReturnAllHatchedDragonsFromPlayers effect = new ReturnAllHatchedDragonsFromPlayers();
        effect.on(Arrays.asList(player, player2)).on(deck);
        deck.addCard(card);
        deck.addCard(card2);

        EasyMock.replay(player, deck, player2);

        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();
        assertTrue(player.hatchedCards.isEmpty());
        assertTrue(player2.hatchedCards.isEmpty());

        EasyMock.verify(player, deck, player2);
    }

    @Test
    public void testMultipleCardsAndPlayersDifferentTemperature() {
        Player player = EasyMock.mock(Player.class);
        Player player2 = EasyMock.mock(Player.class);
        Card card = new Card();
        card.temperature = Temperature.COOL;
        Card card2 = new Card();
        card2.temperature = Temperature.NEUTRAL;
        Card card3 = new Card();
        card3.temperature = Temperature.WARM;
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        player2.hatchedCards = new LinkedList<>();
        player2.hatchedCards.add(card2);
        player2.hatchedCards.add(card3);
        Deck deck = EasyMock.mock(Deck.class);
        ReturnAllHatchedDragonsFromPlayers effect = new ReturnAllHatchedDragonsFromPlayers();
        effect.on(Arrays.asList(player, player2)).on(deck);
        deck.addCard(card2);

        EasyMock.replay(player, deck, player2);

        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();
        assertEquals(1, player2.hatchedCards.size());
        assertTrue(player2.hatchedCards.contains(card3));

        EasyMock.verify(player, deck, player2);
    }

}
