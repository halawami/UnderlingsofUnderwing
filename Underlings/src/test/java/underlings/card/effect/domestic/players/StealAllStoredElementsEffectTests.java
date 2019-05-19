package underlings.card.effect.domestic.players;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.Effect;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class StealAllStoredElementsEffectTests extends MockTest {

    @Test
    public void testEffect() {
        Player currentPlayer = this.mock(Player.class);
        List<Player> players = this.mockListOf(Player.class).withLengthOf(6);
        Gui gui = this.mock(Gui.class);
        Effect effect = new StealAllStoredElementsEffect();

        EasyMock.expect(gui.promptChoice(LocaleUtilities.get("prompt_war_players"), players, 0))
                .andReturn(players.get(3));
        currentPlayer.stealAllElementsFromPlayer(players.get(3));

        this.replayAll();

        effect.on(currentPlayer).on(players).on(gui).apply();
    }

    @Test
    public void testToString() {
        Effect effect = new StealAllStoredElementsEffect();
        assertEquals(LocaleUtilities.get("take_all_elements_effect"), effect.toString());
    }

}
