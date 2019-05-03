package underlings.gui;

import java.util.List;

public interface PromptHandler {

    int promptInt(String prompt, int min, int max);

    <T extends Choice> T promptChoice(String prompt, List<T> choices,
        int playerId);

    boolean promptDecision(String question, int playerId);

    void displayMessage(String message, int playerId, int icon);

}
