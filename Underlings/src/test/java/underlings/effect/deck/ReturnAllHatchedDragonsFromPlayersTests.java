package underlings.effect.deck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.Temperature;
import underlings.card.effect.wild.ReturnAllHatchedDragonsFromPlayers;
import underlings.game.Deck;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class ReturnAllHatchedDragonsFromPlayersTests extends MockTest {

    @Before
    public void init() {
        this.deck = this.mock(Deck.class);
        this.card = new Card();
        this.card2 = new Card();
        this.card3 = new Card();
    }

    @Test
    public void testApplyOnTwoPlayers() {
        this.players = this.mockListOf(Player.class).withLengthOf(2);
        ReturnAllHatchedDragonsFromPlayers effect = EasyMock
                .partialMockBuilder(ReturnAllHatchedDragonsFromPlayers.class)
                .addMockedMethod("removeCardsOfTemperature").createMock();
        this.addMock(effect);
        effect.temperatures = new Temperature[]{Temperature.NEUTRAL};
        effect.on(this.players).on(this.deck);

        for (Player player : players) {
            effect.removeCardsOfTemperature(this.deck, Arrays.asList(effect.temperatures), player);
        }

        this.replayAll();

        effect.apply();
    }


    @Test
    public void testTwoCardsSamePlayer() {
        this.card.temperature = Temperature.NEUTRAL;
        this.card2.temperature = Temperature.NEUTRAL;
        this.player.hatchedCards = new LinkedList<>();
        this.player.hatchedCards.add(this.card);
        this.player.hatchedCards.add(this.card2);

        ReturnAllHatchedDragonsFromPlayers effect = new ReturnAllHatchedDragonsFromPlayers();
        effect.on(Arrays.asList(this.player)).on(this.deck);
        this.deck.addCard(this.card, true);
        this.deck.addCard(this.card2, true);

        this.replayAll();

        effect.temperatures = new Temperature[]{Temperature.NEUTRAL};
        effect.apply();

        assertTrue(this.player.hatchedCards.isEmpty());
    }

    @Test
    public void testMultipleCardsAndPlayers() {
        this.card.temperature = Temperature.NEUTRAL;
        this.card2.temperature = Temperature.NEUTRAL;
        this.player.hatchedCards = new LinkedList<>();
        this.player.hatchedCards.add(this.card);
        this.player2.hatchedCards = new LinkedList<>();
        this.player2.hatchedCards.add(this.card2);

        ReturnAllHatchedDragonsFromPlayers effect = new ReturnAllHatchedDragonsFromPlayers();
        effect.on(Arrays.asList(this.player, this.player2)).on(this.deck);
        this.deck.addCard(this.card, true);
        this.deck.addCard(this.card2, true);

        this.replayAll();

        effect.temperatures = new Temperature[]{Temperature.NEUTRAL};
        effect.apply();
        assertTrue(this.player.hatchedCards.isEmpty());
        assertTrue(this.player2.hatchedCards.isEmpty());
    }

    @Test
    public void testMultipleCardsAndPlayersDifferentTemperature() {
        this.card.temperature = Temperature.COOL;
        this.card2.temperature = Temperature.NEUTRAL;
        this.card3.temperature = Temperature.WARM;
        this.player.hatchedCards = new LinkedList<>();
        this.player.hatchedCards.add(this.card);
        this.player2.hatchedCards = new LinkedList<>();
        this.player2.hatchedCards.add(this.card2);
        this.player2.hatchedCards.add(this.card3);

        ReturnAllHatchedDragonsFromPlayers effect = new ReturnAllHatchedDragonsFromPlayers();
        effect.on(Arrays.asList(this.player, this.player2)).on(this.deck);
        this.deck.addCard(this.card2, true);

        this.replayAll();

        effect.temperatures = new Temperature[]{Temperature.NEUTRAL};
        effect.apply();
        assertEquals(1, this.player2.hatchedCards.size());
        assertTrue(this.player2.hatchedCards.contains(this.card3));
    }

    @Test
    public void testToString() {
        this.replayAll();
        ReturnAllHatchedDragonsFromPlayers effect = new ReturnAllHatchedDragonsFromPlayers();
        effect.temperatures = new Temperature[]{Temperature.COOL};
        StringBuilder temperature = new StringBuilder();
        for (Temperature temp : effect.temperatures) {
            temperature.append(temp);
            temperature.append(" ");
        }
        assertEquals(LocaleWrap.format("return_hatched_dragons", temperature), effect.toString());
    }

}
