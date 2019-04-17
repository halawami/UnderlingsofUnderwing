package underlings.phase;

import java.util.List;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
import underlings.game.HatchingGround;
import underlings.gui.Choice;
import underlings.gui.ElementDrawChoice;
import underlings.gui.GUI;
import underlings.player.Player;

public class ElementPhase implements Phase {

	@Override
	public void execute(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod) {

		for (Player player : players) {

			List<ElementGiver> elementGivers = player.getElementGivers();

			while (!elementGivers.isEmpty()) {
				ElementGiver chosenElementGiver = gui.promptHandler.promptElementGiver(elementGivers);
				List<ElementDrawChoice> possibleElementDrawChoices = chosenElementGiver.getElementDrawChoices();

				Choice chosenElementDrawChoice = gui.promptHandler.promptElementDrawChoice(possibleElementDrawChoices);

				Element drawnElement = elementBag.drawRandomElement();
				player.addElement(drawnElement);

				elementGivers.remove(chosenElementGiver);
				
				displayMethod.run();
				
			}
		}

	}

}
