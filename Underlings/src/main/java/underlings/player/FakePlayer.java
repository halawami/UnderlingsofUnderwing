package underlings.player;

import underlings.element.utilities.ElementSpaceLogic;
import underlings.handler.HandlerFactory;

public class FakePlayer extends Player {

    public static Player instance = new FakePlayer();

    private FakePlayer() {
        this.elementSpaceLogic = new ElementSpaceLogic();
    }

    private FakePlayer(int maxHandlers, HandlerFactory handlerFactory, int playerId) {
        super(maxHandlers, handlerFactory, playerId);
    }

    public static Player getInstance() {
        return instance;
    }
}
