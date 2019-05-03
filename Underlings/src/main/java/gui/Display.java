package gui;

import java.util.List;

import card.Card;
import element.ElementBag;
import handler.Handler;
import player.Player;

public interface Display {

    void displayCard(int row, int col, Card card);

    void displayPlayer(int playerNumber, Player player);

    void displayHandlers(int playerNumber, List<Handler> handler);

    void displayBackground();

    void update();

    void displayStats(ElementBag elementBag, int roundsLeft, int currentPhase, int leadTurn);
}
