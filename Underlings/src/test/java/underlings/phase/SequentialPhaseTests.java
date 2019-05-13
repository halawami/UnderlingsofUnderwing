package underlings.phase;

import java.util.ArrayList;
import org.easymock.EasyMock;
import org.junit.Test;
import underlings.handler.HandlerFactory;
import underlings.player.Player;

public class SequentialPhaseTests {


    @Test
    public void testSequentialExecute() {
        SequentialPhase sequential =
                EasyMock.createMockBuilder(SequentialPhase.class).addMockedMethod("turn").addMockedMethod("setup")
                        .addMockedMethod("setPhaseComplete").addMockedMethod("isPhaseComplete").createMock();

        sequential.displayMethod = () -> {
        };

        Player playerOne = new Player(4, new HandlerFactory(), 0);
        Player playerTwo = new Player(4, new HandlerFactory(), 0);

        sequential.players = new ArrayList<>();
        sequential.players.add(playerOne);
        sequential.players.add(playerTwo);

        sequential.setup();
        sequential.setPhaseComplete(false);
        EasyMock.expect(sequential.isPhaseComplete()).andReturn(false);
        sequential.turn(playerOne);
        EasyMock.expect(sequential.isPhaseComplete()).andReturn(false);
        sequential.turn(playerOne);
        EasyMock.expect(sequential.isPhaseComplete()).andReturn(true);
        sequential.setPhaseComplete(false);
        EasyMock.expect(sequential.isPhaseComplete()).andReturn(false);
        sequential.turn(playerTwo);
        EasyMock.expect(sequential.isPhaseComplete()).andReturn(true);

        EasyMock.replay(sequential);

        sequential.execute(0);

        EasyMock.verify(sequential);
    }

}
