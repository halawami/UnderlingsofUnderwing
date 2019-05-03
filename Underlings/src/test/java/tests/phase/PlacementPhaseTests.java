package tests.phase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import org.easymock.EasyMock;
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
import underlings.phase.Phase;
import underlings.phase.PlacementPhase;
import underlings.player.Player;

public class PlacementPhaseTests {

    @SuppressWarnings("unchecked")
    @Test
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
        final ElementBag elementBag = EasyMock.createMock(ElementBag.class);
        final Runnable runnable = EasyMock.mock(Runnable.class);

        // define expected flow of activity
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
        EasyMock.expect(logic.getValidAdditions(blueSpace)).andReturn(Arrays.asList(ElementColor.BLUE));
        EasyMock.expect(promptHandler.promptDecision("Would you like to place another element?", 1)).andReturn(false);
        EasyMock.expect(logic.isComplete(card)).andReturn(true).anyTimes();

        // assert expected actions occurred
        EasyMock.replay(player, promptHandler, display, elementBag, runnable);
        EasyMock.replay(logic, redSpace, blueSpace, greenSpace, whiteSpace);
        Gui gui = new Gui(promptHandler, display);
        Phase phase = new PlacementPhase(players, gui, elementBag, hatchingGround, runnable, null);
        phase.execute(1);
        EasyMock.verify(player, promptHandler, display, elementBag, runnable);
        EasyMock.verify(logic, redSpace, blueSpace, greenSpace, whiteSpace);
    }

    @SuppressWarnings("unchecked")
    @Test
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
        gui.display = display;
        gui.promptHandler = promptHandler;
        final ElementBag elementBag = EasyMock.createMock(ElementBag.class);
        final Runnable runnable = EasyMock.mock(Runnable.class);

        // define expected flow of activity
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
        EasyMock.expect(logic.getValidAdditions(blueSpace)).andReturn(Arrays.asList(ElementColor.BLUE));
        EasyMock.expect(promptHandler.promptDecision("Would you like to place another element?", 1)).andReturn(false);
        EasyMock.expect(logic.isComplete(card)).andReturn(true).anyTimes();
        EasyMock.expect(card.wildEffects[0].on(elementBag)).andReturn(card.wildEffects[0]).anyTimes();
        EasyMock.expect(card.wildEffects[0].on(hatchingGround)).andReturn(card.wildEffects[0]).anyTimes();
        EasyMock.expect(card.wildEffects[0].on(logic)).andReturn(card.wildEffects[0]).anyTimes();
        EasyMock.expect(card.wildEffects[0].on(player)).andReturn(card.wildEffects[0]).anyTimes();
        card.wildEffects[0].apply();
        gui.notifyAction(-1, card.wildEffects[0].toString() + " has been applied");
        gui.promptHandler.displayMessage("All dragons hatched wild! You lose!", -1, JOptionPane.WARNING_MESSAGE);

        // assert expected actions occurred
        EasyMock.replay(player, promptHandler, display, elementBag, runnable, gui);
        EasyMock.replay(logic, redSpace, blueSpace, greenSpace, whiteSpace, card.wildEffects[0]);
        Phase phase = new PlacementPhase(players, gui, elementBag, hatchingGround, runnable, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(player, promptHandler, display, elementBag, runnable, gui);
        EasyMock.verify(logic, redSpace, blueSpace, greenSpace, whiteSpace, card.wildEffects[0]);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testMultipleWildEffect() {
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
        card.handler = null;
        card.wildEffects = new Effect[2];
        card.wildEffects[0] = EasyMock.mock(Effect.class);
        card.wildEffects[1] = EasyMock.mock(Effect.class);
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
        Gui gui = EasyMock.mock(Gui.class);
        gui.display = display;
        gui.promptHandler = promptHandler;
        final ElementBag elementBag = EasyMock.createMock(ElementBag.class);
        final Runnable runnable = EasyMock.mock(Runnable.class);

        // define expected flow of activity
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
        EasyMock.expect(logic.getValidAdditions(blueSpace)).andReturn(Arrays.asList(ElementColor.BLUE));
        EasyMock.expect(promptHandler.promptDecision("Would you like to place another element?", 1)).andReturn(false);
        EasyMock.expect(logic.isComplete(card)).andReturn(true).anyTimes();
        EasyMock.expect(card.wildEffects[0].on(elementBag)).andReturn(card.wildEffects[0]).anyTimes();
        EasyMock.expect(card.wildEffects[0].on(hatchingGround)).andReturn(card.wildEffects[0]).anyTimes();
        EasyMock.expect(card.wildEffects[0].on(logic)).andReturn(card.wildEffects[0]).anyTimes();
        EasyMock.expect(card.wildEffects[0].on(player)).andReturn(card.wildEffects[0]).anyTimes();
        card.wildEffects[0].apply();
        gui.notifyAction(-1, card.wildEffects[0].toString() + " has been applied");
        EasyMock.expect(card.wildEffects[1].on(elementBag)).andReturn(card.wildEffects[1]).anyTimes();
        EasyMock.expect(card.wildEffects[1].on(hatchingGround)).andReturn(card.wildEffects[1]).anyTimes();
        EasyMock.expect(card.wildEffects[1].on(logic)).andReturn(card.wildEffects[1]).anyTimes();
        EasyMock.expect(card.wildEffects[1].on(player)).andReturn(card.wildEffects[1]).anyTimes();
        card.wildEffects[1].apply();
        gui.notifyAction(-1, card.wildEffects[1].toString() + " has been applied");
        gui.promptHandler.displayMessage("All dragons hatched wild! You lose!", -1, JOptionPane.WARNING_MESSAGE);

        // assert expected actions occurred
        EasyMock.replay(player, promptHandler, display, elementBag, runnable, gui);
        EasyMock.replay(logic, redSpace, blueSpace, greenSpace, whiteSpace, card.wildEffects[0], card.wildEffects[1]);
        Phase phase = new PlacementPhase(players, gui, elementBag, hatchingGround, runnable, null);
        phase.setup();
        phase.turn(player);
        EasyMock.verify(player, promptHandler, display, elementBag, runnable, gui);
        EasyMock.verify(logic, redSpace, blueSpace, greenSpace, whiteSpace, card.wildEffects[0], card.wildEffects[1]);
    }

}
