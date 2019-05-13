package underlings.effect.players;

import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.card.effect.domestic.DeclareWarOnOtherPlayerEffect;
import underlings.gui.Gui;
import underlings.player.Player;

public class DeclareWarOnOtherPlayerEffectTests {

    @Test
    public void test() {
        Player player = EasyMock.mock(Player.class);
        Player currentPlayer = EasyMock.mock(Player.class);
        Gui gui = EasyMock.mock(Gui.class);
        gui.promptPlayerToDeclareWarOn(Arrays.asList(player), 1);
        Effect effect = new DeclareWarOnOtherPlayerEffect();
        effect.on(gui).on(Arrays.asList(player));

        effect.apply();
    }

}
