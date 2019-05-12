package underlings.player;

import java.util.Arrays;

import underlings.element.utilities.ElementSpaceLogic;
import underlings.handler.HandlerFactory;

public class FakePlayer extends Player {

    private static Player instance = new FakePlayer(1, new HandlerFactory(), -1);

    private FakePlayer(int maxHandlers, HandlerFactory handlerFactory, int playerId) {
        super(maxHandlers, handlerFactory, playerId);
        this.elementSpaceLogic = new ElementSpaceLogic(Arrays.asList());
    }

    public static Player getInstance() {
        return instance;
    }
}
