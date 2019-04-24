package underlings.phase;

import java.util.List;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
import underlings.game.HatchingGround;
import underlings.gui.DrawChoice;
import underlings.gui.GUI;
import underlings.player.Player;

public class DrawingPhase extends Phase {

	public DrawingPhase(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod) {
		super(players, gui, elementBag, hatchingGround, displayMethod);
	}

	@Override
	public void turn(Player player) {

		List<ElementGiver> elementGivers = player.getElementGivers();

		while (!elementGivers.isEmpty()) {
			
			DrawChoice drawChoice = this.gui.getDrawChoice(elementGivers);

			Element element = this.elementBag.drawElement(drawChoice);
			player.addElement(element);

			this.displayMethod.run();
		}
	}

	@Override
	public void setup() {

	}

}
