package GUI;

import Game.Card;

public interface GUI {

	public int promptPlayerCount();

	public void displayCard(int row, int col, Card card);

}
