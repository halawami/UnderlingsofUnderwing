package underlings.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConcretePrompt implements PromptHandler {

    @Override
    public <T extends Choice> T promptChoice(String prompt, List<T> choices, int playerId) {
        int index = this.displayOptions(choices.toArray(), "Player " + playerId, prompt);
        return choices.get(index);
    }

    @Override
    public <T extends Choice> T promptChoice(String prompt, List<T> choices, int playerId, Dimension dim) {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(prompt);
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(dim.width, dim.height));

        for (T choice : choices) {
            JButton button = new JButton(choice.toString());
            panel.add(button);
        }
        optionPane.add(panel, 1);

        JDialog dialog = optionPane.createDialog(null, "Player " + playerId);
        dialog.setVisible(true);

        int value = JOptionPane.showOptionDialog(null, prompt, "Player " + playerId, JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, choices.toArray(), choices.get(0));
        if (value == -1) {
            System.exit(0);
        }

        return choices.get(value);
    }

    private int displayOptions(Object[] options, String title, String message) {
        int value = JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (value == -1) {
            System.exit(0);
        }

        return value;
    }

    @Override
    public boolean promptDecision(String question, int playerId) {
        int option = JOptionPane.showConfirmDialog(null, question, "Player " + playerId, JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    @Override
    public void displayMessage(String message, int playerId, int icon) {
        JOptionPane.showMessageDialog(null, message, "Player " + playerId, icon);
    }

    @Override
    public int promptInt(String prompt, int min, int max) {
        int result = 0;
        do {
            try {
                result = Integer.parseInt(JOptionPane.showInputDialog(prompt + " [" + min + ", " + max + "]"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter a number in [" + min + ", " + max + "].");
            }
        } while (result > max || result < min);

        return result;
    }

}
