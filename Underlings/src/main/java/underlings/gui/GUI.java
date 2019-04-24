package underlings.gui;

import java.util.List;

import underlings.element.ElementGiver;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerDecision;

public class GUI {
	public PromptHandler promptHandler;
	public Display display;

	public GUI(PromptHandler promptHandler, Display display) {
		this.promptHandler = promptHandler;
		this.display = display;
	}

	public DrawChoice getDrawChoice(List<ElementGiver> elementGivers) {
		ElementGiver elementGiver = this.promptHandler.promptChoice("Choose and Element Giver", elementGivers);
		elementGivers.remove(elementGiver);
		return this.promptHandler.promptChoice("Choose a Draw Choice", elementGiver.drawChoices);
	}

	public HandlerDecision getHandlerDecision(List<Handler> handlers) {
		Handler handler = this.promptHandler.promptChoice("Choose a Handler", handlers);
		handlers.remove(handler);
		
		List<HandlerChoice> possibleChoices = handler.getPossibleChoices();
		HandlerChoice handlerChoice = this.promptHandler.promptChoice("Choose a movement for " + handler, possibleChoices);
		
		return new HandlerDecision(handler, handlerChoice);
	}

}