package GUI;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import Game.Card;
import Game.CardFactory;
import Game.Game;

public class GUITests {

	private Game game;
	private CardFactory cardFactory;
	private Stack<Card> cards;
	private GUI gui;
	
	@Before
	public void init() {
		this.gui = EasyMock.mock(GUI.class);
		this.game = new Game(this.gui);
		
		this.cards = new Stack<Card>();
		for(int i = 0; i < 50; i++){
			this.cards.push(new Card());
		}
		
		this.cardFactory = EasyMock.mock(CardFactory.class);
		EasyMock.expect(this.cardFactory.getCards()).andStubReturn(this.cards);
		
		EasyMock.replay(this.cardFactory);
	}
	
	@Test
	public void testGetPlayerCountTwoPlayers() {

		// RECORD
		EasyMock.expect(this.gui.promptPlayerCount()).andReturn(2);
		
		// REPLAY
		EasyMock.replay(this.gui);
		this.game.start();
		
		// VERIFY
		EasyMock.verify(this.gui);
		assertEquals(2, this.game.getPlayerCount());
		
	}
	
}
