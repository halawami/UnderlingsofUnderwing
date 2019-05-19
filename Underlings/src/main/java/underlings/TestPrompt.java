package underlings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import underlings.card.Card;
import underlings.gui.ConcretePrompt;
import underlings.handler.HandlerChoice;
import underlings.utilities.LocaleWrap;

public class TestPrompt extends ConcretePrompt {
    Random rand;
    long delay;

    Map<Integer, List<Card>> cards = new HashMap<>();

    public TestPrompt() {
        this(10);
    }

    public TestPrompt(long delay) {
        this.rand = new Random();
        this.delay = delay;
        for (int i = 1; i <= 6; i++) {
            this.cards.put(i, new ArrayList<Card>());
        }
    }

    public void delay() {
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T promptChoice(String prompt, List<T> choices, int playerId) {

        if (choices.contains(HandlerChoice.STAY) && choices.size() == 2) {
            if (!prompt.contains("Field")) {
                return (T) HandlerChoice.STAY;
            }
        }

        if (choices.get(0) instanceof HandlerChoice && this.rand.nextInt(2) == 1) {
            if (choices.contains(HandlerChoice.CARD)) {

                return (T) HandlerChoice.CARD;
            }
        }
        this.delay();
        System.out.println(choices);
        T val = choices.get(this.rand.nextInt(choices.size()));
        System.out.println(prompt + " choices: " + choices + " (return " + val + ")");


        return val;
    }


    @Override
    public int promptInt(String prompt, int min, int max) {
        this.delay();
        int val = 0;
        do {
            val = this.rand.nextInt(max);
        } while (val < min || val > max);
        System.out.println(prompt + " (return " + val + ")");
        return val;
    }

    @Override
    public void displayMessage(String message, int playerId, int icon) {
        this.delay();
        System.out.println(playerId + ": " + message);
        if (!(message.contains("placements") || message.contains("incubation") || message.contains("applied"))) {
            super.displayMessage(message, playerId, icon);
        }
    }

    @Override
    public <T> T pickFromGrid(String prompt, T[][] objects, int playerId) {
        this.delay();
        T object = null;
        while (object == null) {
            int i = this.rand.nextInt(objects.length);
            int j = this.rand.nextInt(objects[i].length);
            object = objects[i][j];
        }
        System.out.println("\n" + playerId + ": " + prompt);
        this.printGrid(objects);
        System.out.println("picked " + object + "\n");

        if (prompt.equals(LocaleWrap.get("prompt_element_card"))) {
            for (int i = 0; i < objects.length; i++) {
                for (int j = 0; j < objects[i].length; j++) {
                    if (this.cards.get(playerId).contains(objects[i][j])) {
                        return objects[i][j];
                    }
                }
            }
        }

        if (prompt.equals(LocaleWrap.get("handler_movement_card"))) {
            this.cards.get(playerId).add((Card) object);
        }
        return object;
    }

    private <T> void printGrid(T[][] objects) {
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects[0].length; j++) {
                System.out.print(objects[i][j]);
            }
            System.out.println();
        }
    }
}
