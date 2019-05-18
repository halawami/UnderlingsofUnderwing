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
import underlings.card.effect.wild.ReturnMostValuableDragonEffect;
import underlings.game.Deck;
import underlings.gui.Gui;
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
        Deck deck = EasyMock.mock(Deck.class);
        Gui gui = EasyMock.mock(Gui.class);
        ReturnMostValuableDragonEffect testedEffect = EasyMock.partialMockBuilder(ReturnMostValuableDragonEffect.class)
                .addMockedMethod("returnMostValuableDragon").createMock();
        testedEffect.on(players).on(deck).on(gui);

        players.forEach(player -> testedEffect.returnMostValuableDragon(player, deck, gui));

        EasyMock.replay(deck, gui, testedEffect);
        players.forEach(EasyMock::replay);

        testedEffect.apply();

        EasyMock.verify(deck, gui, testedEffect);
        players.forEach(EasyMock::verify);
    }

    @Test
    public void testReturnMostValuableDragonNoDragons() {
        Player player = EasyMock.mock(Player.class);
        Deck deck = EasyMock.mock(Deck.class);
        Gui gui = EasyMock.mock(Gui.class);
        List<Card> mostValuableDragons = Collections.emptyList();

        EasyMock.expect(player.getMostValuableDragons()).andReturn(mostValuableDragons);

        EasyMock.replay(player, deck, gui);

        ReturnMostValuableDragonEffect testedEffect = new ReturnMostValuableDragonEffect();
        testedEffect.returnMostValuableDragon(player, deck, gui);

        EasyMock.verify(player, deck, gui);
    }

    @Test
    public void testReturnMostValuableDragonOneDragon() {
        Player player = EasyMock.mock(Player.class);
        Deck deck = EasyMock.mock(Deck.class);
        Gui gui = EasyMock.mock(Gui.class);
        List<Card> mostValuableDragons = this.mockListOf(Card.class).withLengthOf(1);
        player.hatchedCards = new ArrayList<>(mostValuableDragons);

        EasyMock.expect(player.getMostValuableDragons()).andReturn(mostValuableDragons);
        EasyMock.expect(gui.promptChoice("Pick a dragon to return to deck", mostValuableDragons, 0))
                .andReturn(mostValuableDragons.get(0));
        deck.addCard(mostValuableDragons.get(0));

        EasyMock.replay(player, deck, gui);

        ReturnMostValuableDragonEffect testedEffect = new ReturnMostValuableDragonEffect();
        testedEffect.returnMostValuableDragon(player, deck, gui);

        Assert.assertFalse(player.hatchedCards.contains(mostValuableDragons.get(0)));
        EasyMock.verify(player, deck, gui);
    }

    @Test
    public void testReturnMostValuableDragonTwoDragons() {
        Player player = EasyMock.mock(Player.class);
        Deck deck = EasyMock.mock(Deck.class);
        Gui gui = EasyMock.mock(Gui.class);
        List<Card> mostValuableDragons = this.mockListOf(Card.class).withLengthOf(2);
        player.hatchedCards = new ArrayList<>(mostValuableDragons);

        EasyMock.expect(player.getMostValuableDragons()).andReturn(mostValuableDragons);
        EasyMock.expect(gui.promptChoice("Pick a dragon to return to deck", mostValuableDragons, 0))
                .andReturn(mostValuableDragons.get(1));
        deck.addCard(mostValuableDragons.get(1));

        EasyMock.replay(player, deck, gui);

        ReturnMostValuableDragonEffect testedEffect = new ReturnMostValuableDragonEffect();
        testedEffect.returnMostValuableDragon(player, deck, gui);

        Assert.assertFalse(player.hatchedCards.contains(mostValuableDragons.get(1)));
        EasyMock.verify(player, deck, gui);
    }

    @Test
    public void testToString() {
        ReturnMostValuableDragonEffect effect = new ReturnMostValuableDragonEffect();
        assertEquals(LocaleWrap.get("return_most_valuable_dragon_effect"), effect.toString());
    }

}
