package underlings.effect.players;

import java.util.Arrays;
import java.util.LinkedList;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.card.effect.domestic.DeclareWarOnOtherPlayerEffect;
import underlings.gui.Gui;
import underlings.player.Player;

public class DeclareWarOnOtherPlayerEffectTests {

    @Test
    public void testApply() {
        Player player = EasyMock.mock(Player.class);
        player.elements = new LinkedList<>();
        Player currentPlayer = EasyMock.mock(Player.class);
        currentPlayer.elements = new LinkedList<>();
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(currentPlayer.getPlayerId()).andReturn(1);
        EasyMock.expect(gui.promptPlayerToDeclareWarOn(Arrays.asList(player), 1)).andReturn(player);
        Effect effect = new DeclareWarOnOtherPlayerEffect();
        effect.on(currentPlayer).on(gui).on(Arrays.asList(player));

        EasyMock.replay(player, currentPlayer, gui);

        effect.apply();

        EasyMock.verify(player, currentPlayer, gui);
    }

}
