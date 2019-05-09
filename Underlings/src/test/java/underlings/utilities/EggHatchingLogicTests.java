package underlings.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.phase.DragonPhase;
import underlings.phase.Phase;
import underlings.player.Player;
import underlings.player.PlayerFactory;

public class EggHatchingLogicTests {

    @Test
    public void testOneWildEffect() {
        Card card = new Card();
        card.wildEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        card.wildEffects[0] = effect;
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        PlayerFactory playerFactory = new PlayerFactory(new HandlerFactory());
        Player player = playerFactory.createPlayer(2);
        player.elementSpaceLogic = new ElementSpaceLogic();
        EasyMock.expect(card.wildEffects[0].on(elementBag)).andReturn(card.wildEffects[0]);
        EasyMock.expect(card.wildEffects[0].on(hatchingGround)).andReturn(card.wildEffects[0]);
        EasyMock.expect(card.wildEffects[0].on(player.elementSpaceLogic)).andReturn(card.wildEffects[0]);
        EasyMock.expect(card.wildEffects[0].on(player)).andReturn(card.wildEffects[0]);
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(card.wildEffects[0].on(gui)).andReturn(card.wildEffects[0]);
        card.wildEffects[0].apply();
        gui.notifyAction(-1, card.wildEffects[0].toString() + " has been applied");

        EasyMock.replay(effect, elementBag, hatchingGround, gui);
        EggHatchingLogic wildEggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround, playerFactory);
        wildEggHatchingLogic.hatchEgg(card, true);

