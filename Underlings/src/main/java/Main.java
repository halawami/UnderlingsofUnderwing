import org.easymock.EasyMock;

import GUI.GUI;
import Game.Game;

public class Main {

	public static void main(String[] args ) {
		
		GUI gui = EasyMock.mock(GUI.class);
		Game game = new Game(gui);
		game.start();
		
	}
	
}
