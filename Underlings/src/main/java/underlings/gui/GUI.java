package underlings.gui;

import underlings.game.Card;
import underlings.game.Handler;
import underlings.player.Player;

public interface GUI {

	public int promptPlayerCount();

	public void displayCard(int row, int col, Card card);

	public void displayPlayer(int playerNumber, Player player);

	public void displayHandler(int playerNumber, Handler handler);

	public void displayBackground();

	public void update();

}
