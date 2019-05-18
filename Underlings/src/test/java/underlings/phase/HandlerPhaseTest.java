package underlings.phase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import underlings.handler.HandlerState;
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

        HandlerDecision handlerDecision = new HandlerDecision(player.handlers.get(0), HandlerChoice.CARD);

        EasyMock.expect(gui.getHandlerDecision(player.handlers, 0, hatchingGround)).andReturn(handlerDecision);
        handlerMovementLogic.move(handlerDecision.handler, handlerDecision.choice, player);
        Runnable displayMethod = EasyMock.mock(Runnable.class);
        displayMethod.run();

        Phase handlerPhase =
                new HandlerPhase(players, gui, null, hatchingGround, displayMethod, null, handlerMovementLogic);

        EasyMock.replay(gui, handlerMovementLogic, hatchingGround, displayMethod);

        handlerPhase.setup();
        handlerPhase.turn(player);
        assertFalse(hatchingGround.lateHatching);

        EasyMock.verify(gui, handlerMovementLogic, hatchingGround, displayMethod);
    }

    @Test
    public void testTurnHandlerInBreakRoom() {

        Gui gui = EasyMock.mock(Gui.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        HandlerMovementLogic handlerMovementLogic = EasyMock.mock(HandlerMovementLogic.class);
        Player player = new Player(6, new HandlerFactory(), 0);

        List<Player> players = new ArrayList<Player>();
        players.add(player);
        player.handlers.get(0).moveToState(HandlerState.BREAK_ROOM);
        HandlerDecision handlerDecision = new HandlerDecision(player.handlers.get(0), HandlerChoice.CARD);

        EasyMock.expect(gui.getHandlerDecision(player.handlers, 0, hatchingGround)).andReturn(handlerDecision);
        handlerMovementLogic.move(handlerDecision.handler, handlerDecision.choice, player);

        Phase handlerPhase = new HandlerPhase(players, gui, null, hatchingGround, () -> {
        }, null, handlerMovementLogic);

        EasyMock.replay(gui, handlerMovementLogic, hatchingGround);

        handlerPhase.setup();
        handlerPhase.turn(player);

        assertFalse(hatchingGround.lateHatching);
        assertEquals(HandlerState.READY_ROOM, player.handlers.get(0).getState());
        EasyMock.verify(gui, handlerMovementLogic, hatchingGround);

    }

}
