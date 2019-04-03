package underlings.gui;

import java.util.List;

public interface PromptHandler {
	
	public int promptPlayerCount();
	
	public ElementGiver promptElementGiver(List<ElementGiver> elementGivers);
	
	public ElementDrawChoice promptElementDrawChoice(List<ElementDrawChoice> elementDrawChoices);
		
}