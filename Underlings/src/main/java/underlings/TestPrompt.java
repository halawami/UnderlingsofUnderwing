package underlings;

import java.util.List;
import java.util.Random;

import underlings.gui.ConcretePrompt;

public class TestPrompt extends ConcretePrompt {
    Random rand;
    long delay;

    public TestPrompt() {
        this(10);
    }

    public TestPrompt(long delay) {
        this.rand = new Random();
        this.delay = delay;
    }

    public void delay() {
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T promptChoice(String prompt, List<T> choices, int playerId) {
        this.delay();
        System.out.println(choices);
        T val = choices.get(this.rand.nextInt(choices.size()));
        System.out.println(prompt + " choices: " + choices + " (return " + val + ")");
        return val;
    }

    @Override
    public boolean promptDecision(String question, int playerId) {
        this.delay();
        boolean val = this.rand.nextBoolean();
        System.out.println(question + " (return " + val + ")");
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
