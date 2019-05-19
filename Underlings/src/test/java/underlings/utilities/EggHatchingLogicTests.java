package underlings.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.ArrayList;
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
import underlings.element.utilities.ElementSpaceLogic;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.hatchingground.Deck;
import underlings.hatchingground.HatchingGround;
import underlings.player.FakePlayer;
import underlings.player.Player;

public class EggHatchingLogicTests {

    private Card card;
    private Effect wildEffect;
    private Effect domesticEffect;
    private ElementBag elementBag;
    private HatchingGround hatchingGround;
    private Player player;
    private Gui gui;
    private Runnable displayMethod;
    private EggHatchingLogic eggHatchingLogic;
    private Deck deck;
    private List<Player> players;
    private HandlerMovementLogic handlerMovementLogic;

    @Before
    public void init() throws IOException {
        this.card = new Card();
        this.card.elementSpaces = new ElementSpace[1];
        this.card.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);
        this.card.elementSpaces[0].elements = Arrays.asList(new Element(ElementColor.PURPLE));
        this.card.wildEffects = new Effect[1];
        this.wildEffect = EasyMock.mock(Effect.class);
        this.card.wildEffects[0] = this.wildEffect;
        this.elementBag = new ElementBag(new ElementFactory(), new Random());
        this.hatchingGround = EasyMock.mock(HatchingGround.class);
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        FakePlayer.initPlayer(recipes);
        this.player = FakePlayer.getInstance();
        this.gui = EasyMock.mock(Gui.class);
        this.card.domesticEffects = new Effect[1];
        this.domesticEffect = EasyMock.mock(Effect.class);
        this.card.domesticEffects[0] = this.domesticEffect;
        this.displayMethod = EasyMock.mock(Runnable.class);
        this.deck = EasyMock.mock(Deck.class);
        this.players = Arrays.asList(this.player);
        this.handlerMovementLogic = EasyMock.mock(HandlerMovementLogic.class);
        this.eggHatchingLogic = new EggHatchingLogic(this.gui, this.elementBag, this.hatchingGround, this.displayMethod,
                this.players, this.deck, this.handlerMovementLogic);
    }

    private void expectToApply(Effect effect) {
        EasyMock.expect(effect.on(this.card)).andReturn(effect);
        EasyMock.expect(effect.on(this.elementBag)).andReturn(effect);
        EasyMock.expect(effect.on(this.hatchingGround)).andReturn(effect);
        EasyMock.expect(effect.on(this.player)).andReturn(effect);
        EasyMock.expect(effect.on(this.gui)).andReturn(effect);
        EasyMock.expect(effect.on(this.eggHatchingLogic)).andReturn(effect);
        EasyMock.expect(effect.on(this.deck)).andReturn(effect);
        EasyMock.expect(effect.on(this.players)).andReturn(effect);
        EasyMock.expect(effect.on(this.handlerMovementLogic)).andReturn(effect);
        effect.apply();
        this.gui.notifyAction(-1, effect + " has been applied");
        this.displayMethod.run();
    }

    @Test
    public void testOneWildEffect() {
        this.expectToApply(this.wildEffect);

        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(new ArrayList<>());

        EasyMock.replay(this.wildEffect, this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);

        this.eggHatchingLogic.hatchEgg(this.card, this.player);
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.RED));
        assertEquals(WildHandler.getInstance(), this.card.handler);
        assertEquals(HandlerState.CARD, this.card.handler.state);
        EasyMock.verify(this.wildEffect, this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);
    }

    @Test
    public void testOneWildEffectReturnsElementsToBag() {
        this.expectToApply(this.wildEffect);

        this.card.elementSpaces[0].elements = Arrays.asList(this.elementBag.drawElementFromList(ElementColor.BLUE),
                this.elementBag.drawElementFromList(ElementColor.RED));
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(new ArrayList<>());

        EasyMock.replay(this.wildEffect, this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);

        assertEquals(19, this.elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(19, this.elementBag.getNumberRemaining(ElementColor.RED));
        this.eggHatchingLogic.hatchEgg(this.card, this.player);
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.RED));
        assertEquals(WildHandler.getInstance(), this.card.handler);
        EasyMock.verify(this.wildEffect, this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);
    }

    @Test
    public void testTwoWildEffects() {
        this.card.wildEffects = new Effect[2];
        this.card.wildEffects[0] = this.wildEffect;
        this.card.wildEffects[1] = this.wildEffect;
        this.expectToApply(this.wildEffect);
        this.expectToApply(this.wildEffect);

        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(new ArrayList<>());

        EasyMock.replay(this.wildEffect, this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);

        this.eggHatchingLogic.hatchEgg(this.card, this.player);
        assertEquals(WildHandler.getInstance(), this.card.handler);

        EasyMock.verify(this.wildEffect, this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);
    }

    @Test
    public void testOneDomesticEffect() {
        Handler handler = new Handler(HandlerState.CARD);
        this.card.handler = handler;
        this.expectToApply(this.domesticEffect);

        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(new ArrayList<>());

        EasyMock.replay(this.domesticEffect, this.hatchingGround, this.gui, this.displayMethod,
                this.handlerMovementLogic);

        this.eggHatchingLogic.hatchEgg(this.card, this.player);
        assertEquals(handler, this.card.handler);
        assertTrue(this.player.hatchedCards.contains(this.card));
        assertEquals(HandlerState.READY_ROOM, handler.getState());
        EasyMock.verify(this.domesticEffect, this.hatchingGround, this.gui, this.displayMethod,
                this.handlerMovementLogic);
    }

    @Test
    public void testEffectsRecursive() {
        Handler handler = new Handler(HandlerState.CARD);
        this.card.handler = handler;
        this.expectToApply(this.domesticEffect);

        Card card1 = new Card();
        Card card2 = new Card();
        card2.wildEffects = new Effect[0];
        card2.elementSpaces = new ElementSpace[0];

        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(Arrays.asList(card1, card2));
        this.hatchingGround.logic = EasyMock.mock(ElementSpaceLogic.class);
        EasyMock.expect(this.hatchingGround.logic.isComplete(card1)).andReturn(false);
        EasyMock.expect(this.hatchingGround.logic.isComplete(card2)).andReturn(true);
        EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(Arrays.asList());

        EasyMock.replay(this.wildEffect, this.domesticEffect, this.hatchingGround, this.gui, this.displayMethod,
                this.hatchingGround.logic, this.handlerMovementLogic);

        this.eggHatchingLogic.hatchEgg(this.card, this.player);
        assertTrue(this.player.hatchedCards.contains(this.card));
        EasyMock.verify(this.wildEffect, this.domesticEffect, this.hatchingGround, this.gui, this.displayMethod,
                this.hatchingGround.logic, this.handlerMovementLogic);

        assertEquals(WildHandler.getInstance(), card2.handler);
    }

    @Test
    public void testReturnNoElements() {
        this.card.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);

        EasyMock.replay(this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);

        this.eggHatchingLogic.returnElementsToBag(this.card);

        EasyMock.verify(this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);
    }

    @Test
    public void testReturnPurpleComboElements() {
        this.card.elementSpaces[0].elements = Arrays.asList(this.elementBag.drawElementFromList(ElementColor.BLUE),
                this.elementBag.drawElementFromList(ElementColor.RED));

        EasyMock.replay(this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);

        assertEquals(19, this.elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(19, this.elementBag.getNumberRemaining(ElementColor.RED));
        this.eggHatchingLogic.returnElementsToBag(this.card);
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.RED));

        EasyMock.verify(this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);
    }

    @Test
    public void testReturnOrangeComboElements() {
        this.card.elementSpaces[0] = new ElementSpace(ElementColor.ORANGE);
        this.card.elementSpaces[0].elements = Arrays.asList(this.elementBag.drawElementFromList(ElementColor.RED),
                this.elementBag.drawElementFromList(ElementColor.YELLOW));

        EasyMock.replay(this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);

        assertEquals(19, this.elementBag.getNumberRemaining(ElementColor.YELLOW));
        assertEquals(19, this.elementBag.getNumberRemaining(ElementColor.RED));
        this.eggHatchingLogic.returnElementsToBag(this.card);
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.YELLOW));
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.RED));

        EasyMock.verify(this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);
    }

    @Test
    public void testMultiReturnComboElements() {
        this.card.elementSpaces[0] = new ElementSpace(ElementColor.ORANGE);
        this.card.elementSpaces[0].elements = Arrays.asList(this.elementBag.drawElementFromList(ElementColor.BLUE),
                this.elementBag.drawElementFromList(ElementColor.RED));
        Card card2 = new Card();
        card2.elementSpaces = new ElementSpace[1];
        card2.elementSpaces[0] = new ElementSpace(ElementColor.PURPLE);
        card2.elementSpaces[0].elements = Arrays.asList(this.elementBag.drawElementFromList(ElementColor.BLUE),
                this.elementBag.drawElementFromList(ElementColor.RED));

        EasyMock.replay(this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);

        assertEquals(18, this.elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(18, this.elementBag.getNumberRemaining(ElementColor.RED));
        this.eggHatchingLogic.returnElementsToBag(this.card);
        this.eggHatchingLogic.returnElementsToBag(card2);
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
        assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.RED));

        EasyMock.verify(this.hatchingGround, this.gui, this.displayMethod, this.handlerMovementLogic);
    }

}
