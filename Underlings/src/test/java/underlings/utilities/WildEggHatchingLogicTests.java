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

public class WildEggHatchingLogicTests {

    @Test
    public void testOneWildEffects() {
        Card card = new Card();
        card.wildEffects = new Effect[1];
        Effect effect = EasyMock.mock(Effect.class);
        card.wildEffects[0] = effect;
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        Player player = EasyMock.mock(Player.class);
        player.elementSpaceLogic = new ElementSpaceLogic();
        EasyMock.expect(card.wildEffects[0].on(elementBag)).andReturn(card.wildEffects[0]).anyTimes();
        EasyMock.expect(card.wildEffects[0].on(hatchingGround)).andReturn(card.wildEffects[0]).anyTimes();
        EasyMock.expect(card.wildEffects[0].on(player.elementSpaceLogic)).andReturn(card.wildEffects[0]).anyTimes();
        EasyMock.expect(card.wildEffects[0].on(player)).andReturn(card.wildEffects[0]).anyTimes();
        card.wildEffects[0].apply();
        Gui gui = EasyMock.mock(Gui.class);
        gui.notifyAction(-1, card.wildEffects[0].toString() + " has been applied");

        EasyMock.replay(effect, elementBag, hatchingGround, player, gui);
        WildEggHatchingLogic wildEggHatchingLogic = new WildEggHatchingLogic(player, gui, elementBag, hatchingGround);
        wildEggHatchingLogic.hatchWildEgg(card);

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
        card.wildEffects[0].apply();
        EasyMock.expectLastCall().times(2);
        Gui gui = EasyMock.mock(Gui.class);
        gui.notifyAction(-1, card.wildEffects[0].toString() + " has been applied");
        EasyMock.expectLastCall().times(2);

        EasyMock.replay(effect, elementBag, hatchingGround, player, gui);
        WildEggHatchingLogic wildEggHatchingLogic = new WildEggHatchingLogic(player, gui, elementBag, hatchingGround);
        wildEggHatchingLogic.hatchWildEgg(card);

        EasyMock.verify(effect, elementBag, hatchingGround, player, gui);
    }

}
