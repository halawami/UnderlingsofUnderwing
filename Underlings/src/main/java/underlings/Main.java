package underlings;
import org.easymock.EasyMock;

import underlings.game.CardFactory;
import underlings.game.Deck;
import underlings.game.Game;
import underlings.game.HandlerFactory;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.player.PlayerFactory;

public class Main {

	public static void main(String[] args ) {
		
		GUI gui = EasyMock.mock(GUI.class);
		CardFactory cardFactory = new CardFactory();
		Deck deck = new Deck(cardFactory.getCards());
		HatchingGround hatchingGround = new HatchingGround(deck);
		HandlerFactory handlerFactory = new HandlerFactory();
		PlayerFactory playerFactory = new PlayerFactory(handlerFactory);
		
		Game game = new Game(gui, hatchingGround, playerFactory);
		
		game.start();
		
	}
	
}
