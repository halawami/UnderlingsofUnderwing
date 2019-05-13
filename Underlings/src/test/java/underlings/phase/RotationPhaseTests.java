package underlings.phase;

import java.util.ArrayList;
import org.easymock.EasyMock;
import org.junit.Test;
import underlings.handler.HandlerFactory;
import underlings.player.Player;

public class RotationPhaseTests {


    @Test
    public void testRotationExecute() {
        RotationPhase rotation =
                EasyMock.createMockBuilder(RotationPhase.class).addMockedMethod("turn").addMockedMethod("setup")
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
