package phase;

import player.Player;

public interface Phase {

    public void execute(int turnLeader);

    public void setup();

    public boolean turn(Player player);

}
