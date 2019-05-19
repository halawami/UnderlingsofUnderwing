package underlings.phase;

import underlings.player.Player;

public interface FinalPhase {

    public enum FinalPhaseType {
        REGULAR, WILD;
    }

    public void execute();

    public void turn(Player player);

}
