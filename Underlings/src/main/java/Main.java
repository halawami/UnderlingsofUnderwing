import org.easymock.EasyMock;

import GUI.GUI;
import Game.CardFactory;
import Game.Deck;
import Game.Game;
import Game.HatchingGround;

public class Main {

	public static void main(String[] args ) {
		
		GUI gui = EasyMock.mock(GUI.class);
		CardFactory cardFactory = new CardFactory();
		Deck deck = new Deck(cardFactory.getCards());
		HatchingGround hatchingGround = new HatchingGround(deck);
		Game game = new Game(gui, hatchingGround);
		
		game.start();
		
	}
	
}
