package underlings.phase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import org.easymock.EasyMock;
import org.junit.Ignore;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Display;
import underlings.gui.Gui;
import underlings.gui.PromptHandler;
import underlings.handler.Handler;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class PlacementPhaseTests {

    @SuppressWarnings("unchecked")
    @Ignore
    public void basicTest() {
        // create players and define actions
        Player player = EasyMock.createMock(Player.class);
        final List<Player> players = Arrays.asList(player);

        EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
        EasyMock.expect(player.getPlayerId()).andReturn(1).anyTimes();

        ElementSpaceLogic logic = EasyMock.mock(ElementSpaceLogic.class);
        player.elementSpaceLogic = logic;

        List<Element> playerElements = new ArrayList<Element>();
        Element blue1 = new Element(ElementColor.BLUE);
        playerElements.add(blue1);
        Element blue2 = new Element(ElementColor.BLUE);
        playerElements.add(blue2);
        Element white = new Element(ElementColor.WHITE);
        playerElements.add(white);
        EasyMock.expect(player.getElements()).andReturn(playerElements).anyTimes();

        // create hatchingGround and define actions
        Card card = new Card();
        card.wildEffects = new Effect[0];
        card.handler = EasyMock.mock(Handler.class);
        Stack<Card> cardStack = new Stack<>();
        cardStack.push(card);
        Deck deck = new Deck(cardStack);
        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();
        ElementSpace redSpace = EasyMock.mock(ElementSpace.class);
        ElementSpace blueSpace = EasyMock.mock(ElementSpace.class);
        ElementSpace greenSpace = EasyMock.mock(ElementSpace.class);
        ElementSpace whiteSpace = EasyMock.mock(ElementSpace.class);
        ElementSpace[] spaces = {redSpace, blueSpace, greenSpace, whiteSpace};
        card.elementSpaces = spaces;

        // create other fields
        final PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
        final Display display = EasyMock.mock(Display.class);
        final Runnable runnable = EasyMock.mock(Runnable.class);
        final EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);

        // define expected flow of activity
        EasyMock.expect(logic.getPlayableSpaces(EasyMock.anyObject(Card.class), EasyMock.anyObject(List.class)))
                .andReturn(Arrays.asList(blueSpace, greenSpace, whiteSpace)).anyTimes();
        EasyMock.expect(promptHandler.promptChoice("Pick a card to place an element on.", Arrays.asList(card), 1))
                .andReturn(card);
        EasyMock.expect(promptHandler.promptChoice("Pick an element space to place an element on.",
                Arrays.asList(blueSpace, greenSpace, whiteSpace), 1)).andReturn(blueSpace);
        EasyMock.expect(logic.getValidAdditions(blueSpace)).andReturn(Arrays.asList(ElementColor.BLUE));
        EasyMock.expect(promptHandler.promptChoice("Pick an element to place.", Arrays.asList(blue1, blue2), 1))
                .andReturn(blue1);
        blueSpace.addElements(blue1);
        player.removeElement(blue1);
        runnable.run();
        EasyMock.expectLastCall().anyTimes();
        EasyMock.expect(logic.getValidAdditions(blueSpace)).andReturn(Arrays.asList(ElementColor.BLUE));
        EasyMock.expect(promptHandler.promptDecision("Would you like to place another element?", 1)).andReturn(false);
        EasyMock.expect(logic.isComplete(card)).andReturn(true).anyTimes();

        // assert expected actions occurred
        EasyMock.replay(player, promptHandler, display, runnable);
        EasyMock.replay(logic, redSpace, blueSpace, greenSpace, whiteSpace, eggHatchingLogic);
        Gui gui = new Gui(promptHandler, display);
        Phase phase = new PlacementPhase(players, gui, hatchingGround, runnable, eggHatchingLogic, null);
        phase.execute(1);
        EasyMock.verify(player, promptHandler, display, runnable);
        EasyMock.verify(logic, redSpace, blueSpace, greenSpace, whiteSpace, eggHatchingLogic);
    }

    @SuppressWarnings("unchecked")
    @Ignore
    public void testOneWildEffect() {
        Player player = EasyMock.createMock(Player.class);
        final List<Player> players = Arrays.asList(player);
        ElementSpaceLogic logic = EasyMock.mock(ElementSpaceLogic.class);
        player.elementSpaceLogic = logic;
        EasyMock.expect(player.getHandlerCount()).andReturn(1).anyTimes();
        EasyMock.expect(player.getPlayerId()).andReturn(1).anyTimes();

        List<Element> playerElements = new ArrayList<Element>();
        Element blue1 = new Element(ElementColor.BLUE);
        playerElements.add(blue1);
        Element blue2 = new Element(ElementColor.BLUE);
        playerElements.add(blue2);
        Element white = new Element(ElementColor.WHITE);
        playerElements.add(white);
        EasyMock.expect(player.getElements()).andReturn(playerElements).anyTimes();

        // create hatchingGround and define actions
        Card card = new Card();
        card.handler = null;
        card.wildEffects = new Effect[1];
        card.wildEffects[0] = EasyMock.mock(Effect.class);
        Stack<Card> cardStack = new Stack<>();
        cardStack.push(card);
        Deck deck = new Deck(cardStack);
        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(1, 1);
        hatchingGround.populate();
        ElementSpace redSpace = EasyMock.mock(ElementSpace.class);
        ElementSpace blueSpace = EasyMock.mock(ElementSpace.class);
        ElementSpace greenSpace = EasyMock.mock(ElementSpace.class);
        ElementSpace whiteSpace = EasyMock.mock(ElementSpace.class);
        ElementSpace[] spaces = {redSpace, blueSpace, greenSpace, whiteSpace};
        card.elementSpaces = spaces;

        // create other fields
        PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
        Display display = EasyMock.mock(Display.class);
        final Gui gui = EasyMock.mock(Gui.class);
        // gui.display = display;
        gui.promptHandler = promptHandler;
        final ElementBag elementBag = EasyMock.createMock(ElementBag.class);
        final Runnable runnable = EasyMock.mock(Runnable.class);
        final EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);

        // define expected flow of activity
        eggHatchingLogic.hatchEgg(card, true, FakePlayer.getInstance());
        EasyMock.expect(logic.getPlayableSpaces(EasyMock.anyObject(Card.class), EasyMock.anyObject(List.class)))
                .andReturn(Arrays.asList(blueSpace, greenSpace, whiteSpace)).anyTimes();
        EasyMock.expect(promptHandler.promptChoice("Pick a card to place an element on.", Arrays.asList(card), 1))
                .andReturn(card);
        EasyMock.expect(promptHandler.promptChoice("Pick an element space to place an element on.",
                Arrays.asList(blueSpace, greenSpace, whiteSpace), 1)).andReturn(blueSpace);
        EasyMock.expect(logic.getValidAdditions(blueSpace)).andReturn(Arrays.asList(ElementColor.BLUE));
        EasyMock.expect(promptHandler.promptChoice("Pick an element to place", Arrays.asList(blue1, blue2), 1))
                .andReturn(blue1);
        blueSpace.addElements(blue1);
        player.removeElement(blue1);
        runnable.run();
        EasyMock.expectLastCall().anyTimes();
        EasyMock.expect(promptHandler.promptDecision("Would you like to place another element?", 1)).andReturn(false);
        EasyMock.expectLastCall().anyTimes();
        EasyMock.expect(logic.getValidAdditions(blueSpace)).andReturn(Arrays.asList(ElementColor.BLUE));
        EasyMock.expect(gui.getMoreMovesDecision(2, 1)).andReturn(false);
        EasyMock.expect(logic.isComplete(card)).andReturn(true).anyTimes();

        // assert expected actions occurred
        EasyMock.replay(player, promptHandler, display, elementBag, runnable, gui, eggHatchingLogic);
        EasyMock.replay(logic, redSpace, blueSpace, greenSpace, whiteSpace, card.wildEffects[0]);
        Phase phase = new PlacementPhase(players, gui, hatchingGround, runnable, eggHatchingLogic, null);
        phase.execute(1);
        EasyMock.verify(player, promptHandler, display, elementBag, runnable, gui, eggHatchingLogic);
        EasyMock.verify(logic, redSpace, blueSpace, greenSpace, whiteSpace, card.wildEffects[0]);
    }

    @Test
    public void testMoreMovesNoChoices() {
        Display display = EasyMock.mock(Display.class);
        PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
        Gui gui = new Gui(promptHandler, display);

        EasyMock.replay(display, promptHandler);

        boolean result = gui.getMoreMovesDecision(0, 0);

        EasyMock.verify(display, promptHandler);
        assertFalse(result);
    }

    @Test
    public void testMoreMovesTrue() {
        Display display = EasyMock.mock(Display.class);
        PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
        Gui gui = new Gui(promptHandler, display);

        EasyMock.expect(promptHandler.promptDecision(EasyMock.anyString(), EasyMock.anyInt())).andReturn(true);

        EasyMock.replay(display, promptHandler);

        boolean result = gui.getMoreMovesDecision(1, 0);

        EasyMock.verify(display, promptHandler);
        assertTrue(result);
    }

}
