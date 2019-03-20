import org.easymock.EasyMock;

import GUI.GUI;
import Game.CardFactory;
import Game.Deck;
import Game.Game;
import Game.HatchingGround;
import Player.PlayerFactory;

public class Main {

	public static void main(String[] args ) {
		
		GUI gui = EasyMock.mock(GUI.class);
		CardFactory cardFactory = new CardFactory();
		Deck deck = new Deck(cardFactory.getCards());
		HatchingGround hatchingGround = new HatchingGround(deck);
		PlayerFactory playerFactory = new PlayerFactory();
		
		Game game = new Game(gui, hatchingGround, playerFactory);
		
		game.start();
		
	}
	
}
