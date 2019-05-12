package underlings.phase;

import javax.swing.JOptionPane;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class WildFinalPhase implements FinalPhase {

    private Gui gui;

    public WildFinalPhase(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void execute() {
        this.gui.promptHandler.displayMessage(LocaleWrap.get("wild_game_over"), -1, JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void turn(Player player) {

    }

}
