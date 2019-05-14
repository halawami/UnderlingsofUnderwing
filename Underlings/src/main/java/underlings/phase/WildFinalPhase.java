package underlings.phase;

import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class WildFinalPhase implements FinalPhase {

    private Gui gui;

    public WildFinalPhase(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void execute() {
        this.gui.alert(LocaleWrap.get("wild_game_over"), PromptType.ERROR);
    }

    @Override
    public void turn(Player player) {

    }

}
