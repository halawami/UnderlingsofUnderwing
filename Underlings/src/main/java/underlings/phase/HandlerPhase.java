package underlings.phase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import underlings.element.ElementBag;
import underlings.field.Field;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerDecision;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;

public class HandlerPhase extends RotationPhase {

    public HandlerPhase(List<Player> players, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            Runnable displayMethod, Field field, HandlerMovementLogic handlerMovementLogic) {
        super(players, gui, elementBag, hatchingGround, displayMethod, field);
        hatchingGround.lateHatching = false;
        this.handlerMovementLogic = handlerMovementLogic;
    }

    protected Map<Player, List<Handler>> unmovedHandlers;
    protected HandlerMovementLogic handlerMovementLogic;

    @Override
    public void setup() {

        this.unmovedHandlers = new HashMap<>();

        for (Player player : this.players) {
            this.unmovedHandlers.put(player, new ArrayList<>(player.handlers));

            for (Handler handler : this.unmovedHandlers.get(player)) {
                if (handler.getState() == HandlerState.BREAK_ROOM) {
                    handler.moveToState(HandlerState.READY_ROOM);
                }
            }

        }

        this.displayMethod.run();

    }

    @Override
    public void turn(Player player) {

        List<Handler> playersHandlers = this.unmovedHandlers.get(player);

        if (playersHandlers.size() != 0) {

            HandlerDecision decision =
                    this.gui.getHandlerDecision(playersHandlers, player.getId(), this.hatchingGround);
            this.handlerMovementLogic.move(decision.handler, decision.choice, player);
            this.setPhaseComplete(false);

        }
    }

}
