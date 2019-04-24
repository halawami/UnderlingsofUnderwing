package underlings.phase;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
import underlings.game.HatchingGround;
import underlings.gui.DrawChoice;
import underlings.gui.GUI;
import underlings.handler.Handler;
import underlings.player.Player;

import java.util.ArrayList;
import java.util.List;

public class CollectionPhase implements Phase {

	@Override
	public void execute(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod) {

		for (Player player : players) {

			List<ElementGiver> elementGivers = new ArrayList<>();
			for(Handler handler : player.getHandlers())
				elementGivers.add(handler.elementGiver);

			while (!elementGivers.isEmpty()) {
				ElementGiver chosenElementGiver = gui.promptHandler.promptChoice("Choose an Element Giver", elementGivers);
				DrawChoice chosenDrawChoice = gui.promptHandler.promptChoice("Choose a Draw", chosenElementGiver.drawChoices);

				Element drawnElement = elementBag.drawRandomElement();
				player.addElement(drawnElement);

				elementGivers.remove(chosenElementGiver);

				displayMethod.run();
			}
		}

	}

}
