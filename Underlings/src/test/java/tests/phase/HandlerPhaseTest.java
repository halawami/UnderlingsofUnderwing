package tests.phase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.element.ElementBag;
import underlings.game.Card;
import underlings.game.Handler;
import underlings.game.HandlerState;
import underlings.game.HatchingGround;
import underlings.gui.Display;
import underlings.gui.GUI;
import underlings.gui.PromptHandler;
import underlings.phase.HandlerPhase;
import underlings.phase.Phase;
import underlings.player.Player;

public class HandlerPhaseTest {

	@Test
	public void testExecuteOnePlayerTwoHandlersReadyRoomToCard() {
		Phase handlerPhase = new HandlerPhase();

		Player player = EasyMock.createMock(Player.class);
		PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
		Display display = EasyMock.mock(Display.class);
		GUI gui = new GUI(promptHandler, display);
		ElementBag elementBag = EasyMock.createMock(ElementBag.class);
		HatchingGround hatchingGround = EasyMock.createMock(HatchingGround.class);
		Handler handler = EasyMock.mock(Handler.class);
		Card card = EasyMock.mock(Card.class);
		Runnable runnable = EasyMock.mock(Runnable.class);

		List<Handler> handlerList = new LinkedList<>();
		handlerList.add(handler);
		handlerList.add(handler);
		
		EasyMock.expect(player.getHandlers()).andReturn(handlerList);
		
		List<HandlerState> possibleStates = new LinkedList<>();
		possibleStates.addAll(Arrays.asList(HandlerState.CARD, 
				HandlerState.FIELD,
				HandlerState.FIELD_WHITESPACE, 
				HandlerState.READY_ROOM));
		
		EasyMock.expect(promptHandler.promptHandler(handlerList)).andReturn(handler);
		
		EasyMock.expect(handler.getPossibleStates()).andReturn(possibleStates);
		EasyMock.expect(promptHandler.promptHandlerState(possibleStates)).andReturn(HandlerState.CARD);
		
		List<Card> unclaimedEggs = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
				unclaimedEggs.add(card); 
		}
		
		EasyMock.expect(hatchingGround.getUnclaimedEggs()).andReturn(unclaimedEggs);
		
		EasyMock.expect(promptHandler.promptCardSelection(unclaimedEggs)).andReturn(card);
		
		handler.moveToState(HandlerState.CARD);
		
		handlerList = new ArrayList<>(handlerList);
		handlerList.remove(handler);
		
		EasyMock.expect(promptHandler.promptHandler(handlerList)).andReturn(handler);
		
		EasyMock.expect(handler.getPossibleStates()).andReturn(possibleStates);
		EasyMock.expect(promptHandler.promptHandlerState(possibleStates)).andReturn(HandlerState.CARD);
		
		EasyMock.expect(hatchingGround.getUnclaimedEggs()).andReturn(unclaimedEggs);
		
		EasyMock.expect(promptHandler.promptCardSelection(unclaimedEggs)).andReturn(card);
		
		handler.moveToState(HandlerState.CARD);
		
		List<Player> players = new ArrayList<>();
		players.add(player);
		
		runnable.run();
		EasyMock.expectLastCall().times(2);
		
		EasyMock.replay(player, promptHandler, display, elementBag, hatchingGround, handler, card, runnable);
		
		handlerPhase.execute(players, gui, elementBag, hatchingGround, runnable);
		assertEquals(handler, card.handler);
		EasyMock.verify(player, promptHandler, display, elementBag, hatchingGround, handler, card, runnable);
		
	}

}
