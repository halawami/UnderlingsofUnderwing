package underlings.phase;

import java.util.ArrayList;
import java.util.List;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
import underlings.game.Handler;
import underlings.game.HatchingGround;
import underlings.gui.DrawChoice;
import underlings.gui.GUI;
import underlings.player.Player;

public class ElementPhase implements Phase {

	@Override
	public void execute(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod) {

		for (Player player : players) {

			List<ElementGiver> elementGivers = new ArrayList<>();
			for(Handler handler : player.getHandlers())
				elementGivers.add(handler.elementGiver);

			while (!elementGivers.isEmpty()) {
				ElementGiver chosenElementGiver = gui.promptHandler.promptElementGiver(elementGivers);

				DrawChoice chosenDrawChoice = gui.promptHandler.promptElementDrawChoice(chosenElementGiver.drawChoices);

				Element drawnElement = elementBag.drawRandomElement();
				player.addElement(drawnElement);

				elementGivers.remove(chosenElementGiver);

				displayMethod.run();

			}
		}

	}

}
