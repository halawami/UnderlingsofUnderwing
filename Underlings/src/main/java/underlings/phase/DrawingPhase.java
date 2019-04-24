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
	protected void turn(Player player) {
		
		List<ElementGiver> elementGivers = player.getElementGivers();

		while (!elementGivers.isEmpty()) {

			ElementGiver elementGiver = this.gui.promptHandler.promptChoice("Choose an Element Giver", elementGivers);
			DrawChoice drawChoice = this.gui.promptHandler.promptChoice("Choose a Draw Choice", elementGiver.drawChoices);
		
			Element element = this.elementBag.drawElement(drawChoice);
			player.addElement(element);
			
			elementGivers.remove(elementGiver);
			this.displayMethod.run();
		}
	}

	@Override
	protected void setup() {

	}
	
}
