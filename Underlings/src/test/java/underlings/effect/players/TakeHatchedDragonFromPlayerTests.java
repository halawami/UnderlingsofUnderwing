package underlings.effect.players;

import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.card.effect.domestic.TakeHatchedDragonFromPlayer;
import underlings.gui.Gui;
import underlings.player.Player;

public class TakeHatchedDragonFromPlayerTests {

    @Test
    public void testApply() {
        Player player = EasyMock.mock(Player.class);
        Gui gui = EasyMock.mock(Gui.class);
        Effect effect = new TakeHatchedDragonFromPlayer();
        effect.on(gui).on(Arrays.asList(player));
        gui.promptPlayer("Choose a player to take a dragon from", 1);


        effect.apply();
    }

}
