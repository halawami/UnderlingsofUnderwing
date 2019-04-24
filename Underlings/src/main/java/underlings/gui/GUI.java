package underlings.gui;

import java.util.List;

import underlings.element.ElementGiver;

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
	
}