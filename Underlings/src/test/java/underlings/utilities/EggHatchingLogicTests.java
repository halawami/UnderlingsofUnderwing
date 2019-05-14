package underlings.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementFactory;
import underlings.element.ElementSpace;
import underlings.game.Deck;
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
    private Runnable displayMethod;
    private EggHatchingLogic eggHatchingLogic;
    private Deck deck;
    private List<Player> players;

    @Before
    public void init() throws IOException {
        this.card = new Card();
        this.card.elementSpaces = new ElementSpace[1];
        this.card.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);
        this.card.elementSpaces[0].elements = Arrays.asList(new Element(ElementColor.PURPLE));
        this.card.wildEffects = new Effect[1];
        this.effect = EasyMock.mock(Effect.class);
        this.card.wildEffects[0] = effect;
        this.elementBag = new ElementBag(new ElementFactory(), new Random());
        this.hatchingGround = EasyMock.mock(HatchingGround.class);
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        FakePlayer.initPlayer(recipes);
        this.player = FakePlayer.getInstance();
        this.gui = EasyMock.mock(Gui.class);
        this.card.domesticEffects = new Effect[1];
        this.card.domesticEffects[0] = effect;
        this.displayMethod = EasyMock.mock(Runnable.class);
        this.deck = EasyMock.mock(Deck.class);
        this.players = Arrays.asList(this.player);
        this.eggHatchingLogic =
                new EggHatchingLogic(gui, elementBag, hatchingGround, displayMethod, this.players, this.deck);
    }

    @Test
    public void testOneWildEffect() {
        EasyMock.expect(effect.on(card)).andReturn(effect);
        EasyMock.expect(effect.on(elementBag)).andReturn(effect);
        EasyMock.expect(effect.on(hatchingGround)).andReturn(effect);
        EasyMock.expect(effect.on(player)).andReturn(effect);
        EasyMock.expect(effect.on(gui)).andReturn(effect);
        EasyMock.expect(effect.on(eggHatchingLogic)).andReturn(effect);
        EasyMock.expect(effect.on(deck)).andReturn(effect);
        EasyMock.expect(effect.on(players)).andReturn(effect);
        effect.apply();
        this.elementBag.putElement(ElementColor.PURPLE);
        gui.notifyAction(-1, effect.toString() + " has been applied");
        displayMethod.run();

        EasyMock.replay(effect, hatchingGround, gui, displayMethod);

        eggHatchingLogic.hatchEgg(card, true, player);
        assertEquals(WildHandler.getInstance(), card.handler);
        EasyMock.verify(effect, hatchingGround, gui, displayMethod);
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
        EasyMock.expect(effect.on(eggHatchingLogic)).andReturn(effect).times(2);
        EasyMock.expect(effect.on(deck)).andReturn(effect).times(2);
        EasyMock.expect(effect.on(players)).andReturn(effect).times(2);
        effect.apply();
        EasyMock.expectLastCall().times(2);
        this.elementBag.putElement(ElementColor.PURPLE);

        gui.notifyAction(-1, effect + " has been applied");
        displayMethod.run();
        gui.notifyAction(-1, effect + " has been applied");
        displayMethod.run();

        EasyMock.replay(effect, hatchingGround, gui, displayMethod);

        eggHatchingLogic.hatchEgg(card, true, player);
        assertEquals(WildHandler.getInstance(), card.handler);

        EasyMock.verify(effect, hatchingGround, gui, displayMethod);
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
        EasyMock.expect(effect.on(eggHatchingLogic)).andReturn(effect);
        EasyMock.expect(effect.on(deck)).andReturn(effect);
        EasyMock.expect(effect.on(players)).andReturn(effect);
        effect.apply();
        gui.notifyAction(player.getPlayerId(), effect.toString() + " has been applied");
        displayMethod.run();

        EasyMock.replay(effect, hatchingGround, gui, displayMethod);

        eggHatchingLogic.hatchEgg(card, false, player);
        assertEquals(handler, card.handler);
        assertTrue(player.hatchedCards.contains(card));
        assertEquals(HandlerState.READY_ROOM, handler.getState());
        EasyMock.verify(effect, hatchingGround, gui, displayMethod);
    }

    @Test
    public void testOneDomesticEffectNoHandler() {
        EasyMock.expect(effect.on(card)).andReturn(effect);
        EasyMock.expect(effect.on(elementBag)).andReturn(effect);
        EasyMock.expect(effect.on(hatchingGround)).andReturn(effect);
        EasyMock.expect(effect.on(player)).andReturn(effect);
        EasyMock.expect(effect.on(gui)).andReturn(effect);
        EasyMock.expect(effect.on(eggHatchingLogic)).andReturn(effect);
        EasyMock.expect(effect.on(deck)).andReturn(effect);
        EasyMock.expect(effect.on(players)).andReturn(effect);
        effect.apply();
        gui.notifyAction(player.getPlayerId(), effect.toString() + " has been applied");
        displayMethod.run();

        EasyMock.replay(effect, hatchingGround, gui, displayMethod);

        eggHatchingLogic.hatchEgg(card, false, player);
        assertTrue(player.hatchedCards.contains(card));
        EasyMock.verify(effect, hatchingGround, gui, displayMethod);
    }

    @Test
    public void testReturnNoElements() {
        card.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);

        EasyMock.replay(hatchingGround, gui, displayMethod);

        eggHatchingLogic.returnElementsToBag(card);

        EasyMock.verify(hatchingGround, gui, displayMethod);
    }

    @Test
    public void testReturnPurpleComboElements() {
        card.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);
        card.elementSpaces[0].elements = Arrays.asList(elementBag.drawElementFromList(ElementColor.BLUE),
                elementBag.drawElementFromList(ElementColor.RED));

        EasyMock.replay(hatchingGround, gui, displayMethod);

        assertEquals(19, elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(19, elementBag.getNumberRemaining(ElementColor.RED));
        eggHatchingLogic.returnElementsToBag(card);
        assertEquals(20, elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(20, elementBag.getNumberRemaining(ElementColor.RED));

        EasyMock.verify(hatchingGround, gui, displayMethod);
    }

    @Test
    public void testReturnOrangeComboElements() {
        card.elementSpaces[0] = new ElementSpace(ElementColor.ORANGE);
        card.elementSpaces[0].elements = Arrays.asList(elementBag.drawElementFromList(ElementColor.RED),
                elementBag.drawElementFromList(ElementColor.YELLOW));

        EasyMock.replay(hatchingGround, gui, displayMethod);

        assertEquals(19, elementBag.getNumberRemaining(ElementColor.YELLOW));
        assertEquals(19, elementBag.getNumberRemaining(ElementColor.RED));
        eggHatchingLogic.returnElementsToBag(card);
        assertEquals(20, elementBag.getNumberRemaining(ElementColor.YELLOW));
        assertEquals(20, elementBag.getNumberRemaining(ElementColor.RED));

        EasyMock.verify(hatchingGround, gui, displayMethod);
    }

    @Test
    public void testMultiReturnComboElements() {
        card.elementSpaces[0] = new ElementSpace(ElementColor.ORANGE);
        card.elementSpaces[0].elements = Arrays.asList(elementBag.drawElementFromList(ElementColor.BLUE),
                elementBag.drawElementFromList(ElementColor.RED));
        Card card2 = new Card();
        card2.elementSpaces = new ElementSpace[1];
        card2.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);
        card2.elementSpaces[0].elements = Arrays.asList(elementBag.drawElementFromList(ElementColor.BLUE),
                elementBag.drawElementFromList(ElementColor.RED));

        EasyMock.replay(hatchingGround, gui, displayMethod);

        assertEquals(18, elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(18, elementBag.getNumberRemaining(ElementColor.RED));
        eggHatchingLogic.returnElementsToBag(card);
        eggHatchingLogic.returnElementsToBag(card2);
        assertEquals(20, elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(20, elementBag.getNumberRemaining(ElementColor.RED));

        EasyMock.verify(hatchingGround, gui, displayMethod);
    }

}
