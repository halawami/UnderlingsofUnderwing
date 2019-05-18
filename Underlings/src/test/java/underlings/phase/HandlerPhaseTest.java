package underlings.phase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.Constructors;
import underlings.MockTest;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerDecision;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;
import underlings.player.Player;

public class HandlerPhaseTest extends MockTest {

    @Before
    public void init() {
        this.gui = this.mock(Gui.class);
        this.hatchingGround = this.mock(HatchingGround.class);
        this.handlerMovementLogic = this.mock(HandlerMovementLogic.class);
        this.displayMethod = this.mock(Runnable.class);
        this.player = Constructors.Player();
        this.players = new ArrayList<Player>();
        this.players.add(this.player);
    }

    @Test
    public void testTurn() {
        HandlerDecision handlerDecision = new HandlerDecision(this.player.handlers.get(0), HandlerChoice.CARD);

        EasyMock.expect(this.gui.getHandlerDecision(this.player.handlers, 0, this.hatchingGround))
                .andReturn(handlerDecision);
        this.handlerMovementLogic.move(handlerDecision.handler, handlerDecision.choice, this.player);

        this.displayMethod.run();

        this.replayAll();

        Phase handlerPhase = new HandlerPhase(this.players, this.gui, null, this.hatchingGround, this.displayMethod,
                null, this.handlerMovementLogic);


        handlerPhase.setup();
        handlerPhase.turn(this.player);
        assertFalse(this.hatchingGround.lateHatching);

    }

    @Test
    public void testTurnHandlerInBreakRoom() {
        this.player.handlers.get(0).moveToState(HandlerState.BREAK_ROOM);
        HandlerDecision handlerDecision = new HandlerDecision(this.player.handlers.get(0), HandlerChoice.CARD);

        EasyMock.expect(this.gui.getHandlerDecision(this.player.handlers, 0, this.hatchingGround))
                .andReturn(handlerDecision);
        this.handlerMovementLogic.move(handlerDecision.handler, handlerDecision.choice, this.player);

        Phase handlerPhase = new HandlerPhase(this.players, this.gui, null, this.hatchingGround, () -> {
        }, null, this.handlerMovementLogic);

        this.replayAll();

        handlerPhase.setup();
        handlerPhase.turn(this.player);

        assertFalse(this.hatchingGround.lateHatching);
        assertEquals(HandlerState.READY_ROOM, this.player.handlers.get(0).getState());
    }

}
