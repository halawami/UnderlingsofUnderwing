package underlings.phase;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerDecision;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;

public class HandlerPhaseTest {

    @Test
    public void testTurn() {

        Gui gui = EasyMock.mock(Gui.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        HandlerMovementLogic handlerMovementLogic = EasyMock.mock(HandlerMovementLogic.class);
        Player player = new Player(6, new HandlerFactory(), 0);

        List<Player> players = new ArrayList<Player>();
        players.add(player);

        HandlerDecision handlerDecision = new HandlerDecision(player.getHandlers().get(0), HandlerChoice.CARD);

        EasyMock.expect(gui.getHandlerDecision(player.getHandlers(), 0, hatchingGround)).andReturn(handlerDecision);
        handlerMovementLogic.move(handlerDecision.handler, handlerDecision.choice, 0);

        Phase handlerPhase = new HandlerPhase(players, gui, null, hatchingGround, () -> {
        }, null, handlerMovementLogic);

        EasyMock.replay(gui, handlerMovementLogic, hatchingGround);

        handlerPhase.setup();
        handlerPhase.turn(player);

        EasyMock.verify(gui, handlerMovementLogic, hatchingGround);

    }

}
