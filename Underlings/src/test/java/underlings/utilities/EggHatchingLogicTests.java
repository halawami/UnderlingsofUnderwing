package underlings.utilities;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;

public class EggHatchingLogicTests {

    @Test
    public void testOneWildEffect() {
        Card card = new Card();
        card.wildEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        card.wildEffects[0] = effect;
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        Player player = EasyMock.mock(Player.class);
        player.elementSpaceLogic = new ElementSpaceLogic();
        EasyMock.expect(card.wildEffects[0].on(elementBag)).andReturn(card.wildEffects[0]);
        EasyMock.expect(card.wildEffects[0].on(hatchingGround)).andReturn(card.wildEffects[0]);
        EasyMock.expect(card.wildEffects[0].on(player.elementSpaceLogic)).andReturn(card.wildEffects[0]);
        EasyMock.expect(card.wildEffects[0].on(player)).andReturn(card.wildEffects[0]);
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(card.wildEffects[0].on(gui)).andReturn(card.wildEffects[0]);
        card.wildEffects[0].apply();
        gui.notifyAction(-1, card.wildEffects[0].toString() + " has been applied");

        EasyMock.replay(effect, elementBag, hatchingGround, player, gui);
        EggHatchingLogic wildEggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround, player);
        wildEggHatchingLogic.hatchEgg(card);

        EasyMock.verify(effect, elementBag, hatchingGround, player, gui);
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
        Player player = EasyMock.mock(Player.class);
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

        EasyMock.replay(effect, elementBag, hatchingGround, player, gui);

        EggHatchingLogic wildEggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround, player);
        wildEggHatchingLogic.hatchEgg(card);

        EasyMock.verify(effect, elementBag, hatchingGround, player, gui);
    }

    @Test
    public void testOneDomesticEffect() {
        Card card = new Card();
        card.domesticEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        card.domesticEffects[0] = effect;
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        Player player = EasyMock.mock(Player.class);
        player.elementSpaceLogic = new ElementSpaceLogic();
        EasyMock.expect(card.domesticEffects[0].on(elementBag)).andReturn(card.domesticEffects[0]);
        EasyMock.expect(card.domesticEffects[0].on(hatchingGround)).andReturn(card.domesticEffects[0]);
        EasyMock.expect(card.domesticEffects[0].on(player.elementSpaceLogic)).andReturn(card.domesticEffects[0]);
        EasyMock.expect(card.domesticEffects[0].on(player)).andReturn(card.domesticEffects[0]);
        Gui gui = EasyMock.mock(Gui.class);
        EasyMock.expect(card.domesticEffects[0].on(gui)).andReturn(card.domesticEffects[0]);
        card.domesticEffects[0].apply();
        gui.notifyAction(-1, card.domesticEffects[0].toString() + " has been applied");

        EasyMock.replay(effect, elementBag, hatchingGround, player, gui);
        EggHatchingLogic wildEggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround, player);
        wildEggHatchingLogic.hatchEgg(card, false);

        EasyMock.verify(effect, elementBag, hatchingGround, player, gui);
    }

}
