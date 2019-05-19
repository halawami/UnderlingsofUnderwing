package underlings.gui;

import java.util.List;

import underlings.element.ElementBag;
import underlings.handler.Handler;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;

public interface Display {

    void displayHatchingGround(HatchingGround hatchingGround);

    void displayPlayers(List<Player> players);

    void displayHandlers(int playerNumber, List<Handler> handler);

    void displayBackground();

    void update();

    void displayStats(ElementBag elementBag, int roundsLeft, int currentPhase, int leadTurn);
}
