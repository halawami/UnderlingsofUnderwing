package underlings.player;

import java.util.List;

import underlings.element.utilities.ElementSpaceLogic;
import underlings.handler.HandlerFactory;

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
        player.elementSpaceLogic = new ElementSpaceLogic(this.recipes);

        return player;
    }

}
