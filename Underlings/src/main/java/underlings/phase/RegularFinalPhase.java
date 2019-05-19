package underlings.phase;

import java.util.List;

import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;
import underlings.utilities.LocaleWrap;

public class RegularFinalPhase implements FinalPhase {

    private Gui gui;
    private List<Player> players;
    private Phase dragonPhase;
    private ScoreUtils scoreUtils;

    public RegularFinalPhase(List<Player> players, Gui gui, Phase dragonPhase, ScoreUtils scoreUtils) {
        this.players = players;
        this.gui = gui;
        this.dragonPhase = dragonPhase;
        this.scoreUtils = scoreUtils;
    }

    @Override
    public void execute() {
        this.dragonPhase.setup();

        for (Player player : this.players) {
            this.turn(player);
        }

        this.gui.alert(LocaleWrap.get("game_over"), PromptType.WARNING);
        this.scoreUtils.calculateScores();
        this.scoreUtils.displayScores();
        this.scoreUtils.displayWinners();
    }

    @Override
    public void turn(Player player) {
        this.dragonPhase.turn(player);
        this.dragonPhase.turn(player);
    }
}
