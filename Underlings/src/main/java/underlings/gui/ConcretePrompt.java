package underlings.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import underlings.utilities.LocaleUtilities;

public class ConcretePrompt implements PromptHandler {

    @Override
    public <T> T promptChoice(String prompt, List<T> choices, int playerId) {
        int index = this.displayOptions(choices.toArray(), this.getPlayer(playerId), prompt);
        return choices.get(index);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T pickFromGrid(String prompt, T[][] objects, int playerId) {
        if (objects.length == 0) {
            return null;
        }

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(prompt);
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(objects.length, objects[0].length));

        for (int w = 0; w < objects.length; w++) {
            for (int h = 0; h < objects[0].length; h++) {
                if (objects[w][h] == null) {
                    panel.add(new JPanel());
                } else {
                    JButton button = new JButton(objects[w][h].toString());

                    final T object = objects[w][h];
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane pane = (JOptionPane) ((JButton) e.getSource()).getParent().getParent();
                            pane.setValue(object);
                        }
                    });

                    panel.add(button);
                }
            }
        }
        optionPane.add(panel, 1);
        optionPane.remove(2);

        JDialog dialog = optionPane.createDialog(null, this.getPlayer(playerId));
        dialog.setVisible(true);

        Object value = optionPane.getValue();

        if (value == null) {
            System.exit(0);
        }

        return (T) value;
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
    public void displayMessage(String message, int playerId, int icon) {
        JOptionPane.showMessageDialog(null, message, this.getPlayer(playerId), icon);
    }

    @Override
    public void displayMessage(String message, int icon) {
        JOptionPane.showMessageDialog(null, message, "", icon);
    }

    @Override
    public int promptInt(String prompt, int min, int max) {
        int result = 0;
        do {
            try {
                result = Integer
                        .parseInt(JOptionPane.showInputDialog(LocaleUtilities.format("prompt_int", prompt, min, max)));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, LocaleUtilities.format("prompt_int_error", min, max));
            }
        } while (result > max || result < min);

        return result;
    }

    @Override
    public <T> T promptChoiceDropdown(String prompt, List<T> choices, T defaultChoice) {

        @SuppressWarnings("unchecked")
        T choice = (T) JOptionPane.showInputDialog(null, prompt, "", JOptionPane.QUESTION_MESSAGE, null,
                choices.toArray(), defaultChoice);

        return choice;
    }

    private String getPlayer(int playerId) {
        return MessageFormat.format(LocaleUtilities.get("player_number"), playerId);
    }
}
