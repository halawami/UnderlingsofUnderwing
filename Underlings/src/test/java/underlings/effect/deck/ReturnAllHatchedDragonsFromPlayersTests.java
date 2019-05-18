package underlings.effect.deck;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

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
