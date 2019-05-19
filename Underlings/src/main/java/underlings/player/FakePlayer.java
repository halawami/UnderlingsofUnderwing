package underlings.player;

import java.util.List;
import underlings.handler.HandlerFactory;
import underlings.utilities.ElementSpaceUtilities;

public class FakePlayer extends Player {

    private static Player instance = new FakePlayer(1, new HandlerFactory(), -1);

    private FakePlayer(int maxHandlers, HandlerFactory handlerFactory, int playerId) {
        super(maxHandlers, handlerFactory, playerId);
    }

    public static Player getInstance() {
        return instance;
    }

    public static void initPlayer(List<String> recipes) {
        instance.elementSpaceLogic = new ElementSpaceUtilities(recipes);
    }
}
