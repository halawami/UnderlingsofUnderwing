package underlings.gui;

import java.util.List;

public interface PromptHandler {
	
	int promptPlayerCount(int minPlayers, int maxPlayers);
	
	int promptInt(int min, int max);
	
	<T extends Choice> T promptChoice(String prompt, List<T> choices);

	boolean promptDecision(String question);

	void displayMessage(String message);
		
}