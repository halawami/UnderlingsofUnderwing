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
import underlings.utilities.LocaleWrap;

public class TakeHatchedDragonFromPlayerTests {

    @Test
    public void testApply() {
        Player player = EasyMock.mock(Player.class);
        Player player2 = EasyMock.mock(Player.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        Card card = new Card();
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        player2.hatchedCards = new LinkedList<>();
        card.temperature = Temperature.NEUTRAL;
        card.points = 8;
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(player2.getPlayerId()).andReturn(1);
        TakeHatchedDragonFromPlayer effect = new TakeHatchedDragonFromPlayer();
        effect.on(gui).on(Arrays.asList(player, player2)).on(player2).on(eggHatchingLogic);
        Map<Player, List<Card>> map = new HashMap<>();
        map.put(player, Arrays.asList(card));
        EasyMock.expect(gui.promptCardToSteal(LocaleWrap.get("prompt_card_to_steal"), 1, map)).andReturn(card);
        eggHatchingLogic.hatchEgg(card, false, player2);

        EasyMock.replay(player, gui, player2, eggHatchingLogic);

        effect.points = 9;
        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();

        EasyMock.verify(player, gui, player2, eggHatchingLogic);
    }

    @Test
    public void testApplyMultipleDragons() {
        Player player = EasyMock.mock(Player.class);
        Player player2 = EasyMock.mock(Player.class);
        Player player3 = EasyMock.mock(Player.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        Card card = new Card();
        card.temperature = Temperature.WARM;
        card.points = 8;
        Card card2 = new Card();
        card2.temperature = Temperature.NEUTRAL;
        card2.points = 8;
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        player2.hatchedCards = new LinkedList<>();
        player3.hatchedCards = new LinkedList<>();
        player3.hatchedCards.add(card2);
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(player2.getPlayerId()).andReturn(1);
        TakeHatchedDragonFromPlayer effect = new TakeHatchedDragonFromPlayer();
        effect.on(gui).on(Arrays.asList(player, player2, player3)).on(player2).on(eggHatchingLogic);
        Map<Player, List<Card>> map = new HashMap<>();
        map.put(player, Arrays.asList());
        map.put(player3, Arrays.asList(card2));
        EasyMock.expect(gui.promptCardToSteal(LocaleWrap.get("prompt_card_to_steal"), 1, map)).andReturn(card2);
        eggHatchingLogic.hatchEgg(card2, false, player2);

        EasyMock.replay(player, gui, player2, eggHatchingLogic);

        effect.points = 9;
        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();

        EasyMock.verify(player, gui, player2, eggHatchingLogic);
    }

    @Test
    public void testApplyMultipleDragonsHigherPoints() {
        Player player = EasyMock.mock(Player.class);
        Player player2 = EasyMock.mock(Player.class);
        Player player3 = EasyMock.mock(Player.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        Card card = new Card();
        card.temperature = Temperature.NEUTRAL;
        card.points = 10;
        Card card2 = new Card();
        card2.temperature = Temperature.NEUTRAL;
        card2.points = 8;
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        player2.hatchedCards = new LinkedList<>();
        player3.hatchedCards = new LinkedList<>();
        player3.hatchedCards.add(card2);
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(player2.getPlayerId()).andReturn(1);
        TakeHatchedDragonFromPlayer effect = new TakeHatchedDragonFromPlayer();
        effect.on(gui).on(Arrays.asList(player, player2, player3)).on(player2).on(eggHatchingLogic);
        Map<Player, List<Card>> map = new HashMap<>();
        map.put(player, Arrays.asList());
        map.put(player3, Arrays.asList(card2));
        EasyMock.expect(gui.promptCardToSteal(LocaleWrap.get("prompt_card_to_steal"), 1, map)).andReturn(card2);
        eggHatchingLogic.hatchEgg(card2, false, player2);

        EasyMock.replay(player, gui, player2, eggHatchingLogic);

        effect.points = 9;
        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();

        EasyMock.verify(player, gui, player2, eggHatchingLogic);
    }
}
