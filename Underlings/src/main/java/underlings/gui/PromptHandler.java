package underlings.gui;

import java.util.List;
import underlings.card.Card;

public interface PromptHandler {

    int promptInt(String prompt, int min, int max);

    <T extends Choice> T promptChoice(String prompt, List<T> choices, int playerId);

    <T> T promptChoiceDropdown(String prompt, List<T> choices, T defaultChoice);

    Card pickCard(String prompt, Card[][] cards, int playerId);

    boolean promptDecision(String question, int playerId);

    void displayMessage(String message, int playerId, int icon);

}
