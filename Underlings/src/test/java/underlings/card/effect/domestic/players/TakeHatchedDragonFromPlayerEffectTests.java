package underlings.card.effect.domestic.players;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class TakeHatchedDragonFromPlayerEffectTests {

    @Test
    public void testApply() {
        Player player = EasyMock.mock(Player.class);
        Player player2 = EasyMock.mock(Player.class);
        Card card = new Card();
        final Card card2 = new Card();
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        player2.hatchedCards = new LinkedList<>();
        player2.hatchedCards.add(card2);
        card.temperature = Temperature.NEUTRAL;
        card.points = 8;
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(player2.getId()).andReturn(1);
        TakeHatchedDragonFromPlayerEffect effect = new TakeHatchedDragonFromPlayerEffect();
        effect.on(gui).on(Arrays.asList(player, player2)).on(player2);
        Map<Player, List<Card>> map = new HashMap<>();
        map.put(player, Arrays.asList(card));
        EasyMock.expect(
                gui.promptChoice(LocaleUtilities.get("prompt_player_to_steal"), new ArrayList<>(map.keySet()), 0))
                .andReturn(player);
        EasyMock.expect(gui.promptChoice(LocaleUtilities.get("prompt_card_to_steal"), map.get(player), 1))
                .andReturn(card);

        EasyMock.replay(player, gui, player2);

        effect.points = 9;
        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();

        EasyMock.verify(player, gui, player2);
    }

    @Test
    public void testApplyNoHatchedDragon() {
        Player player = EasyMock.mock(Player.class);
        Card card = new Card();
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        Player player2 = EasyMock.mock(Player.class);
        player2.hatchedCards = new LinkedList<>();
        Card card2 = new Card();
        player2.hatchedCards.add(card2);
        Player player3 = EasyMock.mock(Player.class);
        player3.hatchedCards = new LinkedList<>();
        card.temperature = Temperature.NEUTRAL;
        card.points = 8;
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(player2.getId()).andReturn(1);
        TakeHatchedDragonFromPlayerEffect effect = new TakeHatchedDragonFromPlayerEffect();
        effect.on(gui).on(Arrays.asList(player, player2, player3)).on(player2);
        Map<Player, List<Card>> map = new HashMap<>();
        map.put(player, Arrays.asList(card));
        EasyMock.expect(
                gui.promptChoice(LocaleUtilities.get("prompt_player_to_steal"), new ArrayList<>(map.keySet()), 0))
                .andReturn(player);
        EasyMock.expect(gui.promptChoice(LocaleUtilities.get("prompt_card_to_steal"), map.get(player), 1))
                .andReturn(card);

        EasyMock.replay(player, gui, player2, player3);

        effect.points = 9;
        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();

        EasyMock.verify(player, gui, player2, player3);
    }

    @Test
    public void testApplyNoHatchedDragonAllPlayers() {
        Player player = EasyMock.mock(Player.class);
        Player player2 = EasyMock.mock(Player.class);
        player.hatchedCards = new LinkedList<>();
        player2.hatchedCards = new LinkedList<>();
        Gui gui = EasyMock.mock(Gui.class);
        TakeHatchedDragonFromPlayerEffect effect = new TakeHatchedDragonFromPlayerEffect();
        effect.on(gui).on(Arrays.asList(player, player2)).on(player2);
        gui.alert(LocaleUtilities.get("no_player_has_hatched_cards"), PromptType.REGULAR);

        EasyMock.replay(player, gui, player2);

        effect.points = 9;
        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();

        EasyMock.verify(player, gui, player2);
    }

    @Test
    public void testApplyMultipleDragons() {
        Card card = new Card();
        card.temperature = Temperature.WARM;
        card.points = 9;
        Card card2 = new Card();
        card2.temperature = Temperature.NEUTRAL;
        card2.points = 9;
        Player player = EasyMock.mock(Player.class);
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        Player player2 = EasyMock.mock(Player.class);
        player2.hatchedCards = new LinkedList<>();
        Player player3 = EasyMock.mock(Player.class);
        player3.hatchedCards = new LinkedList<>();
        player3.hatchedCards.add(card2);
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(player2.getId()).andReturn(1);
        TakeHatchedDragonFromPlayerEffect effect = new TakeHatchedDragonFromPlayerEffect();
        effect.on(gui).on(Arrays.asList(player, player2, player3)).on(player2);
        Map<Player, List<Card>> map = new HashMap<>();
        map.put(player3, Arrays.asList(card2));

        EasyMock.expect(
                gui.promptChoice(LocaleUtilities.get("prompt_player_to_steal"), new ArrayList<>(map.keySet()), 0))
                .andReturn(player3);
        EasyMock.expect(gui.promptChoice(LocaleUtilities.get("prompt_card_to_steal"), map.get(player3), 1))
                .andReturn(card2);

        EasyMock.replay(player, gui, player2, player3);

        effect.points = 9;
        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();

        EasyMock.verify(player, gui, player2, player3);
    }

    @Test
    public void testApplyMultipleDragonsHigherPoints() {
        Card card = new Card();
        card.temperature = Temperature.NEUTRAL;
        card.points = 10;
        Player player = EasyMock.mock(Player.class);
        player.hatchedCards = new LinkedList<>();
        player.hatchedCards.add(card);
        Player player2 = EasyMock.mock(Player.class);
        player2.hatchedCards = new LinkedList<>();
        Gui gui = EasyMock.mock(Gui.class);
        TakeHatchedDragonFromPlayerEffect effect = new TakeHatchedDragonFromPlayerEffect();
        effect.on(gui).on(Arrays.asList(player, player2)).on(player2);
        Map<Player, List<Card>> map = new HashMap<>();

        gui.alert(LocaleUtilities.get("no_player_has_hatched_cards"), PromptType.REGULAR);

        EasyMock.replay(player, gui, player2);

        effect.points = 9;
        effect.temperatures = new Temperature[] {Temperature.NEUTRAL};
        effect.apply();

        EasyMock.verify(player, gui, player2);
    }

    @Test
    public void testToString() {
        TakeHatchedDragonFromPlayerEffect effect = new TakeHatchedDragonFromPlayerEffect();
        effect.temperatures = new Temperature[] {Temperature.COOL};
        StringBuilder temperature = new StringBuilder();
        for (Temperature temp : effect.temperatures) {
            temperature.append(temp);
            temperature.append(" ");
        }
        assertEquals(LocaleUtilities.format("take_hatched_dragon", temperature, effect.points), effect.toString());
    }

}
