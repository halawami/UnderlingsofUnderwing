package GUI;

import Game.Card;
import Game.Handler;
import Player.Player;

public interface GUI {

	public int promptPlayerCount();

	public void displayCard(int row, int col, Card card);

	public void displayPlayer(int playerNumber, Player player);

	public void displayHandler(int playerNumber, Handler handler);

	public void displayBackground();

}
