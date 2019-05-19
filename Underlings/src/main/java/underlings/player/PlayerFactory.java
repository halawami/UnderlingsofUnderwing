package underlings.player;

import java.util.List;
import underlings.handler.HandlerFactory;
import underlings.utilities.ElementSpaceUtilities;

public class PlayerFactory {

    private HandlerFactory handlerFactory;
    private int playerCount;
    private List<String> recipes;

    public PlayerFactory(HandlerFactory handlerFactory, List<String> recipes) {
        this.handlerFactory = handlerFactory;
        this.playerCount = 0;
        this.recipes = recipes;
    }

    public Player createPlayer(int maxHandlers) {
        Player player = new Player(maxHandlers, this.handlerFactory, ++this.playerCount);
        player.elementSpaceLogic = new ElementSpaceUtilities(this.recipes);

        return player;
    }

}