        EasyMock.verify(effect, elementBag, hatchingGround, gui);
    }

    @Test
    public void testTwoWildEffects() {
        Card card = new Card();
        card.wildEffects = new Effect[2];
        Effect effect = EasyMock.mock(Effect.class);
        card.wildEffects[0] = effect;
        card.wildEffects[1] = effect;
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        PlayerFactory playerFactory = new PlayerFactory(new HandlerFactory());
        Player player = playerFactory.createPlayer(2);
        player.elementSpaceLogic = new ElementSpaceLogic();
        EasyMock.expect(card.wildEffects[0].on(elementBag)).andReturn(card.wildEffects[0]).times(2);
        EasyMock.expect(card.wildEffects[0].on(hatchingGround)).andReturn(card.wildEffects[0]).times(2);
        EasyMock.expect(card.wildEffects[0].on(player.elementSpaceLogic)).andReturn(card.wildEffects[0]).times(2);
        EasyMock.expect(card.wildEffects[0].on(player)).andReturn(card.wildEffects[0]).times(2);
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(card.wildEffects[0].on(gui)).andReturn(card.wildEffects[0]).times(2);
        card.wildEffects[0].apply();
        EasyMock.expectLastCall().times(2);

        gui.notifyAction(-1, card.wildEffects[0].toString() + " has been applied");
        EasyMock.expectLastCall().times(2);

        EasyMock.replay(effect, elementBag, hatchingGround, gui);

        EggHatchingLogic wildEggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround, playerFactory);
        wildEggHatchingLogic.hatchEgg(card, true);

        EasyMock.verify(effect, elementBag, hatchingGround, gui);
    }

    @Test
    public void testOneDomesticEffect() {
        Card card = new Card();
        card.domesticEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        card.domesticEffects[0] = effect;
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        PlayerFactory playerFactory = new PlayerFactory(new HandlerFactory());
        Player player = playerFactory.createPlayer(2);
        player.elementSpaceLogic = new ElementSpaceLogic();
        EasyMock.expect(card.domesticEffects[0].on(elementBag)).andReturn(card.domesticEffects[0]);
        EasyMock.expect(card.domesticEffects[0].on(hatchingGround)).andReturn(card.domesticEffects[0]);
        EasyMock.expect(card.domesticEffects[0].on(player.elementSpaceLogic)).andReturn(card.domesticEffects[0]);
        EasyMock.expect(card.domesticEffects[0].on(player)).andReturn(card.domesticEffects[0]);
        EasyMock.expect(player.getPlayerId()).andReturn(1);
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(card.domesticEffects[0].on(gui)).andReturn(card.domesticEffects[0]);
        card.domesticEffects[0].apply();
        gui.notifyAction(1, card.domesticEffects[0].toString() + " has been applied");

        EasyMock.replay(effect, elementBag, hatchingGround, gui);
        EggHatchingLogic wildEggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround, playerFactory);
        wildEggHatchingLogic.hatchEgg(card, false);

        EasyMock.verify(effect, elementBag, hatchingGround, gui);
    }

    @Test
    public void testReturnPurpleComboElements() {
        Card card = new Card();
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        card.elementSpaces = new ElementSpace[1];
        card.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);
        card.elementSpaces[0].elements = Arrays.asList(ElementColor.RED, ElementColor.BLUE);
        PlayerFactory playerFactory = new PlayerFactory(new HandlerFactory());
        Player player = playerFactory.createPlayer(2);
        player.unhatchedCards = new ArrayList<>();
        Gui gui = EasyMock.mock(Gui.class);
        player.elementSpaceLogic = new ElementSpaceLogic();
        ElementBag bag = EasyMock.mock(ElementBag.class);
        bag.putElement(ElementColor.RED);
        bag.putElement(ElementColor.BLUE);
        EasyMock.replay(hatchingGround, bag, gui);

        EggHatchingLogic eggHatchingLogic = new EggHatchingLogic(gui, bag, hatchingGround, playerFactory);
        eggHatchingLogic.returnElementsToBag(card);

        EasyMock.verify(hatchingGround, bag, gui);
    }

    @Test
    public void testReturnNoElements() {
        Card card = new Card();
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        card.elementSpaces = new ElementSpace[1];
        card.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);
        card.elementSpaces[0].elements = Arrays.asList(ElementColor.RED, ElementColor.BLUE);
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList());
        ElementBag bag = EasyMock.mock(ElementBag.class);
        Player player = EasyMock.mock(Player.class);
        player.unhatchedCards = new ArrayList<>();
        List<Player> players = Arrays.asList(player);

        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        EasyMock.replay(hatchingGround, bag, player, eggHatchingLogic);

        Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null, eggHatchingLogic);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player, eggHatchingLogic);
    }

    @Test
    public void testReturnOrangeComboElements() {
        Card card = new Card();
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        card.elementSpaces = new ElementSpace[1];
        card.elementSpaces[0] = new ElementSpace(ElementColor.ORANGE);
        card.elementSpaces[0].elements = Arrays.asList(ElementColor.RED, ElementColor.YELLOW);
        PlayerFactory playerFactory = new PlayerFactory(new HandlerFactory());
        Player player = playerFactory.createPlayer(2);
        player.unhatchedCards = new ArrayList<>();
        Gui gui = EasyMock.mock(Gui.class);
        player.elementSpaceLogic = new ElementSpaceLogic();
        ElementBag bag = EasyMock.mock(ElementBag.class);
        bag.putElement(ElementColor.RED);
        bag.putElement(ElementColor.YELLOW);
        EasyMock.replay(hatchingGround, bag, gui);

        EggHatchingLogic eggHatchingLogic = new EggHatchingLogic(gui, bag, hatchingGround, playerFactory);
        eggHatchingLogic.returnElementsToBag(card);

        EasyMock.verify(hatchingGround, bag, gui);
    }

    @Test
    public void testMultiReturnComboElements() {
        Card card = new Card();
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        card.elementSpaces = new ElementSpace[1];
        card.elementSpaces[0] = new ElementSpace(ElementColor.ORANGE);
        card.elementSpaces[0].elements = Arrays.asList(ElementColor.RED, ElementColor.YELLOW);
        Card card2 = new Card();
        card2.elementSpaces = new ElementSpace[1];
        card2.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);
        card2.elementSpaces[0].elements = Arrays.asList(ElementColor.RED, ElementColor.BLUE);
        EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(Arrays.asList(card, card2));
        ElementBag bag = EasyMock.mock(ElementBag.class);
        bag.putElement(ElementColor.RED);
        bag.putElement(ElementColor.YELLOW);
        bag.putElement(ElementColor.RED);
        bag.putElement(ElementColor.BLUE);
        Player player = EasyMock.mock(Player.class);
        player.unhatchedCards = new ArrayList<>();
        List<Player> players = Arrays.asList(player);
        EasyMock.expect(player.hasCard(card)).andReturn(false);
        EasyMock.expect(player.hasCard(card2)).andReturn(false);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        EasyMock.replay(hatchingGround, bag, player, eggHatchingLogic);

        Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null, eggHatchingLogic);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(hatchingGround, bag, player, eggHatchingLogic);
    }

}
