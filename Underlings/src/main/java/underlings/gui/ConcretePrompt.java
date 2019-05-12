package underlings.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import underlings.card.Card;

public class ConcretePrompt implements PromptHandler {

    @Override
    public <T extends Choice> T promptChoice(String prompt, List<T> choices, int playerId) {
        int index = this.displayOptions(choices.toArray(), "Player " + playerId, prompt);
        return choices.get(index);
    }

    @Override
    public Card pickCard(String prompt, Card[][] cards, int playerId) {
        if (cards.length == 0) {
            return null;
        }

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(prompt);
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(cards.length, cards[0].length));

        for (int w = 0; w < cards.length; w++) {
            for (int h = 0; h < cards[0].length; h++) {
                if (cards[w][h] == null) {
                    panel.add(new JPanel());
                } else {
                    JButton button = new JButton(cards[w][h].toString());

                    final Card returnCard = cards[w][h];
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane pane = (JOptionPane) ((JButton) e.getSource()).getParent().getParent();
                            pane.setValue(returnCard);
                        }
                    });

                    panel.add(button);
                }
            }
        }
        optionPane.add(panel, 1);
        optionPane.remove(2);

        JDialog dialog = optionPane.createDialog(null, "Player " + playerId);
        dialog.setVisible(true);

        Object value = optionPane.getValue();

        if (value == null || value instanceof Integer) {
            System.exit(0);
        }

        return (Card) value;
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

    @Override
    public <T> T promptChoiceDropdown(String prompt, List<T> choices, T defaultChoice) {

        @SuppressWarnings("unchecked")
        T choice = (T) JOptionPane.showInputDialog(null, prompt, "", JOptionPane.QUESTION_MESSAGE, null,
                choices.toArray(), defaultChoice);

        return choice;
    }

}
