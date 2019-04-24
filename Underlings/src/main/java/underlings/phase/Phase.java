package underlings.phase;

import underlings.player.Player;

public interface Phase {
	
	public void execute();
	
	public void setup();

	public void turn(Player player);

}
