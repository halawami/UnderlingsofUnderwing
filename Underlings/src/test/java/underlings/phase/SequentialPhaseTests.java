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

public class SequentialPhaseTests {


    @Test
    public void testSequentialExecute() {
        ConcreteSequentialPhase rotation = EasyMock.createMockBuilder(ConcreteSequentialPhase.class)
                .addMockedMethod("turn").addMockedMethod("setup").addMockedMethod("setPhaseComplete")
                .addMockedMethod("isPhaseComplete").createMock();

        rotation.displayMethod = () -> {
        };

        Player playerOne = new Player(4, new HandlerFactory(), 0);
        Player playerTwo = new Player(4, new HandlerFactory(), 0);

        rotation.players = new ArrayList<>();
        rotation.players.add(playerOne);
        rotation.players.add(playerTwo);

        rotation.setup();
        rotation.setPhaseComplete(false);
        EasyMock.expect(rotation.isPhaseComplete()).andReturn(false);
        rotation.turn(playerOne);
        EasyMock.expect(rotation.isPhaseComplete()).andReturn(false);
        rotation.turn(playerOne);
        EasyMock.expect(rotation.isPhaseComplete()).andReturn(true);
        rotation.setPhaseComplete(false);
        EasyMock.expect(rotation.isPhaseComplete()).andReturn(false);
        rotation.turn(playerTwo);
        EasyMock.expect(rotation.isPhaseComplete()).andReturn(true);

        EasyMock.replay(rotation);

        rotation.execute(0);

        EasyMock.verify(rotation);
    }

}


class ConcreteSequentialPhase extends SequentialPhase {

    public ConcreteSequentialPhase(List<Player> players, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            Runnable displayMethod, Field field) {
        super(players, gui, elementBag, hatchingGround, displayMethod, field);
    }

    @Override
    public void setup() {}

    @Override
    public void turn(Player player) {}

}
