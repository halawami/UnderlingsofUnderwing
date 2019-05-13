package underlings.effect.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.Card;
import underlings.card.effect.wild.ReturnMostValuableDragonEffect;
import underlings.game.Deck;
import underlings.gui.Gui;
import underlings.player.Player;

public class ReturnMostValuableDragonEffectTests {

    @Test
    public void testApplyEffectOnTwoPlayers() {
        this.testApplyEffectOnPlayers(2);
    }

    @Test
    public void testApplyEffectOnSixPlayers() {
        this.testApplyEffectOnPlayers(6);
    }

    private void testApplyEffectOnPlayers(int numberOfPlayers) {
        List<Player> players = TestUtils.mockListOf(Player.class).withLength(numberOfPlayers);
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
        List<Card> mostValuableDragons = TestUtils.mockListOf(Card.class).withLength(1);
        player.hatchedCards = new ArrayList<>(mostValuableDragons);

        EasyMock.expect(player.getMostValuableDragons()).andReturn(mostValuableDragons);
        EasyMock.expect(gui.promptCard("Pick a dragon to return to deck", mostValuableDragons))
                .andReturn(mostValuableDragons.get(0));
        deck.addCard(mostValuableDragons.get(0));

        EasyMock.replay(player, deck, gui);

        ReturnMostValuableDragonEffect testedEffect = new ReturnMostValuableDragonEffect();
        testedEffect.returnMostValuableDragon(player, deck, gui);

        Assert.assertFalse(player.hatchedCards.contains(mostValuableDragons.get(0)));
        EasyMock.verify(player, deck, gui);
    }



}
