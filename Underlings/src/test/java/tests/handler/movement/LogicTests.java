package tests.handler.movement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.field.Field;
import underlings.field.FieldSpaceFactory;
import underlings.game.HatchingGround;
import underlings.gui.DrawChoice;
import underlings.gui.GUI;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;

public class LogicTests {

	private HatchingGround hatchingGround;
	private GUI gui;
	private Field field;
	private HandlerMovementLogic logic;
	
	@Before
	public void init() {
		this.hatchingGround = EasyMock.mock(HatchingGround.class);
		this.gui = EasyMock.mock(GUI.class);
		this.field = new Field(new FieldSpaceFactory());
		this.logic = new HandlerMovementLogic(this.hatchingGround, this.gui, this.field);
	}
	
	@Test
	public void testCard() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		Card card = new Card();
		
		EasyMock.expect(this.hatchingGround.getUnclaimedEggs()).andReturn(Collections.emptyList());
		EasyMock.expect(this.gui.getCard(Collections.emptyList())).andReturn(card);
		EasyMock.replay(this.hatchingGround, this.gui);
		
		this.logic.move(handler, HandlerChoice.CARD);
		
		EasyMock.verify(this.hatchingGround, this.gui);
		assertEquals(HandlerState.CARD, handler.getState());
		assertEquals(handler, card.handler);
		
	}
	
	@Test
	public void testFieldWhitespace() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		
		EasyMock.replay(this.hatchingGround, this.gui);
		
		this.logic.move(handler, HandlerChoice.FIELD_WHITESPACE);
		
		EasyMock.verify(this.hatchingGround, this.gui);
		assertEquals(HandlerState.FIELD_WHITESPACE, handler.getState());
		assertTrue(handler.elementGiver.drawChoices.contains(DrawChoice.WHITE));
	}
	
	@Test
	public void testBreakRoom() {
		Handler handler = new Handler(HandlerState.CARD);
		
		EasyMock.replay(this.hatchingGround, this.gui);
		
		this.logic.move(handler, HandlerChoice.BREAK_ROOM);
		
		EasyMock.verify(this.hatchingGround, this.gui);
		assertEquals(HandlerState.BREAK_ROOM, handler.getState());
	}
	
	@Test
	public void testStay() {
		Handler handler = new Handler(HandlerState.READY_ROOM);
		
		EasyMock.replay(this.hatchingGround, this.gui);
		
		this.logic.move(handler, HandlerChoice.STAY);
		
		EasyMock.verify(this.hatchingGround, this.gui);
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}
	
	@Test
	public void testReadyRoom() {
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		
		EasyMock.replay(this.hatchingGround, this.gui);
		
		this.logic.move(handler, HandlerChoice.READY_ROOM);
		
		EasyMock.verify(this.hatchingGround, this.gui);
		assertEquals(HandlerState.READY_ROOM, handler.getState());
	}
	
}
