package underlings.phase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collections;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.MockTest;
import underlings.TestUtils;
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
        this.player = TestUtils.makePlayer();
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

        HandlerPhase handlerPhase =
                EasyMock.partialMockBuilder(HandlerPhase.class).addMockedMethod("setPhaseComplete").createMock();
        handlerPhase.players = this.players;
        handlerPhase.gui = this.gui;
        handlerPhase.hatchingGround = this.hatchingGround;
        handlerPhase.displayMethod = this.displayMethod;
        handlerPhase.handlerMovementLogic = this.handlerMovementLogic;

        handlerPhase.setPhaseComplete(false);

        EasyMock.replay(handlerPhase);

        handlerPhase.setup();
        handlerPhase.turn(this.player);

        assertFalse(this.hatchingGround.lateHatching);
        EasyMock.verify(handlerPhase);
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

    @Test
    public void testTurnNoHandlers() {
        HandlerPhase handlerPhase = new HandlerPhase(this.players, this.gui, null, this.hatchingGround, () -> {
        }, null, this.handlerMovementLogic);
        handlerPhase.setup();
        handlerPhase.unmovedHandlers.put(this.player, Collections.emptyList());
        this.replayAll();
        handlerPhase.turn(this.player);
    }

}
