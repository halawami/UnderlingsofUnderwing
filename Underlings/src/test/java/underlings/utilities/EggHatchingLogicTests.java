package underlings.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.player.FakePlayer;
import underlings.player.Player;

public class EggHatchingLogicTests {

    private Card card;
    private Effect effect;
    private ElementBag elementBag;
    private HatchingGround hatchingGround;
    private Player player;
    private Gui gui;

    @Before
    public void init() {
        this.card = new Card();
        this.card.elementSpaces = new ElementSpace[1];
        this.card.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);
        this.card.elementSpaces[0].elements = Arrays.asList(new Element(ElementColor.PURPLE));
        this.card.wildEffects = new Effect[1];
        this.effect = EasyMock.mock(Effect.class);
        this.card.wildEffects[0] = effect;
        this.elementBag = EasyMock.mock(ElementBag.class);
        this.hatchingGround = EasyMock.mock(HatchingGround.class);
        this.player = FakePlayer.getInstance();
        this.gui = EasyMock.mock(Gui.class);
        this.card.domesticEffects = new Effect[1];
        this.card.domesticEffects[0] = effect;
    }

    @Test
    public void testOneWildEffect() {
        EasyMock.expect(effect.on(card)).andReturn(effect);
        EasyMock.expect(effect.on(elementBag)).andReturn(effect);
        EasyMock.expect(effect.on(hatchingGround)).andReturn(effect);
        EasyMock.expect(effect.on(player)).andReturn(effect);
        EasyMock.expect(effect.on(gui)).andReturn(effect);
        EggHatchingLogic wildEggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround);
        EasyMock.expect(effect.on(wildEggHatchingLogic)).andReturn(effect);
        effect.apply();
        this.elementBag.putElement(ElementColor.PURPLE);
        gui.notifyAction(-1, effect.toString() + " has been applied");

        EasyMock.replay(effect, elementBag, hatchingGround, gui);

        wildEggHatchingLogic.hatchEgg(card, true, player);
        assertEquals(WildHandler.getInstance(), card.handler);
        EasyMock.verify(effect, elementBag, hatchingGround, gui);
    }

    @Test
    public void testTwoWildEffects() {
        card.wildEffects = new Effect[2];
        card.wildEffects[0] = effect;
        card.wildEffects[1] = effect;
        EasyMock.expect(effect.on(card)).andReturn(effect).times(2);
        EasyMock.expect(effect.on(elementBag)).andReturn(effect).times(2);
        EasyMock.expect(effect.on(hatchingGround)).andReturn(effect).times(2);
        EasyMock.expect(effect.on(player)).andReturn(effect).times(2);
        EasyMock.expect(effect.on(gui)).andReturn(effect).times(2);
        EggHatchingLogic wildEggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround);
        EasyMock.expect(effect.on(wildEggHatchingLogic)).andReturn(effect).times(2);
        effect.apply();
        EasyMock.expectLastCall().times(2);
        this.elementBag.putElement(ElementColor.PURPLE);

        gui.notifyAction(-1, effect + " has been applied");
        EasyMock.expectLastCall().times(2);

        EasyMock.replay(effect, elementBag, hatchingGround, gui);

        wildEggHatchingLogic.hatchEgg(card, true, player);
        assertEquals(WildHandler.getInstance(), card.handler);

        EasyMock.verify(effect, elementBag, hatchingGround, gui);
    }

    @Test
    public void testOneDomesticEffect() {
        Handler handler = new Handler(HandlerState.CARD);
        card.handler = handler;
        EasyMock.expect(effect.on(card)).andReturn(effect);
        EasyMock.expect(effect.on(elementBag)).andReturn(effect);
        EasyMock.expect(effect.on(hatchingGround)).andReturn(effect);
        EasyMock.expect(effect.on(player)).andReturn(effect);
        EasyMock.expect(effect.on(gui)).andReturn(effect);
        EggHatchingLogic domesticEggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround);
        EasyMock.expect(effect.on(domesticEggHatchingLogic)).andReturn(effect);
        effect.apply();
        gui.notifyAction(player.getPlayerId(), effect.toString() + " has been applied");

        EasyMock.replay(effect, elementBag, hatchingGround, gui);

        domesticEggHatchingLogic.hatchEgg(card, false, player);
        assertEquals(handler, card.handler);
        assertTrue(player.hatchedCards.contains(card));
        assertEquals(HandlerState.READY_ROOM, handler.getState());
        EasyMock.verify(effect, elementBag, hatchingGround, gui);
    }

    @Test
    public void testReturnNoElements() {
        card.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);

        EasyMock.replay(hatchingGround, elementBag, gui);

        EggHatchingLogic eggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround);
        eggHatchingLogic.returnElementsToBag(card);

        EasyMock.verify(hatchingGround, elementBag, gui);
    }

    @Test
    public void testReturnPurpleComboElements() {
        card.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);
        card.elementSpaces[0].elements = Arrays.asList(new Element(ElementColor.RED), new Element(ElementColor.BLUE));
        elementBag.putElement(ElementColor.RED);
        elementBag.putElement(ElementColor.BLUE);
        EasyMock.replay(hatchingGround, elementBag, gui);

        EggHatchingLogic eggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround);
        eggHatchingLogic.returnElementsToBag(card);

        EasyMock.verify(hatchingGround, elementBag, gui);
    }

    @Test
    public void testReturnOrangeComboElements() {
        card.elementSpaces[0] = new ElementSpace(ElementColor.ORANGE);
        card.elementSpaces[0].elements = Arrays.asList(new Element(ElementColor.RED), new Element(ElementColor.YELLOW));
        elementBag.putElement(ElementColor.RED);
        elementBag.putElement(ElementColor.YELLOW);

        EasyMock.replay(hatchingGround, elementBag, gui);

        EggHatchingLogic eggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround);
        eggHatchingLogic.returnElementsToBag(card);

        EasyMock.verify(hatchingGround, elementBag, gui);
    }

    @Test
    public void testMultiReturnComboElements() {
        card.elementSpaces[0] = new ElementSpace(ElementColor.ORANGE);
        card.elementSpaces[0].elements = Arrays.asList(new Element(ElementColor.RED), new Element(ElementColor.YELLOW));
        Card card2 = new Card();
        card2.elementSpaces = new ElementSpace[1];
        card2.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);
        card2.elementSpaces[0].elements = Arrays.asList(new Element(ElementColor.RED), new Element(ElementColor.BLUE));
        elementBag.putElement(ElementColor.RED);
        elementBag.putElement(ElementColor.YELLOW);
        elementBag.putElement(ElementColor.RED);
        elementBag.putElement(ElementColor.BLUE);

        EasyMock.replay(hatchingGround, elementBag, gui);

        EggHatchingLogic eggHatchingLogic = new EggHatchingLogic(gui, elementBag, hatchingGround);
        eggHatchingLogic.returnElementsToBag(card);
        eggHatchingLogic.returnElementsToBag(card2);

        EasyMock.verify(hatchingGround, elementBag, gui);
    }

}
