package underlings.player;

import underlings.element.utilities.ElementSpaceLogic;
import underlings.handler.HandlerFactory;

public class PlayerFactory {

    private HandlerFactory handlerFactory;
    private int playerCount;

    public PlayerFactory(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
        this.playerCount = 0;
    }

    public Player createPlayer(int maxHandlers) {
        Player player = new Player(maxHandlers, this.handlerFactory, ++this.playerCount);
        player.elementSpaceLogic = new ElementSpaceLogic();
        return player;
    }

}
