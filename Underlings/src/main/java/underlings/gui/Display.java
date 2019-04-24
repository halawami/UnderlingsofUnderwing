package underlings.gui;

import java.util.List;

import underlings.card.Card;
import underlings.handler.Handler;
import underlings.player.Player;

public interface Display {
	
	void displayCard(int row, int col, Card card);

	void displayPlayer(int playerNumber, Player player);

	void displayHandlers(int playerNumber, List<Handler> handler);

	void displayBackground();

	void update();
}
