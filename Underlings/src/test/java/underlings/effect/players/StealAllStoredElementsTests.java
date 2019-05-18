package underlings.effect.players;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.StealAllStoredElements;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class StealAllStoredElementsTests extends MockTest {

    @Test
    public void testEffect() {
        Player currentPlayer = EasyMock.mock(Player.class);
        List<Player> players = this.mockListOf(Player.class).withLength(6);
        Gui gui = EasyMock.mock(Gui.class);
        Effect testedEffect = new StealAllStoredElements();
        testedEffect.on(currentPlayer).on(players).on(gui);

        EasyMock.expect(gui.promptChoice(LocaleWrap.get("prompt_war_players"), players, 0)).andReturn(players.get(3));
        currentPlayer.stealAllElementsFromPlayer(players.get(3));

        EasyMock.replay(currentPlayer, gui);
        players.forEach(EasyMock::replay);

        testedEffect.apply();

        EasyMock.verify(currentPlayer, gui);
        players.forEach(EasyMock::verify);
    }

    @Test
    public void testToString() {
        Effect effect = new StealAllStoredElements();
        assertEquals(LocaleWrap.get("take_all_elements_effect"), effect.toString());
    }

}
