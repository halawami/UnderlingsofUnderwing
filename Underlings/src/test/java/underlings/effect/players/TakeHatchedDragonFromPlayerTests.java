package underlings.effect.players;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.card.effect.domestic.TakeHatchedDragonFromPlayer;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class TakeHatchedDragonFromPlayerTests {

    @Test
    public void testApply() {
        Player player = EasyMock.mock(Player.class);
        Player player2 = EasyMock.mock(Player.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        Card card = new Card();
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        card.temperature = Temperature.NEUTRAL;
        card.points = 8;
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(player2.getPlayerId()).andReturn(1);
        TakeHatchedDragonFromPlayer effect = new TakeHatchedDragonFromPlayer();
        effect.on(gui).on(Arrays.asList(player)).on(player2).on(eggHatchingLogic);
        Map<Player, List<Card>> map = new HashMap<>();
        map.put(player, Arrays.asList(card));
        EasyMock.expect(gui.promptCardToSteal("Choose a card to steal", 1, map)).andReturn(card);
        eggHatchingLogic.hatchEgg(card, false, player2);

        EasyMock.replay(player, gui, player2, eggHatchingLogic);

        effect.points = 9;
        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();

        EasyMock.verify(player, gui, player2, eggHatchingLogic);
    }

}
