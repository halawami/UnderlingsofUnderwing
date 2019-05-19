package underlings.effect.deck;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.wild.deck.ReturnMostValuableDragonEffect;
import underlings.gui.Gui;
import underlings.hatchingground.Deck;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class ReturnMostValuableDragonEffectTests extends MockTest {

    @Test
    public void testApplyEffectOnTwoPlayers() {
        this.testApplyEffectOnPlayers(2);
    }

    @Test
    public void testApplyEffectOnSixPlayers() {
        this.testApplyEffectOnPlayers(6);
    }

    private void testApplyEffectOnPlayers(int numberOfPlayers) {
        List<Player> players = this.mockListOf(Player.class).withLengthOf(numberOfPlayers);
        Deck deck = this.mock(Deck.class);
        Gui gui = this.mock(Gui.class);
        ReturnMostValuableDragonEffect effect = EasyMock.partialMockBuilder(ReturnMostValuableDragonEffect.class)
                .addMockedMethod("returnMostValuableDragon").createMock();
        this.addMock(effect);

        players.forEach(player -> effect.returnMostValuableDragon(player, deck, gui));

        this.replayAll();

        effect.on(players).on(deck).on(gui).apply();
    }

    @Test
    public void testReturnMostValuableDragonNoDragons() {
        Player player = this.mock(Player.class);
        Deck deck = this.mock(Deck.class);
        Gui gui = this.mock(Gui.class);
        List<Card> mostValuableDragons = Collections.emptyList();

        EasyMock.expect(player.getMostValuableDragons()).andReturn(mostValuableDragons);

        this.replayAll();

        ReturnMostValuableDragonEffect effect = new ReturnMostValuableDragonEffect();
        effect.returnMostValuableDragon(player, deck, gui);
    }

    @Test
    public void testReturnMostValuableDragonOneDragon() {
        Player player = this.mock(Player.class);
        Gui gui = this.mock(Gui.class);
        List<Card> mostValuableDragons = this.mockListOf(Card.class).withLengthOf(1);
        player.hatchedCards = new ArrayList<>(mostValuableDragons);

        EasyMock.expect(player.getMostValuableDragons()).andReturn(mostValuableDragons);
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_dragon_return_deck"), mostValuableDragons, 0))
                .andReturn(mostValuableDragons.get(0));
        Deck deck = this.mock(Deck.class);
        deck.addCard(mostValuableDragons.get(0), true);

        this.replayAll();

        ReturnMostValuableDragonEffect effect = new ReturnMostValuableDragonEffect();
        effect.returnMostValuableDragon(player, deck, gui);

        Assert.assertFalse(player.hatchedCards.contains(mostValuableDragons.get(0)));
    }

    @Test
    public void testReturnMostValuableDragonTwoDragons() {
        Player player = this.mock(Player.class);
        Gui gui = this.mock(Gui.class);
        List<Card> mostValuableDragons = this.mockListOf(Card.class).withLengthOf(2);
        player.hatchedCards = new ArrayList<>(mostValuableDragons);

        EasyMock.expect(player.getMostValuableDragons()).andReturn(mostValuableDragons);
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_dragon_return_deck"), mostValuableDragons, 0))
                .andReturn(mostValuableDragons.get(1));
        Deck deck = this.mock(Deck.class);
        deck.addCard(mostValuableDragons.get(1), true);

        this.replayAll();

        ReturnMostValuableDragonEffect effect = new ReturnMostValuableDragonEffect();
        effect.returnMostValuableDragon(player, deck, gui);

        Assert.assertFalse(player.hatchedCards.contains(mostValuableDragons.get(1)));
    }

    @Test
    public void testToString() {
        ReturnMostValuableDragonEffect effect = new ReturnMostValuableDragonEffect();
        assertEquals(LocaleWrap.get("return_most_valuable_dragon_effect"), effect.toString());
    }

}
