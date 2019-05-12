package underlings.effect.players;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.card.effect.domestic.StealAllStoredElements;
import underlings.effect.EffectTestUtils;
import underlings.gui.Gui;
import underlings.player.Player;

public class StealAllStoredElementsTests {

    @Test
    public void testEffect() {
        Player currentPlayer = EasyMock.mock(Player.class);
        List<Player> players = EffectTestUtils.getMockObjects(Player.class, 6);
        Gui gui = EasyMock.mock(Gui.class);
        Effect testedEffect = new StealAllStoredElements();
        testedEffect.on(currentPlayer).on(players).on(gui);

        EasyMock.expect(currentPlayer.getPlayerId()).andReturn(1);
        EasyMock.expect(gui.promptPlayer("Choose a player to take all stored elements from", 1, players))
                .andReturn(players.get(3));
        currentPlayer.stealAllElementsFromPlayer(players.get(3));

        EasyMock.replay(currentPlayer, gui);
        players.forEach(EasyMock::replay);

        testedEffect.apply();

        EasyMock.verify(currentPlayer, gui);
        players.forEach(EasyMock::verify);
    }

}
