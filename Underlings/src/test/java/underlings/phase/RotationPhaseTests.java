package underlings.phase;

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Test;
import underlings.element.ElementBag;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.player.Player;

public class RotationPhaseTests {


    @Test
    public void testRotationExecute() {
        ConcreteRotationPhase rotation =
                EasyMock.createMockBuilder(ConcreteRotationPhase.class).addMockedMethod("turn").addMockedMethod("setup")
                        .addMockedMethod("setPhaseComplete").addMockedMethod("isPhaseComplete").createMock();

        rotation.displayMethod = () -> {
        };

        Player playerOne = new Player(4, new HandlerFactory(), 0);
        Player playerTwo = new Player(4, new HandlerFactory(), 0);

        rotation.players = new ArrayList<>();
        rotation.players.add(playerOne);
        rotation.players.add(playerTwo);

        rotation.setPhaseComplete(false);
        rotation.setup();
        EasyMock.expect(rotation.isPhaseComplete()).andReturn(false);
        rotation.setPhaseComplete(true);
        rotation.turn(playerOne);
        rotation.turn(playerTwo);
        EasyMock.expect(rotation.isPhaseComplete()).andReturn(true);
        EasyMock.replay(rotation);

        rotation.execute(0);

        EasyMock.verify(rotation);
    }

}


class ConcreteRotationPhase extends RotationPhase {

    public ConcreteRotationPhase(List<Player> players, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            Runnable displayMethod, Field field) {
        super(players, gui, elementBag, hatchingGround, displayMethod, field);
    }

    @Override
    public void setup() {}

    @Override
    public void turn(Player player) {}

}
