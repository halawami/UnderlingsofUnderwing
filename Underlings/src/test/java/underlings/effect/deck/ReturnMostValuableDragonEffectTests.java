package underlings.effect.deck;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.effect.wild.ReturnMostValuableDragonEffect;
import underlings.game.Deck;
import underlings.gui.Gui;
import underlings.player.Player;

public class ReturnMostValuableDragonEffectTests {

    @Test
    public void testApplyEffectOnTwoPlayers() {
        List<Player> players = TestUtils.mockListOf(Player.class).withLength(2);
        Deck deck = EasyMock.mock(Deck.class);
        Gui gui = EasyMock.mock(Gui.class);
        ReturnMostValuableDragonEffect testedEffect = EasyMock.partialMockBuilder(ReturnMostValuableDragonEffect.class)
                .addMockedMethod("returnMostValueDragon").createMock();
        testedEffect.on(players).on(deck).on(gui);

        players.forEach(player -> testedEffect.returnMostValueDragon(players, deck, gui));

        EasyMock.replay(deck, gui, testedEffect);
        players.forEach(EasyMock::replay);

        testedEffect.apply();

        EasyMock.verify(deck, gui, testedEffect);
        players.forEach(EasyMock::verify);
    }

    @Test
    public void testApplyEffectOnSixPlayers() {
        List<Player> players = TestUtils.mockListOf(Player.class).withLength(6);
        Deck deck = EasyMock.mock(Deck.class);
        Gui gui = EasyMock.mock(Gui.class);
        ReturnMostValuableDragonEffect testedEffect = EasyMock.partialMockBuilder(ReturnMostValuableDragonEffect.class)
                .addMockedMethod("returnMostValueDragon").createMock();
        testedEffect.on(players).on(deck).on(gui);

        players.forEach(player -> testedEffect.returnMostValueDragon(players, deck, gui));

        EasyMock.replay(deck, gui, testedEffect);
        players.forEach(EasyMock::replay);

        testedEffect.apply();

        EasyMock.verify(deck, gui, testedEffect);
        players.forEach(EasyMock::verify);
    }

}
