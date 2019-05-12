package underlings.effect.deck;

import java.util.Arrays;
import java.util.LinkedList;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.card.effect.Effect;
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
        Effect effect = new ReturnAllHatchedDragonsFromPlayers();
        effect.on(Arrays.asList(player)).on(deck);
        deck.addCard(card);
        EasyMock.replay(player, deck);

        effect.apply();

        EasyMock.verify(player, deck);
    }

    @Test
    public void testTwoCardsSamePlayer() {
        Player player = EasyMock.mock(Player.class);
        Card card = new Card();
        card.temperature = Temperature.NEUTRAL;
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        player.hatchedCards.add(card);
        Deck deck = EasyMock.mock(Deck.class);
        Effect effect = new ReturnAllHatchedDragonsFromPlayers();
        effect.on(Arrays.asList(player)).on(deck);
        deck.addCard(card);
        EasyMock.expectLastCall().times(2);
        EasyMock.replay(player, deck);

        effect.apply();

        EasyMock.verify(player, deck);
    }

}
