package underlings.gui;

import java.util.List;

public interface PromptHandler {

    int promptInt(String prompt, int min, int max);

    <T> T promptChoice(String prompt, List<T> choices, int playerId);

    <T> T promptChoiceDropdown(String prompt, List<T> choices, T defaultChoice);

    <T> T pickFromGrid(String prompt, T[][] cards, int playerId);

    void displayMessage(String message, int playerId, int icon);

    void displayMessage(String message, int icon);

}
