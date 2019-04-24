package underlings.phase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
import underlings.game.HatchingGround;
import underlings.gui.DrawChoice;
import underlings.gui.GUI;
import underlings.player.Player;

public class DrawingPhase extends SequentialPhase {

	public DrawingPhase(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod) {
		super(players, gui, elementBag, hatchingGround, displayMethod);
	}

	Map<Player, List<ElementGiver>> elementGivers;

	@Override
	public void turn(Player player) {

		DrawChoice drawChoice = this.gui.getDrawChoice(this.elementGivers.get(player));

		Element element = this.elementBag.drawElement(drawChoice);
		player.addElement(element);
		
		this.phaseComplete = this.elementGivers.get(player).isEmpty();

	}

	@Override
	public void setup() {
		this.elementGivers = new HashMap<>();

		for (Player player : this.players) {
			this.elementGivers.put(player, player.getElementGivers());
		}
	}

}
