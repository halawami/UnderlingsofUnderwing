package underlings.effect.players;

import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.TakeHatchedDragonFromPlayer;
import underlings.gui.Gui;
import underlings.player.Player;

public class TakeHatchedDragonFromPlayerTests {

    @Test
    public void testApply() {
        Player player = EasyMock.mock(Player.class);
        Card card = new Card();
        card.temperature = Temperature.NEUTRAL;
        card.points = 8;
        Gui gui = EasyMock.mock(Gui.class);
        Effect effect = new TakeHatchedDragonFromPlayer();
        effect.on(gui).on(Arrays.asList(player));
        EasyMock.expect(gui.promptCardToSteal("Choose a card to steal", 1, Arrays.asList(card))).andReturn(card);

        EasyMock.replay(player, gui);

        effect.apply();

        EasyMock.verify(player, gui);
    }

}
