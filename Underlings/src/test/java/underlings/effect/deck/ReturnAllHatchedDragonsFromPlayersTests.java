package underlings.effect.deck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        this.player = this.mock(Player.class);
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
    public void testApplyOnSixPlayers() {
        this.players = this.mockListOf(Player.class).withLengthOf(6);
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
    public void testRemoveFromNoHatchedCards() {
        ReturnAllHatchedDragonsFromPlayers effect = new ReturnAllHatchedDragonsFromPlayers();
        this.player.hatchedCards = new ArrayList<>();

        this.replayAll();

        effect.removeCardsOfTemperature(this.deck, Arrays.asList(Temperature.NEUTRAL), this.player);

        assertEquals(0, this.player.hatchedCards.size());
    }

    @Test
    public void testRemoveFromOneHatchedCards() {
        ReturnAllHatchedDragonsFromPlayers effect = new ReturnAllHatchedDragonsFromPlayers();
        this.player.hatchedCards = this.mockListOf(Card.class).withLengthOf(1);
        this.player.hatchedCards.get(0).temperature = Temperature.NEUTRAL;

        this.deck.addCard(this.player.hatchedCards.get(0), true);

        this.replayAll();

        effect.removeCardsOfTemperature(this.deck, Arrays.asList(Temperature.NEUTRAL), this.player);

        assertEquals(0, this.player.hatchedCards.size());
    }

    @Test
    public void testRemoveOneCard() {
        ReturnAllHatchedDragonsFromPlayers effect = new ReturnAllHatchedDragonsFromPlayers();
        List<Card> mockHatchedCards = this.mockListOf(Card.class).withLengthOf(6);
        this.player.hatchedCards = new ArrayList<>(mockHatchedCards);
        this.player.hatchedCards.get(0).temperature = Temperature.NEUTRAL;

        this.deck.addCard(this.player.hatchedCards.get(0), true);

        this.replayAll();

        effect.removeCardsOfTemperature(this.deck, Arrays.asList(Temperature.NEUTRAL), this.player);

        assertEquals(5, this.player.hatchedCards.size());
        assertFalse(this.player.hatchedCards.contains(mockHatchedCards.get(0)));
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
