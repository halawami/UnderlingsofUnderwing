package underlings.phase;

import java.util.List;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.ElementDrawChoice;
import underlings.gui.ElementGiver;
import underlings.gui.GUI;
import underlings.player.Player;

public class ElementPhase implements Phase {

	@Override
	public void execute(Player player, GUI gui, ElementBag elementBag, HatchingGround hatchingGround) {

		List<ElementGiver> elementGivers = player.getElementGivers();
		
		while(!elementGivers.isEmpty()) {
			ElementGiver chosenElementGiver = gui.promptHandler.promptElementGiver(elementGivers);
			List<ElementDrawChoice> possibleElementDrawChoices = chosenElementGiver.getElementDrawChoices();
			
			ElementDrawChoice chosenElementDrawChoice = gui.promptHandler.promptElementDrawChoice(possibleElementDrawChoices);
			
			Element drawnElement = elementBag.drawRandomElement();
			player.addElement(drawnElement);
			
			elementGivers.remove(chosenElementGiver);
		}
		
	}

}
