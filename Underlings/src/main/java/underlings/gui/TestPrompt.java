package underlings.gui;

import java.util.List;
import java.util.Random;

public class TestPrompt extends LamePrompt {

    @Override
    public <T extends Choice> T promptChoice(String prompt, List<T> choices,
        int playerId) {
        return choices.get(new Random().nextInt(choices.size()));
    }

    @Override
    public boolean promptDecision(String question, int playerId) {
        return (new Random().nextBoolean());
    }

    @Override
    public int promptInt(String prompt, int min, int max) {
        int val = 0;
        do {
            val = new Random().nextInt(max);
        } while (val < min || val > max);
        return val;
    }

    @Override
    public void displayMessage(String message, int playerId, int icon) {
        if (!(message.contains("placements") || message.contains("incubation")
            || message.contains("applied"))) {
            super.displayMessage(message, playerId, icon);
        }
    }
}
