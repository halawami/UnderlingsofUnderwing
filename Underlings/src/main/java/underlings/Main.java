package underlings;
import org.easymock.EasyMock;

import underlings.game.CardFactory;
import underlings.game.Deck;
import underlings.game.Game;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.gui.LameGUI
import underlings.player.PlayerFactory;

public class Main {

	public static void main(String[] args ) {
		
		GUI gui = new LameGUI();
		CardFactory cardFactory = new CardFactory();
		Deck deck = new Deck(cardFactory.getCards());
		HatchingGround hatchingGround = new HatchingGround(deck);
		PlayerFactory playerFactory = new PlayerFactory();
		
		Game game = new Game(gui, hatchingGround, playerFactory);
		
		game.start();
		
	}
	
}
