package underlings.phase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.handler.Handler;
import underlings.handler.HandlerDecision;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;

public class HandlerPhase extends RotationPhase {

	public HandlerPhase(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod) {
		super(players, gui, elementBag, hatchingGround, displayMethod);
	}

	private Map<Player, List<Handler>> unmovedHandlers;
	private HandlerMovementLogic handlerMovementLogic = new HandlerMovementLogic();

	@Override
	public void setup() {
		this.unmovedHandlers = new HashMap<>();

		for (Player player : this.players) {
			this.unmovedHandlers.put(player, new ArrayList<>(player.getHandlers()));
		}
	}

	@Override
	public void turn(Player player) {

		List<Handler> playersHandlers = this.unmovedHandlers.get(player);

		this.phaseComplete = false;

		HandlerDecision decision = this.gui.getHandlerDecision(playersHandlers);
		this.handlerMovementLogic.move(decision.handler, decision.choice);

		this.phaseComplete = playersHandlers.size() == 0;

	}

}
