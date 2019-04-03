package underlings.gui;

import java.util.List;

import underlings.game.Card;
import underlings.game.Handler;
import underlings.player.Player;

public interface Display {
	
	public void displayCard(int row, int col, Card card);

	public void displayPlayer(int playerNumber, Player player);

	public void displayHandlers(int playerNumber, List<Handler> handler);

	public void displayBackground();

	public void update();
}
